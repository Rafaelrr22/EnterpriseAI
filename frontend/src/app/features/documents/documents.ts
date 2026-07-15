import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UploadDocument } from './components/upload-document/upload-document';
import { DocumentList } from './components/document-list/document-list';

@Component({
  selector: 'app-documents',
  standalone: true,
  imports: [
    CommonModule,
    UploadDocument,
    DocumentList
  ],
  templateUrl: './documents.html',
  styleUrl: './documents.css'
})
export class Documents {

}
