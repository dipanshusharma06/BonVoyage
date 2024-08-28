import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginServiceService } from '../login-service.service';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SessionStorageService } from '../session-storage.service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';


@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink, MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule],
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  myForm!: FormGroup;
  Message: string = '';
  errorResponse: string = '';
  submissionAttempted: boolean = false;
  showError: boolean = false;
  showMessage: boolean = false;

  constructor(
    private fb: FormBuilder, 
    private loginService: LoginServiceService,
    private router: Router,
    private session: SessionStorageService
    ) { }

  ngOnInit(): void {
    this.myForm = this.fb.group({
      email: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  onSubmit(): void {
    this.submissionAttempted = true;
    if (this.myForm.valid) {
      
      this.loginService.login(this.myForm.value).subscribe(
        (response) => {
          this.Message = "🎉🎉Login Successfull🎉🎉";
          this.showMessage = true;
          this.submissionAttempted = false;
          
          this.myForm.reset();
          this.session.setItem('user',response); // storing user for my bookings and wishlist
          this.router.navigate(['/packages']);
        },
        (error) => {
          
          this.errorResponse = '❌❌Invalid Credentials❌❌';
          this.showError = true; 
        }
      );
    } else {
      this.showError = true;
      
    }
  }
}
