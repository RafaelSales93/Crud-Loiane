import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './../shered/app-material/app-material.module';
import { CursosRoutingModule } from './cursos-routing.module';
import { CursosComponent } from './cursos/cursos.component';
import { SharedModule } from '../shared/shared.module';
import { CursoFormComponent } from './curso-form/curso-form.component';
import { ReactiveFormsModule } from '@angular/forms';





@NgModule({
  declarations: [
    CursosComponent,
    CursoFormComponent
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
