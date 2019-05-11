package com.andrei.address.service;

import com.andrei.address.converter.AddressDTOToEntityConverter;
import com.andrei.address.converter.AddressEntityToDTOConverter;
import com.andrei.address.exception.ExceptionConstants;
import com.andrei.address.model.Address;
import com.andrei.address.repository.AddressRepository;
import com.andrei.contract.address.AddressDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Slf4j
@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressEntityToDTOConverter entityToDTOConverter;
    private final AddressDTOToEntityConverter dtoToEntityConverter;

    public AddressService(AddressRepository addressRepository, AddressEntityToDTOConverter entityToDTOConverter, AddressDTOToEntityConverter dtoToEntityConverter) {
        this.addressRepository = addressRepository;
        this.entityToDTOConverter = entityToDTOConverter;
        this.dtoToEntityConverter = dtoToEntityConverter;
    }

    public AddressDTO save(AddressDTO addressDTO) {
        final Address savedAddress = addressRepository.save(Objects.requireNonNull(dtoToEntityConverter.convert(addressDTO)));

        return entityToDTOConverter.convert(savedAddress);
    }

    public Page<AddressDTO> getAddressByPage(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("houseNumber").descending());

        return addressRepository.findAll(pageable).map(entityToDTOConverter::convert);
    }

    public AddressDTO getAddress(long addressId) {
        final Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESOURCE_NOT_FOUND));
        return entityToDTOConverter.convert(address);
    }

    public void deleteAddress(long addressId) {
        addressRepository.deleteById(addressId);
    }

}
