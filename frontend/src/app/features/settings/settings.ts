import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { MatIconModule } from '@angular/material/icon';

import { ChatStateService } from '../../core/services/chat-state.service';
import { NotificationService } from '../../core/services/notification.service';
import { PreferencesService } from '../../core/services/preferences.service';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatIconModule
  ],
  templateUrl: './settings.html',
  styleUrl: './settings.css'
})
export class Settings {

  showSources = true;

  showClearConfirmation = false;

  constructor(
    private chatStateService: ChatStateService,
    private notificationService: NotificationService,
    private preferencesService: PreferencesService
  ) {

    this.showSources =
      this.preferencesService.showSources;

  }

  savePreferences(): void {

    this.preferencesService.showSources =
      this.showSources;

    this.notificationService.success(
      'Preferences saved successfully.'
    );

  }

  openClearConfirmation(): void {

    this.showClearConfirmation = true;

  }

  cancelClearConversation(): void {

    this.showClearConfirmation = false;

  }

  confirmClearConversation(): void {

    this.chatStateService.clearMessages();

    this.showClearConfirmation = false;

    this.notificationService.info(
      'Conversation history cleared.'
    );

  }

}
