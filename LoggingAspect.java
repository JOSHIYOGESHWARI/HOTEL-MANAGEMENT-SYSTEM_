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

@Aspect  // Marks this class as an Aspect for Aspect-Oriented Programming (AOP)
@Component  // Makes this class a Spring Bean so it can be automatically detected
public class LoggingAspect {
	
	// Logger for logging method calls
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	 
    @Pointcut("execution(* com.hotel.controller..*(..)) || execution(* com.hotel.service..*(..))")
  
    public void applicationPackagePointcut() {
        // Pointcut to match all methods in the controller and service packages
    }
 
    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
    	
    	 // Logs the method name and its arguments
        logger.info("Entering method: " + joinPoint.getSignature().getName() + " with arguments: " + joinPoint.getArgs());
    }
 
 // logs the method name and its return value.
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: " + joinPoint.getSignature().getName() + " with result: " + result);
    }
 
 // logs the method name and the error message.
    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: " + joinPoint.getSignature().getName() + " with error: " + error);
    }

}

