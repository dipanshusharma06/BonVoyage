package com.gl.app.userservice.client;

import com.gl.app.userservice.payload.PackageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "package-service",url = "http://localhost:8097/api/package")
public interface PackageClient {
    @GetMapping("/onepackage/{id}")
    public PackageDto getPackageById(@PathVariable String id);

}
