package com.springboot.aop.SpringBootAOP.aspect;

import java.io.Serializable;

public class Audits implements Serializable{

	/*private String auditName;
	
	private String auditType;*/
	
	private String auditMethod;
	
	private String parameters;
	
	private String username;
		
	private String host;
	
	private String remoteIp;
	
	private String errorMessage;
	
	private Boolean isSuccess;
	
	private Long totalTime;
	
	private Object response;
	
	public String getAuditMethod() {
		return auditMethod;
	}

	public void setAuditMethod(String auditMethod) {
		this.auditMethod = auditMethod;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "Audits [auditMethod=" + auditMethod + ", parameters=" + parameters + ", username=" + username
				+ ", host=" + host + ", remoteIp=" + remoteIp + ", errorMessage=" + errorMessage + ", isSuccess="
				+ isSuccess + ", totalTime=" + totalTime + ", response=" + response + "]";
	}

}
