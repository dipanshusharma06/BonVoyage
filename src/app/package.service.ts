import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PackageService {

  constructor(private http:HttpClient) { }

  apiUrl="http://localhost:8097/api/package"

  getPackages():Observable<any>{
    return this.http.get(this.apiUrl);
  }

  getItenary(packageId:String):Observable<any>{
    return this.http.get(this.apiUrl + "/onepackage/${packageId}");
  }

  
}
