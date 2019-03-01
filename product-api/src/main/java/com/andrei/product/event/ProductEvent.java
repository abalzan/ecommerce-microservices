package com.andrei.product.event;

import com.andrei.product.model.Product;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class ProductEvent extends ApplicationEvent {

    private final String eventType;
    private final Product product;

    public ProductEvent(String eventType, Product product) {
        super(product);
        this.eventType = eventType;
        this.product = product;
    }
}
