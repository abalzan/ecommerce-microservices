package com.andrei.address.controller;

import com.andrei.address.event.UserEvent;
import com.andrei.address.model.User;
import com.andrei.address.service.UserService;
import com.andrei.address.service.ValidationErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class UserController extends AbstractController{

	private final UserService userService;
	private final ValidationErrorService validationErrorService;

	public UserController(UserService userService, ValidationErrorService validationErrorService) {
		this.userService = userService;
		this.validationErrorService = validationErrorService;
	}

	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		User savedUser = userService.save(user);

		UserEvent userCreatedEvent = new UserEvent(this, "UserCreatedEvent", savedUser);
		applicationEventPublisher.publishEvent(userCreatedEvent);

		return ResponseEntity.ok().body("New User has been saved with ID:" + savedUser.getId());
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		User user = userService.getUser(id);

		UserEvent userRetrievedEvent = new UserEvent(this,"UserRetrievedEvent", user);
		applicationEventPublisher.publishEvent(userRetrievedEvent);

		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/users")
	public @ResponseBody Page<User> getUsersByPage(
			@RequestParam(value="pagenumber", defaultValue=DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(value="pagesize", defaultValue=DEFAULT_PAGE_SIZE) Integer pageSize) {

		return userService.getUserByPage(pageNumber, pageSize);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@Valid @PathVariable("id") long id, @RequestBody User user, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		userService.getUser(id);
		userService.save(user);

		UserEvent userUpdatedEvent = new UserEvent(this, "UserUpdatedEvent", user);
		applicationEventPublisher.publishEvent(userUpdatedEvent);

		return ResponseEntity.ok().body("User has been updated successfully.");
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        userService.getUser(id);

		userService.deleteUser(id);
		return ResponseEntity.ok().body("User has been deleted successfully.");
	}
}
