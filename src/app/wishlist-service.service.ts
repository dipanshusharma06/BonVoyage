import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WishlistServiceService {

  constructor(private http:HttpClient) { }

  apiUrl='http://localhost:8095/api/users/';
  apiUrlWishlist='http://localhost:8095/api/wishlists/'

  getUser(userName:String):Observable<any>{
    return this.http.get(this.apiUrl+userName);
  }

  createWishlist(userId:String):Observable<any>{
    return this.http.post(this.apiUrlWishlist+userId,'');
  }

  addPackageToWishList(userId:String, wishListId:String, packageId:String):Observable<any>{
    return this.http.post(this.apiUrlWishlist+`${userId}/${wishListId}/${packageId}`,'');
  }

  deletePackageFromWishList(userId:String, wishListId:String, packageId:String):Observable<any>{
    return this.http.delete(this.apiUrlWishlist+`${userId}/${wishListId}/${packageId}`);
  }

}
