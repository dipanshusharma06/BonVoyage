package com.gl.app.userservice.repository;

import com.gl.app.userservice.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist,String> {
}
