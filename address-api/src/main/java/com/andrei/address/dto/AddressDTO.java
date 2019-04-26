package com.andrei.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private long id;

    private String houseNumber;

    private String streetAddress;

    private String city;

    private String state;

    private String zipCode;

    private long accountId;

}
