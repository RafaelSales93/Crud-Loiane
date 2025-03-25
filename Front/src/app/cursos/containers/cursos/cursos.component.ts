import { Curso } from '../../model/curso';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { CursosService } from '../../services/cursos.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogComponent } from '../../components/confirmation-dialog/confirmation-dialog.component';
import { CursoPage } from '../../model/curso-page';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AsyncPipe } from '@angular/common';
import { CursosListComponent } from '../../components/cursos-list/cursos-list.component';


@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss'],
  standalone: true,
  imports: [
    MatCardModule,
    MatToolbarModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    AsyncPipe,
    CursosListComponent,
  ],
})
export class CursosComponent implements OnInit {

  cursos$: Observable<CursoPage> = of({ cursos: [], totalElements: 0, totalPages: 0 });
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  pageIndex = 0;
  pageSize = 10;

  constructor(
    private cursosService: CursosService,
    public dialog: MatDialog,
    public router: Router,
    public route: ActivatedRoute,
    private _snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  refresh(pageEvent: PageEvent = { length: 0, pageIndex: 0, pageSize: 10 }) {
    this.cursos$ = this.cursosService.list(pageEvent.pageIndex, pageEvent.pageSize)
      .pipe(
        tap(response => {
          this.pageIndex = pageEvent.pageIndex;
          this.pageSize = pageEvent.pageSize;
          if (this.paginator) {
            this.paginator.length = response.totalElements;
          }
        }),
        catchError((error) => {
          this.onError('Erro ao Carregar Cursos');
          return of({ cursos: [], totalElements: 0, totalPages: 0 });
        })
      );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg,
    });
  }

  ngOnInit(): void {}

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(curso: Curso) {
    this.router.navigate(['edit', curso._id], { relativeTo: this.route });
  }

  onRemove(curso: Curso) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover esse curso?',
    });

    dialogRef.afterClosed().subscribe((result: Boolean) => {
      if (result) {
        this.cursosService.remove(curso._id).subscribe(
          () => {
            this.refresh();
            this._snackBar.open('Curso removido com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center',
            });
          },
          () => {
            this.onError('Erro ao tentar remover curso.');
          }
        );
      }
    });
  }
}
