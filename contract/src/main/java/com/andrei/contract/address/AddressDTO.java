package com.andrei.contract.address;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@JsonPropertyOrder({
        "addressId",
        "houseNumber",
        "streetAddress",
        "city",
        "state",
        "zipCode"
})

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @JsonProperty("addressId")
    private long id;

    @NotBlank(message = "House Number should not be null")
    @JsonProperty("houseNumber")
    private String houseNumber;

    @NotBlank(message = "Street Address should not be null")
    @JsonProperty("streetAddress")
    private String streetAddress;

    @NotBlank(message = "City should not be null")
    @JsonProperty("city")
    private String city;

    @NotBlank(message = "State should not be null")
    @JsonProperty("state")
    private String state;

    @NotBlank(message = "Member type should not be null")
    @JsonProperty("zipCode")
    private String zipCode;

}
