import { Component, OnInit } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { WishlistServiceService } from '../wishlist-service.service';
import { SessionStorageService } from '../session-storage.service';
import { MatCardModule } from '@angular/material/card';
import {MatSnackBar} from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { PackageService } from '../package.service';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { BookServiceService } from '../book-service.service';
import { Router } from '@angular/router';

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
  packageId: String,
  bookingDate: String,
  bookingPerson: Number,
  bookingRooms: Number
}

@Component({
  selector: 'app-package-listing',
  standalone: true,
  imports: [HttpClientModule,MatCardModule, CommonModule, MatIconModule,MatButtonModule],
  templateUrl: './package-listing.component.html',
  styleUrl: './package-listing.component.css'
})
export class PackageListingComponent implements OnInit{

  user!: User;
  userPackages: Destination[]=[];
  wishlist!: any;
  booking: Booking={
    userId:"",
    packageId:"",
    bookingDate:"",
    bookingPerson: 0,
    bookingRooms: 0
  };
  bookings!:Booking[];

  constructor(
    private wishlistService:WishlistServiceService,
    private session: SessionStorageService,
    private packageService: PackageService,
    private bookService: BookServiceService,
    private _snackBar: MatSnackBar,
    private router: Router
  ){}

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  ngOnInit(): void {
    this.packageService.getPackages().subscribe((packages)=>{
      this.userPackages = packages;
    })
  }

  addToWishList(packageId:String):void{
    this.user = this.session.getItem('user');
    this.wishlistService.getUser(this.user.name).subscribe((userData)=>{
      this.user=userData;
      this.session.setItem('user',this.user);

      if(this.user.wishlist.length==0){
        this.wishlistService.createWishlist(this.user.id).subscribe((response)=>{
          this.wishlist= response;
          this.session.setItem('wishlist',this.wishlist);
          console.log(this.wishlist);
        });
      }

      // console.log(this.user);
      // console.log(this.wishlist);
      // console.log(packageId);

      this.wishlist= this.user.wishlist;
      if(this.user.wishlist[0].packages.indexOf(packageId)!=-1){
        this.openSnackBar("Already added to wishlist","close");
      }else{
        this.wishlistService.addPackageToWishList(this.user.id, this.wishlist[0].wishlistId, packageId).subscribe((response)=>{
          this.wishlistService.getUser(this.user.name).subscribe((response)=>{
            this.user=response;
            this.wishlist= this.user.wishlist[0];
            this.session.setItem('user',this.user);
            this.session.setItem('wishlist',this.wishlist);
          })
        });
        this.openSnackBar("Added to wishlist","close");
      }
    })
    
  }

  addToBookings(packageId:String):void{
    this.user=this.session.getItem('user');
    this.booking.userId= this.user.id;
    this.booking.packageId= packageId;
    this.booking.bookingDate="2024-06-13"
    this.booking.bookingPerson= 5;
    this.booking.bookingRooms= 2;
    
    console.log(this.booking);
    this.bookings= this.session.getItem('booking');
    let bookingList = [];
    for(let item of this.bookings){
      bookingList.push(item.packageId);
    }
    if(bookingList.indexOf(packageId)!=-1){
      this.openSnackBar("Already booked !","close")
      console.log("Already booked !")
    }else{
      this.bookService.bookPackage(this.booking).subscribe((response)=>{
        console.log(response);
        this.router.navigate(['/bookings'])
      });
    }
  }
}

