package com.gl.app.userservice.service;

import com.gl.app.userservice.payload.ApiResponseDto;
import com.gl.app.userservice.payload.WishlistDto;

public interface WishlistService {
    public WishlistDto createWishlist(String userId,WishlistDto wishlistDto);
    public ApiResponseDto getWishlist(String userId,String wishlistId);
    public ApiResponseDto updateWishlist(String userId, WishlistDto wishlistDto);
    public String deletePackageWishlist(String userId, String wishlistId, String packageId);
    public String addPackageToWishlist(String userId,String wishlistId,String packageId);
}
