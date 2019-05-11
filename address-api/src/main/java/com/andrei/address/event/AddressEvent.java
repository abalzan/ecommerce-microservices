package com.andrei.address.event;

import com.andrei.contract.address.AddressDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class AddressEvent extends ApplicationEvent {

    private final String eventType;
    private final AddressDTO addressDTO;

    public AddressEvent(Object source, String eventType, AddressDTO addressDTO) {
        super(source);
        this.eventType = eventType;
        this.addressDTO = addressDTO;
    }
}
