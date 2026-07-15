import { Injectable } from '@angular/core';

import { ChatMessage } from '../models/chat-message';

@Injectable({
  providedIn: 'root'
})
export class ChatStateService {

  private readonly storageKey = 'gef-ai-chat-history';

  messages: ChatMessage[] = [];

  constructor() {

    this.loadMessages();

  }

  saveMessages(): void {

    sessionStorage.setItem(
      this.storageKey,
      JSON.stringify(this.messages)
    );

  }

  clearMessages(): void {

    this.messages.length = 0;

    sessionStorage.removeItem(
      this.storageKey
    );

  }

  private loadMessages(): void {

    const storedMessages = sessionStorage.getItem(
      this.storageKey
    );

    if (!storedMessages) {
      return;
    }

    try {

      const parsedMessages: ChatMessage[] =
        JSON.parse(storedMessages);

      this.messages.push(
        ...parsedMessages.map((message) => ({
          ...message,
          timestamp: new Date(message.timestamp),
          loading: false
        }))
      );

    } catch (error) {

      console.error(
        'Failed to load the chat history.',
        error
      );

      sessionStorage.removeItem(
        this.storageKey
      );

    }

  }

}
