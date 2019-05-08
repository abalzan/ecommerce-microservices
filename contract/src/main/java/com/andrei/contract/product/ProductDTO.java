package com.andrei.contract.product;

import com.andrei.contract.category.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@JsonPropertyOrder({
        "productId",
        "productCode",
        "productName",
        "shortDescription",
        "longDescription",
        "canDisplay",
        "isDeleted",
        "isAutomotive",
        "isInternational",
        "parentCategory",
        "category"
})

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @JsonProperty("productId")
    private long id;

    @NotBlank(message = "product code should not be null")
    @JsonProperty("productCode")
    private String productCode;

    @NotBlank(message = "product name should not be null")
    @JsonProperty("productName")
    private String productName;

    @NotBlank(message = "Short description code should not be null")
    @JsonProperty("shortDescription")
    private String shortDescription;

    @NotBlank(message = "Long description should not be null")
    @JsonProperty("longDescription")
    private String longDescription;

    @JsonProperty("canDisplay")
    private boolean canDisplay;

    @JsonProperty("isDeleted")
    private boolean isDeleted;

    @JsonProperty("isAutomotive")
    private boolean isAutomotive;

    @JsonProperty("isInternational")
    private boolean isInternational;

    @JsonProperty("parentCategory")
    private CategoryDTO parentCategory;

    @JsonProperty("category")
    private CategoryDTO category;

}
