package com.andrei.address.controller;

import com.andrei.address.event.AddressEvent;
import com.andrei.address.model.Address;
import com.andrei.address.service.AddressService;
import com.andrei.address.service.ValidationErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class AddressController extends AbstractController{

	private final AddressService addressService;
	private final ValidationErrorService validationErrorService;

	public AddressController(AddressService addressService, ValidationErrorService validationErrorService) {
		this.addressService = addressService;
		this.validationErrorService = validationErrorService;
	}

	@PostMapping("/addresses")
	public ResponseEntity<?> createAddress(@Valid @RequestBody Address address, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		Address savedAddress = addressService.save(address);

		AddressEvent addressCreatedEvent = new AddressEvent(this, "AddressCreatedEvent", savedAddress);
		applicationEventPublisher.publishEvent(addressCreatedEvent);

		return ResponseEntity.ok().body("New Address has been saved with ID:" + savedAddress.getId());
	}

	@GetMapping("/addresses/{id}")
	public ResponseEntity<Address> getAddress(@PathVariable("id") long id) {
		Address address = addressService.getAddress(id);

		AddressEvent addressRetrievedEvent = new AddressEvent(this,"AddressRetrievedEvent", address);
		applicationEventPublisher.publishEvent(addressRetrievedEvent);

		return ResponseEntity.ok().body(address);
	}

	@GetMapping("/addresses")
	public @ResponseBody Page<Address> getAddresssByPage(
			@RequestParam(value="pagenumber", defaultValue=DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(value="pagesize", defaultValue=DEFAULT_PAGE_SIZE) Integer pageSize) {

		return addressService.getAddressByPage(pageNumber, pageSize);
	}

	@PutMapping("/addresses/{id}")
	public ResponseEntity<?> updateAddress(@Valid @PathVariable("id") long id, @RequestBody Address address, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		addressService.getAddress(id);
		addressService.save(address);

		AddressEvent addressUpdatedEvent = new AddressEvent(this, "AddressUpdatedEvent", address);
		applicationEventPublisher.publishEvent(addressUpdatedEvent);

		return ResponseEntity.ok().body("Address has been updated successfully.");
	}

	@DeleteMapping("/addresses/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable("id") long id) {
        addressService.getAddress(id);

		addressService.deleteAddress(id);
		return ResponseEntity.ok().body("Address has been deleted successfully.");
	}
}
