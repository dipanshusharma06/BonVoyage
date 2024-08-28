import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PackageService } from '../package.service';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { SignUpComponent } from '../sign-up/sign-up.component';
import { RouterLink, Router } from '@angular/router';


interface destination{
  packageCountry:String,
  packageCity:String,
  packageName:String,
  packageDesc:String,
  packageImage:String
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,MatCardModule,MatButtonModule, RouterLink,SignUpComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})


export class HomeComponent implements OnInit{
  destinations!:destination[];

  constructor(private packageService:PackageService){}

  ngOnInit(): void {
    this.packageService.getPackages().subscribe((packages)=>{
      this.destinations= packages;
    });
  }

  rangeFrom(startIndex: number, endIndex: number): destination[] { 
    if (!this.destinations) {
      return [];
    }
    return this.destinations.slice(startIndex, endIndex); 
  } 
}
