package com.andrei.user.event;

import com.andrei.user.model.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class UserEvent extends ApplicationEvent {

    private final String eventType;
    private final User user;

    public UserEvent(Object source, String eventType, User user) {
        super(source);
        this.eventType = eventType;
        this.user = user;
    }
}
