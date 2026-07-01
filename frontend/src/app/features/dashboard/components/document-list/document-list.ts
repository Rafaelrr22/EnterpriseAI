import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

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

  documents: DocumentResponse[] = [];

  constructor(
    private documentService: DocumentService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    this.loadDocuments();

    this.documentService.documentsChanged$
      .subscribe(() => {

        this.loadDocuments();

      });

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

  deleteDocument(id: string): void {

    this.documentService.delete(id).subscribe({

      next: () => {

        this.documentService.notifyDocumentsChanged();

      },

      error: (error) => {

        console.error(error);

      }

    });

  }

  downloadDocument(document: DocumentResponse): void {

    this.documentService.download(document.id)
      .subscribe({

        next: (blob) => {

          const url = window.URL.createObjectURL(blob);

          const a = window.document.createElement('a');

          a.href = url;
          a.download = document.filename;

          a.click();

          window.URL.revokeObjectURL(url);

        },

        error: (error) => {

          console.error(error);

        }

      });

  }

}
