package com.gl.app.packageService.service;



import com.gl.app.packageService.payload.PackageDto;

import java.util.List;

public interface PackageService {
    public PackageDto createPackage (PackageDto packageDto);
    public List<PackageDto> getAllPackage();
    public List<PackageDto> getPackageByLocation(String city);
    public PackageDto getPackageById(String Id);



}
