package com.gl.app.packageService.payload;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageDto {

    private String packageID;
    private String packageCountry;
    private String packageCity;
    private String packageName;
    private String packageDesc;
    private int packageRating;
    private int packageReviews;
    private String packagePrice;
    private String packageDuration;
    private String minAge;
    private int maxPeople;
    private String packagePickup;
    private String availableDate;
    private String packageLanguage;
    private  List<PackageItenaryDto> packageItenaryList;
    private String packageImage;
}