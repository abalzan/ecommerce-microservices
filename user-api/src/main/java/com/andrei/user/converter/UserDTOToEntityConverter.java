package com.andrei.user.converter;

import com.andrei.contract.user.UserDTO;
import com.andrei.user.model.Account;
import com.andrei.user.model.Address;
import com.andrei.user.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UserDTOToEntityConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(@NotNull UserDTO source) {

        Account account = Account.builder()
                .accountName(source.getAccount().getAccountName())
                .accountNumber(source.getAccount().getAccountNumber())
                .build();

        Address address = Address.builder()
                .id(source.getAddress().getId())
                .houseNumber(source.getAddress().getHouseNumber())
                .streetAddress(source.getAddress().getStreetAddress())
                .city(source.getAddress().getCity())
                .state(source.getAddress().getState())
                .zipCode(source.getAddress().getZipCode())
                .build();

        return User.builder()
                .id(source.getId())
                .username(source.getUsername())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .gender(source.getGender())
                .memberType(source.getMemberType())
                .registrationDate(source.getRegistrationDate())
                .account(account)
                .address(address)
                .build();
    }
}