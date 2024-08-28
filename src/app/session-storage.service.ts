import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  constructor() { }

  // Set item in sessionStorage
  setItem(key: string, value: any): void {
    sessionStorage.setItem(key, JSON.stringify(value));
  }

  // Get item from sessionStorage
  getItem(key: string): any {
    const data = sessionStorage.getItem(key);
    return data ? JSON.parse(data) : null;
  }

  // Remove item from sessionStorage
  removeItem(key: string): void {
    sessionStorage.removeItem(key);
  }

  // Clear all sessionStorage
  clear(): void {
    sessionStorage.clear();
  }
}
