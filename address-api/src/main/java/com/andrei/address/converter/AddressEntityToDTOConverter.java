package com.andrei.address.converter;

import com.andrei.address.model.Address;
import com.andrei.contract.address.AddressDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityToDTOConverter implements Converter<Address, AddressDTO> {
    @Override
    public AddressDTO convert(Address source) {
        return AddressDTO.builder()
                .id(source.getId())
                .houseNumber(source.getHouseNumber())
                .streetAddress(source.getStreetAddress())
                .city(source.getCity())
                .state(source.getState())
                .zipCode(source.getZipCode())
                .build();
    }
}
