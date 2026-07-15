import { Routes } from '@angular/router';

import { authGuard } from './core/guards/auth.guard';

import { Login } from './features/auth/login/login';
import { Dashboard } from './features/dashboard/dashboard';
import { Documents } from './features/documents/documents';
import { Chat } from './features/chat/chat';
import { Settings } from './features/settings/settings';

import { MainLayout } from './layouts/main-layout/main-layout';

export const routes: Routes = [
  {
    path: 'login',
    component: Login
  },
  {
    path: '',
    component: MainLayout,
    canActivate: [authGuard],
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        component: Dashboard
      },
      {
        path: 'documents',
        component: Documents
      },
      {
        path: 'chat',
        component: Chat
      },
      {
        path: 'settings',
        component: Settings
      }
    ]
  },
  {
    path: '**',
    redirectTo: 'dashboard'
  }
];
