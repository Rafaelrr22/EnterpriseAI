import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { RagRequest } from '../models/rag-request';
import { RagResponse } from '../models/rag-response';

@Injectable({
  providedIn: 'root'
})
export class RagService {

  private readonly apiUrl = 'http://localhost:8080/api/rag';

  constructor(
    private http: HttpClient
  ) {}

  ask(question: string): Observable<RagResponse> {

    const request: RagRequest = {
      question
    };

    return this.http.post<RagResponse>(
      `${this.apiUrl}/ask`,
      request
    );

  }

}
