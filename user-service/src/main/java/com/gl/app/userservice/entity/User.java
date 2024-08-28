package com.gl.app.userservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gl.app.userservice.utils.UserUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    private String id;

    @Column(name = "userName", unique = true, nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    private String role;

    @OneToMany(mappedBy = "user",orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Wishlist> wishlist;

    @PrePersist
    private void generateId() {
        if (this.id == null) {
            this.id = UserUtils.generateUserId("U");
        }
    }
}
