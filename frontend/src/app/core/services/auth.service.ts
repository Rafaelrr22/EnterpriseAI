import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { LoginRequest } from '../models/login-request';
import { LoginResponse } from '../models/login-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly apiUrl = 'http://localhost:8080/api/auth';
  private readonly tokenKey = 'token';

  constructor(
    private http: HttpClient
  ) {}

  login(request: LoginRequest): Observable<LoginResponse> {

    return this.http.post<LoginResponse>(
      `${this.apiUrl}/login`,
      request
    );

  }

  saveToken(token: string): void {

    localStorage.setItem(
      this.tokenKey,
      token
    );

  }

  getToken(): string | null {

    return localStorage.getItem(
      this.tokenKey
    );

  }

  logout(): void {

    localStorage.removeItem(
      this.tokenKey
    );

  }

  isAuthenticated(): boolean {

    return this.getToken() !== null;

  }

}
