package com.gl.app.userservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor

public class ApiResponseDto {
    private String status;
    private String results;
    private List<PackageDto> data;
}
