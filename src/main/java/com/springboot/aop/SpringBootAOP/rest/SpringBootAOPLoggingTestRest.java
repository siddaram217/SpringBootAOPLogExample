package com.springboot.aop.SpringBootAOP.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.aop.SpringBootAOP.aspect.Auditable;

@RestController
public class SpringBootAOPLoggingTestRest {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootAOPLoggingTestRest.class);
	
	@GetMapping("/testAOP/{name}")
	@Auditable/*(actionName="TEST_NAME",actionType="TEST_GET")*/
	public String testAOPLogging(@PathVariable("name") String name) {
		logger.info("SpringBootAOPLoggingTestRest.testAOPLogging() "+name);
		try {
			Thread.currentThread().sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
}
