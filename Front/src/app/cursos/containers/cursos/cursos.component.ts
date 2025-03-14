import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Curso } from '../../modelos/curso';
import { CursosService } from '../../services/cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss'],
})
export class CursosComponent implements OnInit {
  cursos$: Observable<Curso[]>;
 

  constructor(
    private cursosService: CursosService,
    public dialog: MatDialog,
    public router: Router, 
    public route: ActivatedRoute
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

  onAdd() {
   this.router.navigate(['new'], {relativeTo: this.route});
   
    
  }
}
