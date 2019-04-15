package com.andrei.user.service;

import com.andrei.user.exception.ExceptionConstants;
import com.andrei.user.model.User;
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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Page<User> getUserByPage(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("username").descending());

        return userRepository.findAll(pageable);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESOURCE_NOT_FOUND));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
