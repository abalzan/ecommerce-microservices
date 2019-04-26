package com.andrei.address.event;

import com.andrei.address.model.Address;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class AddressEvent extends ApplicationEvent {

    private final String eventType;
    private final Address address;

    public AddressEvent(Object source, String eventType, Address address) {
        super(source);
        this.eventType = eventType;
        this.address = address;
    }
}
