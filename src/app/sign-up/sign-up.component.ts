import { CommonModule } from '@angular/common';
import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { LoginServiceService } from '../login-service.service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink, MatFormFieldModule, MatIconModule, MatInputModule,MatButtonModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SignUpComponent implements OnInit {
  myForm: FormGroup;
  Message: string = '';
  errorResponse: string = '';
  showError: boolean = false;
  showMessage: boolean = false;
  submissionAttempted: boolean = false;
  

  constructor(private fb: FormBuilder, private loginService: LoginServiceService,private router: Router) {
    this.myForm = this.fb.group({
      name: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      phone: [null, [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
      password: [null, [Validators.required, Validators.minLength(8)]],
      confirm: [null, Validators.required],
      address: [null, Validators.required]
    }, { validators: this.passwordMatchValidator });
  }

  ngOnInit(): void {}

  passwordMatchValidator(form: AbstractControl): ValidationErrors | null {
    const password = form.get('password')?.value;
    const confirmPassword = form.get('confirm')?.value;
    if (password !== confirmPassword) {
      form.get('confirm')?.setErrors({ passwordMismatch: true });
      return { passwordMismatch: true };
    } else {
      return null;
    }
  }

  onSubmit(): void {
    this.submissionAttempted = true;
    this.showMessage = false;
    this.Message = "";
    this.errorResponse = "";
    if (this.myForm.valid) {
      this.loginService.register(this.myForm.value).subscribe(
        (response) => {
          this.Message = "🎉🎉Registration Successfull🎉🎉";
          this.showMessage = true;
          this.submissionAttempted = false;
          
          this.myForm.reset();
          this.router.navigate(['/sign-in']);  
        },
        error => {
          this.errorResponse = '❌❌Not submitted❌❌';
          this.showError = true;
        }
      );
    } else {
      this.showError = true;
    }
  }
}
