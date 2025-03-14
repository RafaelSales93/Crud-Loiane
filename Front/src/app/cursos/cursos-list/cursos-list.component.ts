import { CursosComponent } from './../cursos/cursos.component';
import { Component, Input, OnInit } from '@angular/core';
import { Curso } from '../modelos/curso';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cursos-list',
  templateUrl: './cursos-list.component.html',
  styleUrls: ['./cursos-list.component.scss'],
})
export class CursosListComponent implements OnInit {
 @Input() cursos: Curso[] = [];
  displayedColumns = ['nome', 'categoria', 'actions'];

  constructor(public router: Router, public route: ActivatedRoute) {}

  ngOnInit(): void {}
  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }
}
