package com.andrei.user.controller;

import com.andrei.contract.user.UserDTO;
import com.andrei.user.event.UserEvent;
import com.andrei.user.service.UserService;
import com.andrei.user.service.ValidationErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController extends AbstractController{

	private final UserService userService;
	private final ValidationErrorService validationErrorService;

	public UserController(UserService userService, ValidationErrorService validationErrorService) {
		this.userService = userService;
		this.validationErrorService = validationErrorService;
	}

	@PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

        UserDTO savedUser = userService.save(user);

		UserEvent userCreatedEvent = new UserEvent(this, "UserCreatedEvent", savedUser);
		applicationEventPublisher.publishEvent(userCreatedEvent);

		return ResponseEntity.ok().body("New User has been saved with ID:" + savedUser.getId());
	}

	@GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") long id) {
        UserDTO userDTO = userService.getUser(id);

        UserEvent userRetrievedEvent = new UserEvent(this, "UserRetrievedEvent", userDTO);
		applicationEventPublisher.publishEvent(userRetrievedEvent);

        return ResponseEntity.ok().body(userDTO);
	}

	@GetMapping("/users")
    public @ResponseBody
    Page<UserDTO> getUsersByPage(
			@RequestParam(value="pagenumber", defaultValue=DEFAULT_PAGE_NUMBER) Integer pageNumber,
			@RequestParam(value="pagesize", defaultValue=DEFAULT_PAGE_SIZE) Integer pageSize) {

		return userService.getUserByPage(pageNumber, pageSize);
	}

	@PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable("id") long id, @RequestBody UserDTO userDTO, BindingResult result) {

		ResponseEntity<?> responseEntity = validationErrorService.validationErrorService(result);

		if (responseEntity != null) return responseEntity;

		userService.getUser(id);
        userService.save(userDTO);

        UserEvent userUpdatedEvent = new UserEvent(this, "UserUpdatedEvent", userDTO);
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
