package com.gl.app.userservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gl.app.userservice.utils.UserUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    private String wishlistId;
    private List<String> packages;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private User user;

    @PrePersist
    private void generateId() {
        if (this.wishlistId == null) {
            this.wishlistId = UserUtils.generateWishlistId("W");
        }
    }
}
