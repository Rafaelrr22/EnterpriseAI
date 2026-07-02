import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Header } from '../../shared/components/header/header';
import { UploadDocument } from './components/upload-document/upload-document';
import { DocumentList } from './components/document-list/document-list';
import { Chat } from '../chat/chat';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    Header,
    UploadDocument,
    DocumentList,
    Chat
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {

}
