package com.andrei.category.aop;

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

    private Counter counterControllerCreateSuccessful = Metrics.counter("com.andrei.category.controller.create");
    private Counter counterControllerUpdateSuccessful = Metrics.counter("com.andrei.category.controller.update");
    private Counter counterControllerDeleteSuccessful = Metrics.counter("com.andrei.category.controller.delete");

    private Counter counterControllerCalls = Metrics.counter("com.andrei.category.controller.execution");
    private Counter counterControllerException = Metrics.counter("com.andrei.category.controller.exception");

    @Pointcut("execution(public * com.andrei.category.controller.CategoryController.*(..))")
    private void allMethodsCategoryControllerClass() {
    }

    @Before("allMethodsCategoryControllerClass()")
    public void generallAllMethodsAspect(JoinPoint joinPoint) {
        log.info("Calling method(s) {} with parameters {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @Before("allMethodsCategoryControllerClass()")
    public void getCalledCategoryOperation() {
        log.info("incrementing calls to categoryController");
        counterControllerCalls.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.category.controller.CategoryController.createCategory(..))")
    public void getCalledOnCategorySave() {
        log.info("Incrementing statistics create category");
        counterControllerCreateSuccessful.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.category.controller.CategoryController.updateCategory(..))")
    public void getCalledOnCategoryUpdate() {
        log.info("Incrementing statistics update category");
        counterControllerUpdateSuccessful.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.category.controller.CategoryController.deleteCategory(..))")
    public void getCalledOnCategoryDelete() {
        log.info("Incrementing statistics delete category");
        counterControllerDeleteSuccessful.increment();
    }

    @AfterThrowing(value = "allMethodsCategoryControllerClass()", throwing = "categoryException")
    public void getCalledOnCategoryException(JoinPoint joinPoint, Throwable categoryException) {
        log.info("Incrementing statistics exception for category");
        counterControllerException.increment();

        log.error("Method {}, with arguments {} Throws the exception", joinPoint.getSignature(), joinPoint.getArgs(), categoryException);
    }

}
