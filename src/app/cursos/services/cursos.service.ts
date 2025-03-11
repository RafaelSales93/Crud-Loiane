import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Curso } from '../modelos/curso';


@Injectable({
  providedIn: 'root'
})
export class CursosService {

  constructor(private httpClient: HttpClient) { }

  list(): Curso[] {
    return[
      {_id: '1', nome: 'Angular', categoria: 'front-end'}
    ];
  }
}
