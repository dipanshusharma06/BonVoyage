package com.gl.app.userservice.repository;

import com.gl.app.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmailAndPassword(String email,String password);
    Optional<User> findByName(String name);
}
