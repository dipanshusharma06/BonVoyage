package com.gl.app.packageService.service;


import com.gl.app.packageService.payload.PackageItenaryDto;

public interface PackageItenaryService {
    public PackageItenaryDto createPackageItenary(PackageItenaryDto packageItenaryDto, String PackId);
}
