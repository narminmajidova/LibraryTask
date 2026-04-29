package com.user.service.controller;

import com.user.service.dto.UserDto;
import com.user.service.model.User;
import com.user.service.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping("/{username}/favorite/{bookId}")
    public UserDto addFavorite(@PathVariable String username,
                               @PathVariable Long bookId) {
        return userService.addFavoriteBook(username, bookId);
    }

    @DeleteMapping("/{username}/favorite/{bookId}")
    public UserDto removeFavorite(@PathVariable String username,
                                  @PathVariable Long bookId) {
        return userService.removeFavoriteBook(username, bookId);
    }

    @GetMapping("/{username}")
    public UserDto findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable Long id) {
        return userService.getById(id);
    }
}