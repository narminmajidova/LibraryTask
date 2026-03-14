package com.example.task1.service;


import com.example.task1.dto.UserDto;
import java.util.Set;

public interface UserService {

    UserDto addFavoriteBook(String username, Long bookId);

    UserDto removeFavoriteBook(String username, Long bookId);

    UserDto findByUsername(String username);

    UserDto createUser(UserDto userDto);

    Set<UserDto> findAll();
}