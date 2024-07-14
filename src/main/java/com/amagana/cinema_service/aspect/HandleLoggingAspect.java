package com.amagana.cinema_service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.amagana.cinema_service.exception.CinemaBussnessException;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class HandleLoggingAspect {

    @Pointcut("execution(* com.amagana.cinema_service.controllers..*(..))")
    public void setupHandleLogging() {}

    /**
     *ObjectMapper objectMapper;
     */
    

    @Before(value = "setupHandleLogging()")
    public void handleBeforeAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("{}::{} Starting proceed...", signature.getDeclaringTypeName(), signature.getName());
    }

    @AfterReturning(value = "setupHandleLogging()", returning = "result")
    public void handleAfterReturning(JoinPoint joinPoint, Object result) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("{}::{} End with results {}", signature.getDeclaringTypeName(), signature.getName(), result);
    }

    @AfterThrowing(value = "setupHandleLogging()", throwing = "exception") 
    public void handleAfterTrowing(JoinPoint joinPoint, Exception exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.error("{}::{} Errors occurs with value  {}", signature.getDeclaringTypeName(), signature.getName(), exception.getMessage());
        throw new CinemaBussnessException(exception.getMessage());
    }
}
