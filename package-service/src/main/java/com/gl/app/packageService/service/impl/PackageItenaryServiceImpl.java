package com.gl.app.packageService.service.impl;


import com.gl.app.packageService.entity.Package;
import com.gl.app.packageService.entity.PackageItenary;
import com.gl.app.packageService.exception.ResourceNotFoundException;
import com.gl.app.packageService.payload.PackageItenaryDto;
import com.gl.app.packageService.repository.PackageItenaryRepository;
import com.gl.app.packageService.repository.PackageRepository;
import com.gl.app.packageService.service.PackageItenaryService;
import com.gl.app.packageService.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageItenaryServiceImpl implements PackageItenaryService {
    private final PackageItenaryRepository packageItenaryRepository;
    private final PackageRepository packageRepository;
    @Override
    public PackageItenaryDto createPackageItenary(PackageItenaryDto packageItenaryDto, String PackId) {
        Package p1 = packageRepository.findById(PackId).orElseThrow(
                ()-> new ResourceNotFoundException("Package","PackId",PackId)
        );
        PackageItenary packageItenary= Mapper.mapToPackageItenaryEntity(packageItenaryDto);
        packageItenary.setPack(p1);
        return Mapper.mapToPackageItenaryDto(packageItenaryRepository.save(packageItenary));
    }
}
