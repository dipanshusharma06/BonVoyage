import { Component, OnInit, PLATFORM_ID, Inject } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { SessionStorageService } from '../session-storage.service';
import { BookServiceService } from '../book-service.service';
import {MatExpansionModule} from '@angular/material/expansion';
import { PackageService } from '../package.service';
import { response } from 'express';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

interface packageItenary{
  day:String,
  title:String,
  activities:String
}

interface Destination{
  packageID:String,
  packageCountry:String,
  packageCity:String,
  packageName:String,
  packageDesc:String,
  packageRating:String,
  packageReviews:Number,
  packagePrice:Number,
  packageDuration:String,
  availableDate:String,
  packageImage:String,
  packageItenaryList:packageItenary[]
}

interface Wishlist{
  wishlistId: String,
  packages: String[]
}

interface User{
  id: String,
  name: String,
  email: String,
  phone: String,
  address: String,
  wishlist: Wishlist[] 
}

interface Booking{
  userId: String,
  bookingId: String,
  packageId: String,
  bookingDate: String,
  bookingPerson: Number,
  bookingRooms: Number
}

@Component({
  selector: 'app-mybooking',
  standalone: true,
  imports: [CommonModule,MatExpansionModule,MatIconModule,MatButtonModule],
  templateUrl: './mybooking.component.html',
  styleUrl: './mybooking.component.css'
})
export class MybookingComponent implements OnInit{

  user!:User;
  bookings!:Booking[];
  userPackages: Destination[]=[];

  panelOpenState = false;

  constructor(  
    @Inject(PLATFORM_ID) private platformId: Object,
    private session: SessionStorageService,
    private bookService: BookServiceService,
    private packageService: PackageService
  ){}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)){
      this.user = this.session.getItem('user');
      this.bookService.getBookings(this.user.id).subscribe((response)=>{
        this.bookings= response;
        this.session.setItem('booking',this.bookings);
        this.packageService.getPackages().subscribe((packageResponse)=>{
          this.userPackages= packageResponse;

          let bookingPackages=[];
          for(let item of  this.bookings){
            bookingPackages.push(item.packageId);
         }

          console.log(bookingPackages);

          this.userPackages=this.userPackages.filter((item)=>{
            return bookingPackages.indexOf(item.packageID)!=-1;
          })

          console.log(this.userPackages);
        })
       
      });
    }
  }

  deletePackageFromBookings(packageId:String):void{
    this.bookings= this.session.getItem('booking');
    let bookingId= this.bookings.filter((item)=>{
      return item.packageId == packageId;
    })
    this.bookService.deletePackage(bookingId[0].bookingId).subscribe((response)=>{
      console.log(response);
    });

    window.location.reload();
  }

}

