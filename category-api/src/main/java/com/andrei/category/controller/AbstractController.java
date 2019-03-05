package com.andrei.category.controller;

import com.andrei.category.exception.Http400Exception;
import com.andrei.category.exception.Http404Exception;
import com.andrei.category.exception.RestAPIException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

@Slf4j
public abstract class AbstractController implements ApplicationEventPublisherAware {

    protected ApplicationEventPublisher applicationEventPublisher;
    protected static final String DEFAULT_PAGE_SIZE = "20";
    protected static final String DEFAULT_PAGE_NUMBER = "0";

    private Counter http400CounterException = Metrics.counter("com.andrei.controller.HTTP400");
    private Counter http404CounterException = Metrics.counter("com.andrei.controller.HTTP404");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Http400Exception.class)
    public @ResponseBody
    RestAPIException handleBadRequestException(Http400Exception exception, WebRequest request, HttpServletResponse response) {

        log.info("Received Data Store Exception {} ", exception.getLocalizedMessage());
        http400CounterException.increment();
        return new RestAPIException(exception.getMessage(), "please check the parameters for the request");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Http404Exception.class)
    public @ResponseBody
    RestAPIException handleResourceNotFoundException(Http404Exception exception, WebRequest request, HttpServletResponse response) {

        log.info("Resource not found Exception {} ", exception.getLocalizedMessage());
        http404CounterException.increment();
        return new RestAPIException(exception.getMessage(), "Requested resource not found");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}