package com.gl.app.packageService.service.impl;



import com.gl.app.packageService.PackageServiceApplication;
import com.gl.app.packageService.entity.Package;
import com.gl.app.packageService.exception.ResourceNotFoundException;
import com.gl.app.packageService.payload.PackageDto;
import com.gl.app.packageService.repository.PackageRepository;
import com.gl.app.packageService.service.PackageService;
import com.gl.app.packageService.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;
    @Override
    public PackageDto createPackage(PackageDto packageDto) {


        Package p1 = Mapper.mapToPackageEntity(packageDto);
        p1.setPackageID(PackageServiceApplication.genId());
        return Mapper.mapToPackageDto(packageRepository.save(p1));
    }

    @Override
    public List<PackageDto> getAllPackage() {

        return packageRepository.findAll().stream().map((e)->
            Mapper.mapToPackageDto(e)
        ).toList();
    }

    @Override
    public List<PackageDto> getPackageByLocation(String country) {
        List<Package> packageList = packageRepository.getByPackageCountry(country);
        return packageList.stream().map(Mapper::mapToPackageDto).toList();
    }

    @Override
    public PackageDto getPackageById(String Id) {
        Package p2 = packageRepository.findById(Id).orElseThrow(
                ()-> new ResourceNotFoundException("Package","ID",Id)
        );
        return Mapper.mapToPackageDto(p2);
    }
}
