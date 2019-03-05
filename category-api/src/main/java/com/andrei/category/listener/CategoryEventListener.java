package com.andrei.category.listener;

import com.andrei.category.event.CategoryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CategoryEventListener {

    @EventListener
    public void onApplicationEvent(CategoryEvent categoryEvent){

        log.info("Reveived Category Event:{} ", categoryEvent.getEventType());
        log.info("Receive Category From Category Event: {} ", categoryEvent.getCategory().toString());
    }
}
