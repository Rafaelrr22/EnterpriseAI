import {
  ChangeDetectorRef,
  Component,
  ElementRef,
  ViewChild
} from '@angular/core';

import { CommonModule } from '@angular/common';

import { DocumentService } from '../../../../core/services/document.service';
import {NotificationService} from '../../../../core/services/notification.service';
import { MatIconModule } from "@angular/material/icon";

@Component({
  selector: 'app-upload-document',
  standalone: true,
  imports: [
    CommonModule,
    MatIconModule
  ],
  templateUrl: './upload-document.html',
  styleUrl: './upload-document.css'
})
export class UploadDocument {

  @ViewChild('fileInput')
  fileInput!: ElementRef<HTMLInputElement>;

  selectedFile: File | null = null;

  selectedFileName = 'No file selected';

  uploading = false;

  constructor(
    private documentService: DocumentService,
    private notificationService: NotificationService,
    private cdr: ChangeDetectorRef
  ) {}

  onFileSelected(event: Event): void {

    const input = event.target as HTMLInputElement;

    if (!input.files?.length) {

      this.selectedFile = null;
      this.selectedFileName = 'No file selected';

      return;

    }

    this.selectedFile = input.files[0];
    this.selectedFileName = this.selectedFile.name;

  }

  upload(): void {

    if (!this.selectedFile) {
      return;
    }

    this.uploading = true;

    this.documentService.upload(this.selectedFile).subscribe({

      next: () => {

        this.selectedFile = null;
        this.selectedFileName = 'No file selected';

        this.fileInput.nativeElement.value = '';

        this.uploading = false;

        this.cdr.detectChanges();

        this.documentService.notifyDocumentsChanged();

        this.notificationService.success(
          'Document uploaded successfully.'
        );

      },

      error: (error) => {

        console.error(error);

        this.notificationService.error(
          'Failed to upload document.'
        );

        this.uploading = false;

      }

    });

  }

  onDragOver(event: DragEvent): void {

    event.preventDefault();

  }

  onDrop(event: DragEvent): void {

    event.preventDefault();

    const files = event.dataTransfer?.files;

    if (!files?.length) {
      return;
    }

    const file = files[0];

    if (file.type !== 'application/pdf') {

      this.notificationService.error(
        'Only PDF documents are supported.'
      );

      return;

    }

    this.selectedFile = file;
    this.selectedFileName = file.name;

  }

}
