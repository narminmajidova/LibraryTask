package com.example.task1.service;


import com.example.task1.dto.UserDto;
import com.example.task1.model.User;
import jakarta.persistence.Cacheable;

import java.util.Set;

public interface UserService {

//    @Cacheable(value = "users", key = "#id")
    User getById(Long id);

    UserDto addFavoriteBook(String username, Long bookId);

    UserDto removeFavoriteBook(String username, Long bookId);

    UserDto findByUsername(String username);

    UserDto createUser(UserDto userDto);

    Set<UserDto> findAll();
}