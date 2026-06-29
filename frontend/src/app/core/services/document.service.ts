import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  private readonly apiUrl = 'http://localhost:8080/api/documents';

  constructor(
    private http: HttpClient
  ) {}

  upload(file: File): Observable<any> {

    const formData = new FormData();

    formData.append('file', file);

    return this.http.post(
      this.apiUrl,
      formData
    );

  }

  list(): Observable<any> {

    return this.http.get(this.apiUrl);

  }

}
