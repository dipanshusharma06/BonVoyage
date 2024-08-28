import { Component, OnInit, PLATFORM_ID, Inject } from '@angular/core';
import { SessionStorageService } from '../session-storage.service';
import { WishlistServiceService } from '../wishlist-service.service';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { PackageService } from '../package.service';
import { isPlatformBrowser } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

interface Destination{
  packageID:String,
  packageCountry:String,
  packageCity:String,
  packageName:String,
  packageDesc:String,
  packageRating:Number,
  packageReviews:Number,
  packagePrice:String,
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

@Component({
  selector: 'app-wishlist',
  standalone: true,
  imports: [MatCardModule, CommonModule,HttpClientModule,MatIconModule,MatButtonModule],
  providers:[SessionStorageService],
  templateUrl: './wishlist.component.html',
  styleUrl: './wishlist.component.css'
})
export class WishlistComponent implements OnInit{

  wishlist!: Wishlist[];
  user!: User;
  userPackages!: String[];
  userPackagesList!: Destination[];

  constructor(
    private wishlistService:WishlistServiceService,
    private session: SessionStorageService,
    private packageService: PackageService,
    @Inject(PLATFORM_ID) private platformId: Object
  ){}

  ngOnInit(): void {  

    if (isPlatformBrowser(this.platformId)){

      this.user = this.session.getItem('user');

    this.wishlistService.getUser(this.user.name).subscribe((response)=>{
      this.user = response;
      this.session.setItem('user',this.user);
      this.wishlist = this.user.wishlist;
      this.userPackages = this.wishlist[0].packages;
      console.log(this.userPackages);

      this.packageService.getPackages().subscribe((packageData)=>{
        this.userPackagesList = packageData;
        this.userPackagesList=this.userPackagesList.filter((userDataPackage)=>{
          return this.userPackages.indexOf(userDataPackage.packageID)!=-1;
        })
        console.log(this.userPackagesList);
      })
    });

    }

  }

  deletePackageFromWishlist(packageId:String):void{
    this.wishlistService.deletePackageFromWishList(this.user.id,this.wishlist[0].wishlistId,packageId).subscribe((response)=>{
      console.log(response);
    })
    window.location.reload();
  }

}
