package com.andrei.product.listener;

import com.andrei.product.event.ProductEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEventListener {

    @EventListener
    public void onApplicationEvent(ProductEvent productEvent){

        log.info("Reveived Product Event:{} ", productEvent.getEventType());
        log.info("Receive Product From Product Event: {} ", productEvent.getProduct().toString());
    }
}
