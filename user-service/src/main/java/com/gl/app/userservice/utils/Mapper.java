package com.gl.app.userservice.utils;

import com.gl.app.userservice.entity.User;
import com.gl.app.userservice.entity.Wishlist;
import com.gl.app.userservice.payload.UserDto;
import com.gl.app.userservice.payload.WishlistDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@AllArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    public User mapToUserEntity(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    public UserDto mapToUserDto(User user){
        return modelMapper.map(user,UserDto.class);
    }

    public Wishlist mapToWishlist(WishlistDto wishlistDto){
        return modelMapper.map(wishlistDto, Wishlist.class);
    }

    public WishlistDto mapToWishlistDto(Wishlist wishlist){
        return modelMapper.map(wishlist,WishlistDto.class);
    }
}
