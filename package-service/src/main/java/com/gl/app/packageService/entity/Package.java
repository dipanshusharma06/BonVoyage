package com.gl.app.packageService.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "packages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    @Id
    private String packageID;
    @Column(nullable = false)
    private String packageCountry;
    @Column(nullable = false)
    private String packageCity;
    @Column(nullable = false, unique = true)
    private String packageName;
    @Column(nullable = false)
    private String packageDesc;
    @Column(nullable = false)
    private int packageRating;
    @Column(nullable = false)
    private int packageReviews;
    @Column(nullable = false)
    private String packagePrice;
    @Column(nullable = false)
    private String packageDuration;
    @Column(nullable = false)
    private String minAge;
    @Column(nullable = false)
    private int maxPeople;
    @Column(nullable = false)
    private String packagePickup;
    @Column(nullable = false)
    private String availableDate;
    @Column(nullable = false)
    private String packageLanguage;
    @Column(nullable = false)
    private String packageImage;


    @OneToMany(mappedBy = "pack", orphanRemoval = true, cascade = CascadeType.ALL)
    List<PackageItenary> packageItenaryList = new ArrayList<>();

}
