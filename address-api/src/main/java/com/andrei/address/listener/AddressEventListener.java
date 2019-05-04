package com.andrei.address.listener;

import com.andrei.address.event.AddressEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AddressEventListener {

    @EventListener
    public void onApplicationEvent(AddressEvent addressEvent){

        log.info("Received Address Event:{} ", addressEvent.getEventType());
        log.info("Receive Address From Address Event: {} ", addressEvent.getAddress().toString());
    }
}
