package com.gl.app.packageService.repository;

import com.gl.app.packageService.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package,String> {
    public List<Package> getByPackageCountry(String country);
}
