
import { Routes } from '@angular/router';

export const APP_ROTAS: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'cursos' },
  {
    path: 'cursos',
    loadChildren: () => import('./cursos/cursos.routes')
    .then(m => m.CURSOS_ROTAS)
  }
];