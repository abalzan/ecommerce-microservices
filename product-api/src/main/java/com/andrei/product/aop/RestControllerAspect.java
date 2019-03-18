package com.andrei.product.aop;

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

    private Counter counterControllerCreateSuccessful = Metrics.counter("com.andrei.product.controller.create");
    private Counter counterControllerUpdateSuccessful = Metrics.counter("com.andrei.product.controller.update");
    private Counter counterControllerDeleteSuccessful = Metrics.counter("com.andrei.product.controller.delete");

    private Counter counterControllerCalls = Metrics.counter("com.andrei.product.controller.execution");
    private Counter counterControllerException = Metrics.counter("com.andrei.product.controller.exception");

    @Pointcut("execution(public * com.andrei.product.controller.ProductController.*(..))")
    private void allMethodsProductControllerClass() {
    }

    @Before("allMethodsProductControllerClass()")
    public void generallAllMethodsAspect(JoinPoint joinPoint) {
        log.info("Calling method(s) {} with parameters {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @Before("allMethodsProductControllerClass()")
    public void getCalledProductOperation() {
        log.info("incrementing calls to productController");
        counterControllerCalls.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.product.controller.ProductController.createProduct(..))")
    public void getCalledOnProductSave() {
        log.info("Incrementing statistics create product");
        counterControllerCreateSuccessful.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.product.controller.ProductController.updateProduct(..))")
    public void getCalledOnProductUpdate() {
        log.info("Incrementing statistics update product");
        counterControllerUpdateSuccessful.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.product.controller.ProductController.deleteProduct(..))")
    public void getCalledOnProductDelete() {
        log.info("Incrementing statistics delete product");
        counterControllerDeleteSuccessful.increment();
    }

    @AfterThrowing(value = "allMethodsProductControllerClass()", throwing = "productException")
    public void getCalledOnProductException(JoinPoint joinPoint, Throwable productException) {
        log.info("Incrementing statistics exception for product");
        counterControllerException.increment();

        log.error("Method {}, with arguments {} Throws the exception", joinPoint.getSignature(), joinPoint.getArgs(), productException);
    }

//    @Around("execution(public * com.andrei.product.controller.ProductController.*(..))")
//    public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//
//        log.info("Calling method(s) {} with parameters {}", proceedingJoinPoint.getSignature(), proceedingJoinPoint.getArgs());
//        long beginTimeMillis = System.currentTimeMillis();
//
//        final Object proceed;
//        try {
//            proceed = proceedingJoinPoint.proceed();
//        }catch (Exception e) {
//            log.error("Method {}, with arguments {} Throws the exception", proceedingJoinPoint.getSignature(), proceedingJoinPoint.getArgs());
//            throw e;
//        }
//
//        long endTimeMillis = System.currentTimeMillis();
//
//        log.info("It took {} seconds to be proceed", (endTimeMillis-beginTimeMillis)/1000);
//
//        return proceed;
//    }
}
