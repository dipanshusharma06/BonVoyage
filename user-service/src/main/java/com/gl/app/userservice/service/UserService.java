package com.gl.app.userservice.service;

import com.gl.app.userservice.entity.User;
import com.gl.app.userservice.payload.UserDto;

public interface UserService {
    public UserDto registerUser(UserDto userDto);
    public UserDto getUserByName(String name);
    public UserDto getUserByEmailAndPassword(String email, String password);
    public UserDto getUserById(String userId);
}
