package com.springboot.aop.SpringBootAOP.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuditAspect {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(AuditAspect.class);

	@Around("@annotation(auditable)")
	public Object doAudit(ProceedingJoinPoint joinPoint,Auditable auditable) throws Exception {
		Object returnObj = null;
		long start = System.currentTimeMillis();
		logger.info("AuditAspect doAudit start time " + start);
		try {
			if (joinPoint != null) {
				returnObj = joinPoint.proceed();
			} else {
				returnObj = null;
			}
			long end = System.currentTimeMillis();
			logger.info("AuditAspect doAudit end time " + end);
			long elapsedTime = end - start;
			createAudit(joinPoint, Boolean.TRUE, null, elapsedTime,returnObj);
		} catch (Throwable e) {
			logger.info("Inside doAudit @Exception ", e);
			long elapsedTime = System.currentTimeMillis() - start;
			createAudit(joinPoint, Boolean.FALSE, e, elapsedTime,null);
			throw new Exception(e);
		}
		return returnObj;
	}

	private void createAudit(ProceedingJoinPoint joinPoint, Boolean isSuccess, Throwable e, long elapsedTime, Object output) {
		logger.info("AuditAspect createAudit isSuccess " + isSuccess);
		try {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			String parameter = getParameters(signature, joinPoint);
			Audits audits = new Audits();
			audits.setAuditMethod(signature.getName());
			audits.setParameters(parameter);
			audits.setErrorMessage(e != null ? e.getMessage() : null);
			audits.setIsSuccess(isSuccess);
			audits.setTotalTime(elapsedTime);
			audits.setResponse(output);
			HttpServletRequest request = getRequest();
			if (request != null) {
				String ipAddress = getRequest().getHeader("X-FORWARDED-FOR");
				if (ipAddress == null) {
					ipAddress = getRequest().getRemoteAddr();
				}
				audits.setRemoteIp(ipAddress);
				audits.setHost(getRequest().getLocalAddr() + ":" + getRequest().getLocalPort());
			}
			logger.info("AuditAspect createAudit audits " + audits);
		} catch (Exception e1) {
			logger.error("AuditAspect createAudit Exception ", e1);
		}
	}

	private String getParameters(MethodSignature signature, ProceedingJoinPoint joinPoint) {
		String[] names = signature.getParameterNames();
		Class<?>[] types = signature.getParameterTypes();
		Object[] objs = joinPoint.getArgs();
		StringBuilder parameters = new StringBuilder();
		for (int i = 0; i < names.length; i++) {
			parameters.append(names[i] + " : " + objs[i] + ",");
		}
		String parameter = null;
		if (parameters != null && parameters.length() != 0) {
			parameter = parameters.substring(0, parameters.length() - 1);
		}
		return parameter;
	}

	public HttpServletRequest getRequest() {
		HttpServletRequest serv = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		return serv;
	}
}
