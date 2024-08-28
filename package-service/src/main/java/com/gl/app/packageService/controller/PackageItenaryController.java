package com.gl.app.packageService.controller;

import com.gl.app.packageService.payload.PackageItenaryDto;
import com.gl.app.packageService.service.PackageItenaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/packageItenary/{packID}")
public class PackageItenaryController {
    private final PackageItenaryService packageItenaryService;

    public PackageItenaryController(PackageItenaryService packageItenaryService) {
        this.packageItenaryService = packageItenaryService;
    }
    @PostMapping
    public ResponseEntity<PackageItenaryDto> createPackageItenary(@RequestBody PackageItenaryDto packageItenaryDto, @PathVariable String packID){
        PackageItenaryDto packageItenaryDto1 = packageItenaryService.createPackageItenary(packageItenaryDto,packID);
        return new ResponseEntity<>(packageItenaryDto, HttpStatus.CREATED);
    }
}
