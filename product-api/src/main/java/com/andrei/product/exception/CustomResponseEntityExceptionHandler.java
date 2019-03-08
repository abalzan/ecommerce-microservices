package com.andrei.product.exception;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private Counter http400CounterException = Metrics.counter("com.andrei.controller.HTTP400");
    private Counter http404CounterException = Metrics.counter("com.andrei.controller.HTTP404");
    private Counter httpGenericCounterException = Metrics.counter("com.andrei.controller.Exception");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Http400Exception.class)
    public @ResponseBody
    RestAPIException handleBadRequestException(Http400Exception exception, WebRequest request, HttpServletResponse response) {

        log.info("Received Data Store Exception {} ", exception.getLocalizedMessage());
        http400CounterException.increment();
        return new RestAPIException(new Date(), exception.getMessage(), "please check the parameters for the request");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Http404Exception.class)
    public @ResponseBody
    RestAPIException handleResourceNotFoundException(Http404Exception exception, WebRequest request, HttpServletResponse response) {

        log.info("Resource not found Exception {} ", exception.getLocalizedMessage());
        http404CounterException.increment();
        return new RestAPIException(new Date(), exception.getMessage(), "Requested resource not found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    RestAPIException handleRuntimeException(Exception exception, WebRequest request, HttpServletResponse response) {

        log.info("Resource not found Exception {} ", exception.getLocalizedMessage());
        httpGenericCounterException.increment();
        return new RestAPIException(new Date(), exception.getMessage(), "Requested resource not found");
    }


}
