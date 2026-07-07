import { Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DocumentService } from '../../../../core/services/document.service';

@Component({
  selector: 'app-upload-document',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './upload-document.html',
  styleUrl: './upload-document.css'
})
export class UploadDocument {

  @ViewChild('fileInput')
  fileInput!: ElementRef<HTMLInputElement>;

  selectedFile: File | null = null;

  selectedFileName = 'No file selected';

  constructor(
    private documentService: DocumentService
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

    this.documentService.upload(this.selectedFile).subscribe({

      next: (response) => {

        console.log(response);

        this.documentService.notifyDocumentsChanged();

        this.selectedFile = null;

        this.selectedFileName = 'No file selected';

        this.fileInput.nativeElement.value = '';

      },

      error: (error) => {

        console.error(error);

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

      // Test
      alert('Only PDF files are supported.');

      return;

    }

    this.selectedFile = file;

    this.selectedFileName = file.name;

  }

}
