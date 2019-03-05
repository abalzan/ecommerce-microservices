package com.andrei.category.event;

import com.andrei.category.model.Category;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class CategoryEvent extends ApplicationEvent {

    private final String eventType;
    private final Category category;

    public CategoryEvent(String eventType, Category category) {
        super(category);
        this.eventType = eventType;
        this.category = category;
    }
}
