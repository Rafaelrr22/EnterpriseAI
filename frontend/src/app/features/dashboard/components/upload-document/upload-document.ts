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

  constructor(
    private documentService: DocumentService
  ) {}

  onFileSelected(event: Event): void {

    const input = event.target as HTMLInputElement;

    if (!input.files?.length) {
      return;
    }

    this.selectedFile = input.files[0];

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

        this.fileInput.nativeElement.value = '';

      },

      error: (error) => {

        console.error(error);

      }

    });

  }

}
