import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import {CommonModule} from '@angular/common';

import { DocumentResponse } from '../../../../core/models/document-response';
import { DocumentService } from '../../../../core/services/document.service';

@Component({
  selector: 'app-document-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './document-list.html',
  styleUrl: './document-list.css'
})
export class DocumentList implements OnInit {

  //documents: DocumentResponse[] = [];
  documents: any[] = [];

  constructor(
    private documentService: DocumentService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadDocuments();
  }

  loadDocuments(): void {

    console.log('Loading documents...');

    this.documentService.list().subscribe({

      /*
      next: (documents) => {

        console.log('Documents:', documents);

        this.documents = documents;

        console.log('Length:', this.documents.length);

      },
      */

      next: (documents) => {

        this.documents = [...documents];

        console.log('Assigned:', this.documents);

        this.cdr.detectChanges();

      },

      error: (error) => {

        console.error('Error:', error);

      }

    });

  }

}
