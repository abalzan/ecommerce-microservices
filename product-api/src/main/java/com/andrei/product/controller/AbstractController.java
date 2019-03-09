package com.andrei.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

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