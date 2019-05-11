package com.andrei.user.event;

import com.andrei.contract.user.UserDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class UserEvent extends ApplicationEvent {

    private final String eventType;
    private final UserDTO userDTO;

    public UserEvent(Object source, String eventType, UserDTO userDTO) {
        super(source);
        this.eventType = eventType;
        this.userDTO = userDTO;
    }
}
