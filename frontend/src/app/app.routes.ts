import { Routes } from '@angular/router';

import { authGuard } from './core/guards/auth.guard';

import { Login } from './features/auth/login/login';
import { Dashboard } from './features/dashboard/dashboard';
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
        path: 'dashboard',
        component: Dashboard
      }
    ]
  },
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: 'dashboard'
  }
];
