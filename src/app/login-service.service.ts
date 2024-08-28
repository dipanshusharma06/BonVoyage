import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {
  private baseUrl = "http://localhost:8095/api/users"

  constructor(private http: HttpClient) { }

  register(user: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, user);
  }
  login(detail :any): Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/login`, detail);
  }

}
