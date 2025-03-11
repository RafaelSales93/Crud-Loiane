import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable, of } from 'rxjs';

import { Curso } from '../modelos/curso';
import { CursosService } from './../services/cursos.service';
import { catchError } from 'rxjs/operators';
import { error } from '@angular/compiler/src/util';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss'],
})
export class CursosComponent implements OnInit {
  cursos$: Observable<Curso[]>;
  displayedColumns = ['nome', 'categoria'];

  constructor(
    private cursosService: CursosService,
    public dialog: MatDialog
  ) {
    // this.cursos = [];
    
    
    
    this.cursos$ = this.cursosService.list().pipe(
      catchError((error) => {
        this.onError('Erro ao Carregar Cursos');

        return of([]);
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }
  ngOnInit(): void {}
}
