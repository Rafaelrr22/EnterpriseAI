import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RagService } from '../../core/services/rag.service';
import { ChatMessage } from '../../core/models/chat-message';

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

  messages: ChatMessage[] = [];

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

          this.messages.unshift({

            question: this.question,

            answer: response.answer,

            sources: response.sources,

            timestamp: new Date()

          });

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
