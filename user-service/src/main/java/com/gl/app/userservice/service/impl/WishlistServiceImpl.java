package com.gl.app.userservice.service.impl;

import com.gl.app.userservice.client.PackageClient;
import com.gl.app.userservice.entity.User;
import com.gl.app.userservice.entity.Wishlist;
import com.gl.app.userservice.exception.ApiNotFoundException;
import com.gl.app.userservice.exception.ResourceNotFoundException;
import com.gl.app.userservice.payload.*;
import com.gl.app.userservice.repository.UserRepository;
import com.gl.app.userservice.repository.WishlistRepository;
import com.gl.app.userservice.service.WishlistService;
import com.gl.app.userservice.utils.Mapper;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final PackageClient packageClient;
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    private User checkAndGetUser(String userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "userId", userId)
        );
    }

    private Wishlist checkAndGetWishlist(String userId, String wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(
                () -> new ResourceNotFoundException("Wishlist", "wishlistId", wishlistId)
        );
        if (!wishlist.getUser().getId().equals(userId)) {
            throw new ApiNotFoundException("User", "Wishlist", "wishlistId", wishlistId);
        }
        return wishlist;
    }

    @Override
    public WishlistDto createWishlist(String userId, WishlistDto wishlistDto) {
        User user = checkAndGetUser(userId);
        Wishlist wishlist = mapper.mapToWishlist(wishlistDto);
        wishlist.setUser(user);
        wishlist.setPackages(new ArrayList<>());
        wishlist= wishlistRepository.save(wishlist);
        return mapper.mapToWishlistDto(wishlist);
    }

    @Override
    public ApiResponseDto getWishlist(String userId, String wishlistId) {
        checkAndGetUser(userId);
        Wishlist wishlist = checkAndGetWishlist(userId, wishlistId);

        List<PackageDto> packageDtoList = new ArrayList<>();
        wishlist.getPackages().forEach(packageId -> {
            try {
                packageDtoList.add(packageClient.getPackageById(packageId));
            } catch (FeignException.NotFound e) {
                // Handle the case where the package is not found
                throw new ResourceNotFoundException("Package", "packageId", packageId);
            }
        });

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setStatus("success");
        apiResponseDto.setResults(packageDtoList.size()+"");
        apiResponseDto.setData(packageDtoList);
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto updateWishlist(String userId, WishlistDto wishlistDto) {
        checkAndGetUser(userId);
        Wishlist wishlist = checkAndGetWishlist(userId, wishlistDto.getWishlistId());

        wishlist.setPackages(wishlistDto.getPackages());
        wishlistRepository.save(wishlist);

        List<PackageDto> packageDtoList = new ArrayList<>();
        wishlist.getPackages().forEach(packageId -> {
            try {
                packageDtoList.add(packageClient.getPackageById(packageId));
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Package", "packageId", packageId);
            }
        });

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setStatus("success");
        apiResponseDto.setResults(packageDtoList.size()+"");
        apiResponseDto.setData(packageDtoList);

        return apiResponseDto;
    }

    @Override
    public String deletePackageWishlist(String userId, String wishlistId, String packageId) {
        checkAndGetUser(userId);
        Wishlist wishlist = checkAndGetWishlist(userId, wishlistId);
        if (!wishlist.getPackages().remove(packageId)) {
            throw new ResourceNotFoundException("Package", "packageId", packageId);
        }
        wishlistRepository.save(wishlist);
        return "Package removed successfully";
    }

    @Override
    public String addPackageToWishlist(String userId, String wishlistId, String packageId) {
        checkAndGetUser(userId);
        Wishlist wishlist = checkAndGetWishlist(userId, wishlistId);
        wishlist.getPackages().add(packageId);
        wishlistRepository.save(wishlist);
        return "Package added successfully";
    }
}
