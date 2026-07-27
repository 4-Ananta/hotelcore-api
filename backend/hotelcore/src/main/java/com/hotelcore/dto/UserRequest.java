package com.hotelcore.dto;

import com.hotelcore.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "Username tidak boleh kosong")
    private String username;

    @NotBlank(message = "Password tidak boleh kosong")
    private String password;

    @NotBlank(message = "Full Name tidak boleh kosong")
    private String fullName;

    @NotNull(message = "Role tidak boleh kosong")
    private UserRole role;
}
