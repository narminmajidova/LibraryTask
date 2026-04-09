package com.example.task1.service;

import com.example.task1.dto.UserDto;
import com.example.task1.client.BookClient;
import com.example.task1.model.User;
import com.example.task1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BookClient bookClient;

    public UserServiceImpl(UserRepository userRepository, BookClient bookClient) {
        this.userRepository = userRepository;
        this.bookClient = bookClient;
    }

    @Override
    @Transactional
    @Cacheable(value = "users", key = "#id", unless = "#result.favoriteBookList == null")
    public User getById(Long id) {
        log.info("GET user request received: {}", id);
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("User not found | id={}", id);
                        return new RuntimeException("User not found");
                    });

            log.debug("User fetched successfully | id={}", id);

            return user;

        } catch (Exception e) {
            log.error("Error fetching user | id={}", id, e);
            throw e;
        }
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