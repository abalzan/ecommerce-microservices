package com.andrei.address.listener;

import com.andrei.address.event.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventListener {

    @EventListener
    public void onApplicationEvent(UserEvent userEvent){

        log.info("Received User Event:{} ", userEvent.getEventType());
        log.info("Receive User From User Event: {} ", userEvent.getUser().toString());
    }
}
