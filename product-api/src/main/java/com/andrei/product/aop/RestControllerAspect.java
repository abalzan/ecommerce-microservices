package com.andrei.product.aop;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class RestControllerAspect {

    private Counter counter = Metrics.counter("com.andrei.product.created");

    @Before("execution(public * com.andrei.product.controller.*Controller.*(..))")
    public void generallAllMethodsAspect(){
      log.info("calling aop");
    }


    @AfterReturning("execution(public * com.andrei.product.controller.*Controller.createProduct(..))")
    public void getCalledOnProductSave(){
        log.info("incrementing saved product");
        counter.increment();
    }
}
