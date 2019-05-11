package com.andrei.category.event;

import com.andrei.contract.category.CategoryDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class CategoryEvent extends ApplicationEvent {

    private final String eventType;
    private final CategoryDTO categoryDTO;

    public CategoryEvent(String eventType, CategoryDTO categoryDTO) {
        super(categoryDTO);
        this.eventType = eventType;
        this.categoryDTO = categoryDTO;
    }
}
