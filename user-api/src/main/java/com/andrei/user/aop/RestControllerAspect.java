package com.andrei.user.aop;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class RestControllerAspect {

    private Counter counterControllerCreateSuccessful = Metrics.counter("com.andrei.user.controller.create");
    private Counter counterControllerUpdateSuccessful = Metrics.counter("com.andrei.user.controller.update");
    private Counter counterControllerDeleteSuccessful = Metrics.counter("com.andrei.user.controller.delete");

    private Counter counterControllerCalls = Metrics.counter("com.andrei.user.controller.execution");
    private Counter counterControllerException = Metrics.counter("com.andrei.user.controller.exception");

    @Pointcut("execution(public * com.andrei.user.controller.UserController.*(..))")
    private void allMethodsUserControllerClass() {
    }

    @Before("allMethodsUserControllerClass()")
    public void generallAllMethodsAspect(JoinPoint joinPoint) {
        log.info("Calling method(s) {} with parameters {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @Before("allMethodsUserControllerClass()")
    public void getCalledUserOperation() {
        log.info("incrementing calls to userController");
        counterControllerCalls.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.user.controller.UserController.createUser(..))")
    public void getCalledOnUserSave() {
        log.info("Incrementing statistics create user");
        counterControllerCreateSuccessful.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.user.controller.UserController.updateUser(..))")
    public void getCalledOnUserUpdate() {
        log.info("Incrementing statistics update user");
        counterControllerUpdateSuccessful.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.user.controller.UserController.deleteUser(..))")
    public void getCalledOnUserDelete() {
        log.info("Incrementing statistics delete user");
        counterControllerDeleteSuccessful.increment();
    }

    @AfterThrowing(value = "allMethodsUserControllerClass()", throwing = "userException")
    public void getCalledOnUserException(JoinPoint joinPoint, Throwable userException) {
        log.info("Incrementing statistics exception for user");
        counterControllerException.increment();

        log.error("Method {}, with arguments {} Throws the exception", joinPoint.getSignature(), joinPoint.getArgs(), userException);
    }
}
