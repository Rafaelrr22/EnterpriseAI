import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, Subject } from 'rxjs';

import { DocumentResponse } from '../models/document-response';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  private readonly apiUrl = 'http://localhost:8080/api/documents';

  private documentsChanged = new Subject<void>();

  documentsChanged$ = this.documentsChanged.asObservable();

  constructor(
    private http: HttpClient
  ) {}

  notifyDocumentsChanged(): void {

    this.documentsChanged.next();

  }

  upload(file: File): Observable<DocumentResponse> {

    const formData = new FormData();

    formData.append('file', file);

    return this.http.post<DocumentResponse>(
      this.apiUrl,
      formData
    );

  }

  list(): Observable<DocumentResponse[]> {

    return this.http.get<DocumentResponse[]>(
      this.apiUrl
    );

  }

  delete(id: string): Observable<void> {

    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );

  }

  download(id: string): Observable<Blob> {

    return this.http.get(
      `${this.apiUrl}/${id}/download`,
      {
        responseType: 'blob'
      }
    );

  }

}
