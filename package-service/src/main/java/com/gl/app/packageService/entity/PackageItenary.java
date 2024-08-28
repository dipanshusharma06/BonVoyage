package com.gl.app.packageService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PackageItenary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageItenary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long packId;
    @Column(nullable = false)
    private String day;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String activities;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "packageItenaryId", nullable = false)
    private Package pack;

}
