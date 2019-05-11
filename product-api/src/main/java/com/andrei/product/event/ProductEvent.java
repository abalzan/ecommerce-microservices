package com.andrei.product.event;

import com.andrei.contract.product.ProductDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class ProductEvent extends ApplicationEvent {

    private final String eventType;
    private final ProductDTO productDTO;

    public ProductEvent(String eventType, ProductDTO productDTO) {
        super(productDTO);
        this.eventType = eventType;
        this.productDTO = productDTO;
    }
}
