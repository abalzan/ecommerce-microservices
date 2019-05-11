package com.andrei.user.service;

import com.andrei.contract.user.UserDTO;
import com.andrei.user.converter.UserDTOToEntityConverter;
import com.andrei.user.converter.UserEntityToDTOConverter;
import com.andrei.user.exception.ExceptionConstants;
import com.andrei.user.model.User;
import com.andrei.user.repository.AccountRepository;
import com.andrei.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final UserDTOToEntityConverter dtoToEntityConverter;
    private final UserEntityToDTOConverter entityToDTOConverter;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, UserDTOToEntityConverter dtoToEntityConverter, UserEntityToDTOConverter entityToDTOConverter) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.entityToDTOConverter = entityToDTOConverter;
    }

    public UserDTO save(UserDTO userDTO) {

        final User user = dtoToEntityConverter.convert(userDTO);

        accountRepository.save(user.getAccount());
        final User savedUser = userRepository.save(user);

        return entityToDTOConverter.convert(savedUser);
    }

    public Page<UserDTO> getUserByPage(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("username").descending());

        return userRepository.findAll(pageable).map(entityToDTOConverter::convert);
    }

    public UserDTO getUser(Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESOURCE_NOT_FOUND));

        return entityToDTOConverter.convert(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
