package com.amagana.cinema_service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.amagana.cinema_service.exception.CinemaBussnessException;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class HandleExceptionAspect {

    @Pointcut("execution(* com.amagana.cinema_service.service..*(..))")
    public void pointcutServiceLayer(){}

    @Before(value = "pointcutServiceLayer()")
    public void logBeforeServiceExecution(JoinPoint joinPoint) {
      MethodSignature signature = (MethodSignature) joinPoint.getSignature();
      log.info("{}::{} started execution", signature.getDeclaringTypeName(), signature.getName());
    }

    @AfterReturning(value = "pointcutServiceLayer()", returning = "result")
    public void logAfterServiceExecution(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("{}::{} end execution with value {}", signature.getDeclaringTypeName(), signature.getName(), result);
    }

    @AfterThrowing(value = "pointcutServiceLayer()", throwing = "exception")
    public void logAfterServiceExecutionWithError(JoinPoint joinPoint, Exception exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.error("{}::{} end excution with errors {}",signature.getDeclaringTypeName(), signature.getName()
        , exception.getMessage());
        throw new CinemaBussnessException("Execution failed with error "+ exception.getMessage());
    }

    @Around(value = "pointcutServiceLayer()")
    public Object logAroundServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.error("{}::{} end excution with errors {}",signature.getDeclaringTypeName(), signature.getName());
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("Execution failed with error", e.getMessage());
            throw new CinemaBussnessException("Execution failed with error "+ e.getMessage());
        }
    }

}
