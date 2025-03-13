import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { Location } from '@angular/common';
import { CursosService } from '../services/cursos.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.scss'],
})
export class CursoFormComponent implements OnInit {

  
  form = this.formBuilder.group({
    nome: [''],
    categoria: ['']
  });

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: CursosService,
    private _snackBar: MatSnackBar,
    private location: Location ) {
    // this.form 
  }

  ngOnInit(): void {

  }

  onSubmit() {
    this.service.save(this.form.value).subscribe(
      (result) => this.onSuccess(),
      (error) => this.onErro()
    );
  }
  onCancel() {
    this.location.back();
  }

  private onErro() {
    this._snackBar.open('Erro ao salvar curso', '', { duration: 5000 });
  }
  private onSuccess() {
    this._snackBar.open('Curso Salvo com Sucesso!', '', { duration: 5000 });
    this.onCancel();
  }
}
