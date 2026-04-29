package com.user.service.service;


import com.user.service.dto.UserDto;
import com.user.service.model.User;

import java.util.Set;

public interface UserService {

    User getById(Long id);

    UserDto addFavoriteBook(String username, Long bookId);

    UserDto removeFavoriteBook(String username, Long bookId);

    UserDto findByUsername(String username);

    UserDto createUser(UserDto userDto);

    Set<UserDto> findAll();
}