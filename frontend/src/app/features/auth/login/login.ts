import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {

    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });

  }

  onSubmit(): void {

    if (this.loginForm.invalid) {
      return;
    }

    this.authService.login(this.loginForm.getRawValue()).subscribe({

      next: (response) => {

        this.authService.saveToken(response.token);

        console.log('JWT:', response.token);

      },

      error: (error) => {

        console.error(error);

      }

    });

  }

}
