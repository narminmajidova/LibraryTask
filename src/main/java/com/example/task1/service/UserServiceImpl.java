package com.example.task1.service;

import com.example.task1.dto.UserDto;
import com.example.task1.client.BookClient;
import com.example.task1.model.User;
import com.example.task1.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BookClient bookClient;

    public UserServiceImpl(UserRepository userRepository, BookClient bookClient) {
        this.userRepository = userRepository;
        this.bookClient = bookClient;
    }

    @Cacheable(value = "users", key = "#id", unless = "#result.favoriteBookList == null")
    @Override
    @Transactional
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public UserDto addFavoriteBook(String username, Long bookId) {
        return null;
    }

    @Override
    public UserDto removeFavoriteBook(String username, Long bookId) {
        return null;
    }

    @Override
    public UserDto findByUsername(String username) {
        return new UserDto();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return userDto;
    }

    @Override
    public Set<UserDto> findAll() {
        return new HashSet<>();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password("")
                .authorities("USER")
                .build();
    }
}