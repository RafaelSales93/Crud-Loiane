import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { CursosRoutingModule } from './cursos-routing.module';
import { CursosComponent } from './containers/cursos/cursos.component';
import { SharedModule } from '../shared/shared.module';
import { CursoFormComponent } from './containers/curso-form/curso-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CursosListComponent } from './components/cursos-list/cursos-list.component';





@NgModule({
  declarations: [
    CursosComponent,
    CursoFormComponent,
    CursosListComponent,
  ],
  imports: [
    CommonModule,
    CursosRoutingModule,
    AppMaterialModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class CursosModule { }
