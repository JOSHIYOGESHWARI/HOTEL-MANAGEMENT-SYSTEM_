package com.hotel.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut("execution(* com.hotel.controller..*(..)) || execution(* com.hotel.service..*(..))")
	public void applicationPackagePointcut() {
		// Pointcut to match all methods in the controller and service packages
	}

	@Before("applicationPackagePointcut()")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
	}

	@AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("Exiting method: {} with result: {}", joinPoint.getSignature().getName(), result);
	}

	@AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		logger.error("Exception in method: {} with error: {}", joinPoint.getSignature().getName(), error.getMessage(),
				error);
	}
}