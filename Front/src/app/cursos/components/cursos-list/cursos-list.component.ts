
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Curso } from '../../model/curso';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';


@Component({
    selector: 'app-cursos-list',
    templateUrl: './cursos-list.component.html',
    styleUrls: ['./cursos-list.component.scss'],
    standalone: true,
    imports: [
        MatTableModule,
        MatIconModule,
        MatButtonModule,
    ],
})
export class CursosListComponent implements OnInit {
  @Input() cursos: Curso[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() remove = new EventEmitter(false);

  readonly displayedColumns = ['nome', 'categoria', 'actions'];

  constructor() {}

  ngOnInit(): void {}

  onAdd() {
    this.add.emit(true);
  }

  onEdit(curso: Curso) {
    this.edit.emit(curso);
  }

  onDelete(curso: Curso) {
    this.remove.emit(curso);
  }
}
