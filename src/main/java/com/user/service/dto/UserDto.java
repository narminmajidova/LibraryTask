package com.user.service.dto;

import com.user.service.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
    private String name;
    private String surname;
    private Role role;
    private List<Long> favoriteBookIds = new ArrayList<>();

}