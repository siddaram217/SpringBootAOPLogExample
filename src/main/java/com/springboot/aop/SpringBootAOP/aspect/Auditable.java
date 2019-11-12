package com.springboot.aop.SpringBootAOP.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Auditable {
	
	/*Audit action type
	String actionType();
	
	Audit action Name
	String actionName();*/
}
