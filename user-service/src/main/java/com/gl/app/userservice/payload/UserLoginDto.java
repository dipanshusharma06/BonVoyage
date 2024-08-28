package com.gl.app.userservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserLoginDto {
    @NotNull(message = "Email cannot be null")
    @Email(message = "Must be a valid email")
    private String email;
    @NotNull(message = "Password cannot be null or empty")
    private String password;
}
