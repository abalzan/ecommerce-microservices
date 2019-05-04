package com.andrei.address.aop;

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

    private Counter counterControllerCreateSuccessful = Metrics.counter("com.andrei.address.controller.create");
    private Counter counterControllerUpdateSuccessful = Metrics.counter("com.andrei.address.controller.update");
    private Counter counterControllerDeleteSuccessful = Metrics.counter("com.andrei.address.controller.delete");

    private Counter counterControllerCalls = Metrics.counter("com.andrei.address.controller.execution");
    private Counter counterControllerException = Metrics.counter("com.andrei.address.controller.exception");

    @Pointcut("execution(public * com.andrei.address.controller.AddressController.*(..))")
    private void allMethodsAddressControllerClass() {
    }

    @Before("allMethodsAddressControllerClass()")
    public void generallAllMethodsAspect(JoinPoint joinPoint) {
        log.info("Calling method(s) {} with parameters {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @Before("allMethodsAddressControllerClass()")
    public void getCalledAddressOperation() {
        log.info("incrementing calls to addressController");
        counterControllerCalls.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.address.controller.AddressController.createAddress(..))")
    public void getCalledOnAddressSave() {
        log.info("Incrementing statistics create address");
        counterControllerCreateSuccessful.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.address.controller.AddressController.updateAddress(..))")
    public void getCalledOnAddressUpdate() {
        log.info("Incrementing statistics update address");
        counterControllerUpdateSuccessful.increment();
    }

    @AfterReturning(value = "execution(public * com.andrei.address.controller.AddressController.deleteAddress(..))")
    public void getCalledOnAddressDelete() {
        log.info("Incrementing statistics delete address");
        counterControllerDeleteSuccessful.increment();
    }

    @AfterThrowing(value = "allMethodsAddressControllerClass()", throwing = "addressException")
    public void getCalledOnAddressException(JoinPoint joinPoint, Throwable addressException) {
        log.info("Incrementing statistics exception for address");
        counterControllerException.increment();

        log.error("Method {}, with arguments {} Throws the exception", joinPoint.getSignature(), joinPoint.getArgs(), addressException);
    }
}
