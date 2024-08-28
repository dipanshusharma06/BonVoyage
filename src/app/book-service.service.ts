import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookServiceService {

  constructor(private http:HttpClient) { }

  apiUrl= "http://localhost:8096/api/booking";

  bookPackage(data:any):Observable<any>{
    return this.http.post(this.apiUrl,data);
  }

  deletePackage(bookingId:String):Observable<any>{
    return this.http.delete(this.apiUrl+`/${bookingId}`);
  }

  getBookings(userId:String):Observable<any>{
    return this.http.get(this.apiUrl+`/${userId}`)
  }
}
