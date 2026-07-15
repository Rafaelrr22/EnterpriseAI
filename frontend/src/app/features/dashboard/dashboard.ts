import {
  ChangeDetectorRef,
  Component,
  OnDestroy,
  OnInit
} from '@angular/core';

import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { MatIconModule } from '@angular/material/icon';

import { Subject, takeUntil } from 'rxjs';

import { DocumentService } from '../../core/services/document.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    MatIconModule
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit, OnDestroy {

  documentCount = 0;

  loadingDocuments = true;

  private readonly destroy$ = new Subject<void>();

  constructor(
    private documentService: DocumentService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    this.loadDocumentCount();

    this.documentService.documentsChanged$
      .pipe(
        takeUntil(this.destroy$)
      )
      .subscribe(() => {

        this.loadDocumentCount();

      });

  }

  ngOnDestroy(): void {

    this.destroy$.next();

    this.destroy$.complete();

  }

  private loadDocumentCount(): void {

    this.loadingDocuments = true;

    this.documentService.list()
      .subscribe({

        next: (documents) => {

          this.documentCount = documents.length;

          this.loadingDocuments = false;

          this.cdr.detectChanges();

        },

        error: (error) => {

          console.error(
            'Failed to load the document count.',
            error
          );

          this.loadingDocuments = false;

          this.cdr.detectChanges();

        }

      });

  }

}
