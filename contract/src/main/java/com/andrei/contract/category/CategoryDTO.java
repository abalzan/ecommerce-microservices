package com.andrei.contract.category;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@JsonPropertyOrder({
        "categoryId",
        "categoryName",
        "categoryDescription"
})

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @JsonProperty("categoryId")
    private long id;

    @NotBlank(message = "Category description should not be null")
    @JsonProperty("categoryName")
    private String categoryName;

    @NotBlank(message = "Category name should not be null")
    @JsonProperty("categoryDescription")
    private String categoryDescription;
}
