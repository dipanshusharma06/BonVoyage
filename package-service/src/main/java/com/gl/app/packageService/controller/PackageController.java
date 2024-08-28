package com.gl.app.packageService.controller;


import com.gl.app.packageService.payload.PackageDto;
import com.gl.app.packageService.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/package")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }
    @PostMapping("/create")
    public ResponseEntity<PackageDto> createPackage(@Valid @RequestBody PackageDto packageDto){
        PackageDto packageDto1 = packageService.createPackage(packageDto);
        return new ResponseEntity<>(packageDto1, HttpStatus.CREATED);
    }
    @GetMapping
    public List<PackageDto> getAllPackage(){
        return packageService.getAllPackage();
    }
    @GetMapping("/onepackage/{id}")
    public PackageDto getPackageById(@PathVariable String id){ return packageService.getPackageById(id);}

    @GetMapping("/{country}")
    public List<PackageDto> getPackageByLocation(@PathVariable String country){
        return packageService.getPackageByLocation(country);
    }


}
