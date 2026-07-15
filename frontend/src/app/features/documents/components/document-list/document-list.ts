import {
  ChangeDetectorRef,
  Component,
  OnInit
} from '@angular/core';

import { CommonModule } from '@angular/common';

import { MatIconModule } from '@angular/material/icon';

import { DocumentResponse } from '../../../../core/models/document-response';

import { DocumentService } from '../../../../core/services/document.service';
import { NotificationService } from '../../../../core/services/notification.service';

@Component({
  selector: 'app-document-list',
  standalone: true,
  imports: [
    CommonModule,
    MatIconModule
  ],
  templateUrl: './document-list.html',
  styleUrl: './document-list.css'
})
export class DocumentList implements OnInit {

  documents: DocumentResponse[] = [];

  documentPendingDeletionId: string | null = null;

  deletingDocumentId: string | null = null;

  constructor(
    private documentService: DocumentService,
    private notificationService: NotificationService,
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

    this.documentService.list().subscribe({

      next: (documents) => {

        this.documents = [...documents];

        this.cdr.detectChanges();

      },

      error: (error) => {

        console.error(error);

        this.notificationService.error(
          'Failed to load documents.'
        );

      }

    });

  }

  openDeleteConfirmation(id: string): void {

    this.documentPendingDeletionId = id;

  }

  cancelDelete(): void {

    this.documentPendingDeletionId = null;

  }

  confirmDelete(id: string): void {

    if (this.deletingDocumentId) {
      return;
    }

    this.deletingDocumentId = id;

    this.documentService.delete(id).subscribe({

      next: () => {

        this.documentPendingDeletionId = null;

        this.deletingDocumentId = null;

        this.documentService.notifyDocumentsChanged();

        this.notificationService.success(
          'Document deleted successfully.'
        );

      },

      error: (error) => {

        console.error(error);

        this.deletingDocumentId = null;

        this.notificationService.error(
          'Failed to delete document.'
        );

      }

    });

  }

  downloadDocument(
    document: DocumentResponse
  ): void {

    this.documentService.download(document.id)
      .subscribe({

        next: (blob) => {

          const url =
            window.URL.createObjectURL(blob);

          const link =
            window.document.createElement('a');

          link.href = url;

          link.download = document.filename;

          link.click();

          window.URL.revokeObjectURL(url);

        },

        error: (error) => {

          console.error(error);

          this.notificationService.error(
            'Failed to download document.'
          );

        }

      });

  }

  formatSize(size: number): string {

    if (size < 1024) {
      return `${size} B`;
    }

    if (size < 1024 * 1024) {

      return `${(size / 1024).toFixed(1)} KB`;

    }

    return `${(
      size /
      (1024 * 1024)
    ).toFixed(1)} MB`;

  }

}
