package com.billing.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging method execution in the controller and service layers.
 * This aspect captures method execution details such as arguments, return values, and exceptions.
 */
@Aspect
@Component
public class LoggingAspect {

    // Logger to capture log information
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut that matches all methods in the controller and service packages.
     * It is used to define where the logging aspect will be applied.
     */
    @Pointcut("execution(* com.billing.controller..*(..)) || execution(* com.billing.service..*(..))")
    public void applicationPackagePointcut() {
        // Empty method body, serves as a pointcut expression for matching methods
    }

    /**
     * Log method entry with method name and arguments.
     * This will be executed before the matched methods.
     *
     * @param joinPoint contains details of the method being executed
     */
    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    /**
     * Log method exit with method name and return result.
     * This will be executed after the matched methods complete successfully.
     *
     * @param joinPoint contains details of the method being executed
     * @param result the result returned by the method
     */
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", joinPoint.getSignature().getName(), result);
    }

    /**
     * Log method exception with method name and exception details.
     * This will be executed if the matched methods throw an exception.
     *
     * @param joinPoint contains details of the method being executed
     * @param error the exception thrown by the method
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: {} with error: {}", joinPoint.getSignature().getName(), error);
    }
}
