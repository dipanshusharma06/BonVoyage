package com.gl.app.userservice.payload;


import com.gl.app.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WishlistDto {
    private String wishlistId;
    private List<String> packages;
}
