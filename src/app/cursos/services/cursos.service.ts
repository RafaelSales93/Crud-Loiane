import { Curso } from './../modelos/curso';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})
export class CursosService {

private readonly API = '/assets/cursos.json';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Curso[]>(this.API)
  }
}
