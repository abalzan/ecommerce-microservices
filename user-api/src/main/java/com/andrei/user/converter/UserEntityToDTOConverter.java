package com.andrei.user.converter;

import com.andrei.contract.account.AccountDTO;
import com.andrei.contract.address.AddressDTO;
import com.andrei.contract.user.UserDTO;
import com.andrei.user.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UserEntityToDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(@NotNull User source) {

        AccountDTO accountDTO = AccountDTO.builder()
                .accountName(source.getAccount().getAccountName())
                .accountNumber(source.getAccount().getAccountNumber())
                .build();

        AddressDTO addressDTO = AddressDTO.builder()
                .id(source.getAddress().getId())
                .houseNumber(source.getAddress().getHouseNumber())
                .streetAddress(source.getAddress().getStreetAddress())
                .city(source.getAddress().getCity())
                .state(source.getAddress().getState())
                .zipCode(source.getAddress().getZipCode())
                .build();

        return UserDTO.builder()
                .id(source.getId())
                .username(source.getUsername())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .gender(source.getGender())
                .memberType(source.getMemberType())
                .registrationDate(source.getRegistrationDate())
                .account(accountDTO)
                .address(addressDTO)
                .build();
    }
}
