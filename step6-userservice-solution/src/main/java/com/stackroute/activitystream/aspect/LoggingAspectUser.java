package com.stackroute.activitystream.aspect;

import org.aspectj.lang.annotation.Aspect;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspectUser {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspectUser.class);

	@Before("execution(* com.stackroute.activitystream.controller.UserController.*(..))")
	public void logBeforeSave(JoinPoint joinPoint) {
		logger.info("============@Before==========");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("*********************************");
	}

	@After("execution(* com.stackroute.activitystream.controller.UserController.*(..))")
	public void logAfterSave(JoinPoint joinPoint) {
		logger.info("============@After==========");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
		logger.debug("*********************************");
	}

	@AfterReturning(pointcut = "execution(* com.stackroute.activitystream.controller.UserController.*(..))", returning = "result")
	public void logAfterReturningSave(JoinPoint joinPoint, Object result) {
		logger.debug("============@AfterReturning==========");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
		logger.debug("*********************************");
	}

	@AfterThrowing(pointcut = "execution(* com.stackroute.activitystream.controller.UserController.*(..))", throwing = "error")
	public void logAfterThrowingSave(JoinPoint joinPoint, Throwable error) {
		logger.info("============@AfterThrowing==========");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
		logger.debug("Exception : " + error);
		logger.debug("*********************************");
	}
}