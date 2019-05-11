package com.andrei.address.converter;

import com.andrei.address.model.Address;
import com.andrei.contract.address.AddressDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressDTOToEntityConverter implements Converter<AddressDTO, Address> {
    @Override
    public Address convert(AddressDTO source) {
        return Address.builder()
                .id(source.getId())
                .houseNumber(source.getHouseNumber())
                .streetAddress(source.getStreetAddress())
                .city(source.getCity())
                .state(source.getState())
                .zipCode(source.getZipCode())
                .build();
    }
}
