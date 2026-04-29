package com.user.service.service;

import com.user.service.dto.UserDto;
import com.user.service.model.User;
import com.user.service.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @Cacheable(value = "users", key = "#id", unless = "#result.favoriteBookIds == null")
    public User getById(Long id) {
        log.info("GET user request received: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found | id={}", id);
                    return new RuntimeException("User not found");
                });
    }

    @Override
    @Transactional
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return toDto(user);
    }

    @Override
    public UserDto createUser(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setRole(dto.getRole());
        user.setIsActive(true);
        user.setMembershipStartDate(LocalDate.now());

        User saved = userRepository.save(user);
        return toDto(saved);
    }

    @Override
    @Transactional
    public UserDto addFavoriteBook(String username, Long bookId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        if (!user.getFavoriteBookIds().contains(bookId)) {
            user.getFavoriteBookIds().add(bookId);
            userRepository.save(user);
            log.info("Favorite added | username={}, bookId={}", username, bookId);
        }

        return toDto(user);
    }

    @Override
    @Transactional
    public UserDto removeFavoriteBook(String username, Long bookId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        user.getFavoriteBookIds().remove(bookId);
        userRepository.save(user);
        log.info("Favorite removed | username={}, bookId={}", username, bookId);

        return toDto(user);
    }

    @Override
    public Set<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow();

        System.out.println("DEBUG password from DB: [" + user.getPassword() + "]");

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setRole(user.getRole());
        dto.setFavoriteBookIds(user.getFavoriteBookIds());
        return dto;
    }
}