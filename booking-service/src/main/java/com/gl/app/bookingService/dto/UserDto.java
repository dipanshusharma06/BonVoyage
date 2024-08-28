package com.gl.app.bookingService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private String id;

    @NotNull(message="Name cannot be null or empty")
    private String name;

    @NotNull(message="Email cannot be null or empty")
    @Email(message= "Must be a valid email")
    private String email;

    @NotNull(message="Password cannot be null or empty")
    private String password;

    @NotNull(message="Phone cannot be null or empty")
    private String phone;

    @NotNull(message="Address cannot be null or empty")
    private String address;

    private String role;
    private List<WishlistDto> wishlist;
}
