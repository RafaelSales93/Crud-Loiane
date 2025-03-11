import { CursosService } from './../services/cursos.service';
import { Component, OnInit } from '@angular/core';

import { Curso } from '../modelos/curso';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.scss']
})
export class CursosComponent implements OnInit {

  cursos: Curso[] = [];
  displayedColumns = ['nome','categoria'];

  constructor(private cursosService: CursosService) { 
   // this.cursos = [];
  }

  ngOnInit(): void {
    this.cursos = this.cursosService.list();
  }

}
