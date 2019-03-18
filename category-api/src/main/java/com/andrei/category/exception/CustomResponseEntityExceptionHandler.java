package com.andrei.category.exception;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private Counter http400CounterException = Metrics.counter("com.andrei.controller.HTTP400");
    private Counter http404CounterException = Metrics.counter("com.andrei.controller.HTTP404");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Http400Exception.class)
    public RestAPIException handleBadRequestException(Http400Exception exception) {

        log.info("Received Data Store Exception {} ", exception.getLocalizedMessage());
        http400CounterException.increment();
        return new RestAPIException(new Date(), exception.getMessage(), "please check the parameters for the request");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Http404Exception.class)
    public RestAPIException handleResourceNotFoundException(Http404Exception exception) {

        log.info("Resource not found Exception {} ", exception.getLocalizedMessage());
        http404CounterException.increment();
        return new RestAPIException(new Date(), exception.getMessage(), "Requested resource not found");
    }

}
