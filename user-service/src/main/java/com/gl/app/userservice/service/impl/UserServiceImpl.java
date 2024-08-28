package com.gl.app.userservice.service.impl;

import com.gl.app.userservice.entity.User;
import com.gl.app.userservice.exception.ResourceNotFoundException;
import com.gl.app.userservice.payload.UserDto;
import com.gl.app.userservice.repository.UserRepository;
import com.gl.app.userservice.service.UserService;
import com.gl.app.userservice.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper mapper;



    @Override
    public UserDto registerUser(UserDto userDto) {
        User user= mapper.mapToUserEntity(userDto);
        user= userRepository.save(user);
        return mapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserByName(String name) {
        User user = userRepository.findByName(name).orElseThrow(
                ()-> new ResourceNotFoundException("User","name",name)
        );
        return mapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) {
        User user= userRepository.findByEmailAndPassword(email,password).orElseThrow(
                ()-> new ResourceNotFoundException("User","email",email)
        );
        return mapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "userId", userId)
        );
        return mapper.mapToUserDto(user);
    }
}
