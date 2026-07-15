import {
  ChangeDetectorRef,
  Component
} from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { MatIconModule } from '@angular/material/icon';

import { RagService } from '../../core/services/rag.service';
import { NotificationService } from '../../core/services/notification.service';
import { ChatStateService } from '../../core/services/chat-state.service';

import { ChatMessage } from '../../core/models/chat-message';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatIconModule
  ],
  templateUrl: './chat.html',
  styleUrl: './chat.css'
})
export class Chat {

  question = '';

  messages: ChatMessage[];

  loading = false;

  constructor(
    private ragService: RagService,
    private notificationService: NotificationService,
    private cdr: ChangeDetectorRef,
    private chatStateService: ChatStateService
  ) {

    this.messages = this.chatStateService.messages;

  }

  ask(): void {

    if (this.loading) {
      return;
    }

    const question = this.question.trim();

    if (!question) {
      return;
    }

    const message: ChatMessage = {
      question,
      answer: '',
      sources: [],
      timestamp: new Date(),
      loading: true
    };

    this.messages.unshift(message);

    this.question = '';

    this.loading = true;

    this.ragService.ask(question).subscribe({

      next: (response) => {

        message.answer = response.answer;

        message.sources = response.sources;

        message.loading = false;

        this.loading = false;

        this.cdr.detectChanges();

      },

      error: (error) => {

        console.error(error);

        this.notificationService.error(
          'Failed to get AI response.'
        );

        message.answer = 'Something went wrong.';

        message.loading = false;

        this.loading = false;

        this.cdr.detectChanges();

      }

    });

  }

  copyAnswer(answer: string): void {

    navigator.clipboard.writeText(answer);

    this.notificationService.success(
      'Answer copied to clipboard.'
    );

  }

  clearConversation(): void {

    this.messages.length = 0;

    this.question = '';

    this.notificationService.info(
      'Conversation cleared.'
    );

  }

}
