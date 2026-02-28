package com.example.task1.service;

import com.example.task1.dto.UserDto;
import com.example.task1.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto addFavoriteBook(String username, Long bookId);

    UserDto removeFavoriteBook(String username, Long bookId);

    UserDto findByUsername(String username);

    List<UserDto> findAll();
}