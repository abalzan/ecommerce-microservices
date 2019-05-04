package com.andrei.address.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "House Number should not be null")
    @Column(name = "HOUSE_NUMBER", nullable = false)
    private String houseNumber;

    @NotBlank(message = "Street Address should not be null")
    @Column(name = "STREET_ADDRESS", nullable = false)
    private String streetAddress;

    @NotBlank(message = "City should not be null")
    @Column(name = "CITY", nullable = false)
    private String city;

    @NotBlank(message = "State should not be null")
    @Column(name = "STATE", nullable = false)
    private String state;

    @NotBlank(message = "Member type should not be null")
    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;

}
