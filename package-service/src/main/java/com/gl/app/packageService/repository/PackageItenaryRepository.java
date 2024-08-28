package com.gl.app.packageService.repository;


import com.gl.app.packageService.entity.PackageItenary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageItenaryRepository extends JpaRepository<PackageItenary,String> {
}
