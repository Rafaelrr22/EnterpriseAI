import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  logout(): void {

    this.authService.logout();

    this.router.navigate(['/login']);

  }

}
