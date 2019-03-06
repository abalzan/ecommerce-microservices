package com.andrei.product.controller;

import com.andrei.product.exception.Http400Exception;
import com.andrei.product.exception.Http404Exception;
import com.andrei.product.exception.RestAPIException;
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

    ApplicationEventPublisher applicationEventPublisher;
    static final String DEFAULT_PAGE_SIZE = "20";
    static final String DEFAULT_PAGE_NUMBER = "0";

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}