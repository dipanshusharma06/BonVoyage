package com.gl.app.packageService.utils;


import com.gl.app.packageService.PackageServiceApplication;
import com.gl.app.packageService.entity.Package;
import com.gl.app.packageService.entity.PackageItenary;
import com.gl.app.packageService.payload.PackageDto;
import com.gl.app.packageService.payload.PackageItenaryDto;

public class Mapper {

    public static PackageDto mapToPackageDto(Package packageEntity){
        return PackageServiceApplication.modelMapper().map(packageEntity, PackageDto.class);
    }


    public static Package mapToPackageEntity(PackageDto packageDto){
        return PackageServiceApplication.modelMapper().map(packageDto, Package.class);
    }
    public static PackageItenaryDto mapToPackageItenaryDto(PackageItenary packageItenary){
        return PackageServiceApplication.modelMapper().map(packageItenary, PackageItenaryDto.class);
    }
    public static PackageItenary mapToPackageItenaryEntity(PackageItenaryDto packageItenaryDto){
        return PackageServiceApplication.modelMapper().map(packageItenaryDto, PackageItenary.class);
    }

}
