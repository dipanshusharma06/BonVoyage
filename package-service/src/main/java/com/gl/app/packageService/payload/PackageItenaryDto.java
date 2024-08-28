package com.gl.app.packageService.payload;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageItenaryDto {
    private String day;
    private String title;
    private String activities;

}
