import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RagService } from '../../core/services/rag.service';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './chat.html',
  styleUrl: './chat.css'
})
export class Chat {

  question = '';

  answer = '';

  sources: string[] = [];

  loading = false;

  constructor(
    private ragService: RagService,
    private cdr: ChangeDetectorRef
  ) {}

  ask(): void {

    if (this.loading) {
      return;
    }

    if (!this.question.trim()) {
      return;
    }

    this.loading = true;

    this.ragService.ask(this.question)
      .subscribe({

        next: (response) => {

          console.log('Response received:', response);

          this.answer = response.answer;

          this.sources = response.sources;

          this.question = '';

          this.loading = false;

          this.cdr.detectChanges();

        },

        error: (error) => {

          console.error(error);

          this.loading = false;

          this.cdr.detectChanges();

        }

      });

  }

}
