import { Routes } from '@angular/router';

import { CursoResolver } from './guards/curso.resolver';
import { CursosComponent } from './containers/cursos/cursos.component';
import { CursoFormComponent } from './containers/curso-form/curso-form.component';

export const CURSOS_ROTAS: Routes = [
  { path: '', component: CursosComponent },
  { path: 'new', component: CursoFormComponent, resolve: { curso: CursoResolver } },
  { path: 'edit/:id', component: CursoFormComponent, resolve: { curso: CursoResolver } }
];