package com.example.task1.service;

import com.example.task1.client.BookClient;
import com.example.task1.dto.BookDto;
import com.example.task1.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final BookClient bookClient;

    public UserServiceImpl(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @Override
    public UserDto addFavoriteBook(String username, Long bookId) {
        BookDto bookDto = bookClient.getBookById(bookId);

        Set<BookDto> favorites = new HashSet<>();
        favorites.add(bookDto);

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setFavoriteBooks(favorites);

        return userDto;
    }

    @Override
    public UserDto removeFavoriteBook(String username, Long bookId) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setFavoriteBooks(new HashSet<>());
        return userDto;
    }

    @Override
    public UserDto findByUsername(String username) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setFavoriteBooks(new HashSet<>());
        return userDto;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return userDto;
    }

    @Override
    public Set<UserDto> findAll() {
        return new HashSet<>();
    }
}