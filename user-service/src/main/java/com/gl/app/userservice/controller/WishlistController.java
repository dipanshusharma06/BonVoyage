package com.gl.app.userservice.controller;

import com.gl.app.userservice.payload.ApiResponseDto;
import com.gl.app.userservice.payload.WishlistDto;
import com.gl.app.userservice.service.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/wishlists/{userId}")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    @PostMapping
    public ResponseEntity<WishlistDto> createWishlist(@PathVariable String userId){
        return new ResponseEntity<>(wishlistService.createWishlist(userId,new WishlistDto()), HttpStatus.CREATED);
    }

    @PostMapping("/{wishlistId}/{packageId}")
    ResponseEntity<String> addPackageToWishlist(@PathVariable String userId,
                                                @PathVariable String wishlistId,
                                                @PathVariable String packageId){
        return ResponseEntity.ok(wishlistService.addPackageToWishlist(userId,wishlistId,packageId));
    }

    @DeleteMapping("/{wishlistId}/{packageId}")
    ResponseEntity<String> deletePackageWishlist(@PathVariable String userId,
                                                @PathVariable String wishlistId,
                                                @PathVariable String packageId){
        return ResponseEntity.ok(wishlistService.deletePackageWishlist(userId,wishlistId,packageId));
    }

//    @PutMapping("/{wishlistId}")
//    public ResponseEntity<ApiResponseDto> updateWishlist(@PathVariable String wishlistId,
//                                                         @PathVariable String userId,
//                                                         @RequestBody WishlistDto wishlistDto){
//        return ResponseEntity.ok(wishlistService.updateWishlist(wishlistDto));
//    }

    @GetMapping("/{wishlistId}")
    public ResponseEntity<ApiResponseDto> getWishlist(@PathVariable String userId,@PathVariable String wishlistId){
        return ResponseEntity.ok(wishlistService.getWishlist(userId,wishlistId));
    }


}
