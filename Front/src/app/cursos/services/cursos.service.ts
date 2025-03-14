import { Curso } from '../model/curso';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CursosService {

  private readonly API = 'api/cursos';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Curso[]>(this.API)
      .pipe(
       // delay(5000)
      );
  }

  loadById(id: string){
    return this.httpClient.get<Curso>(`${this.API}/${id}`);
  }

  save(record: Partial <Curso>) {
  return this.httpClient.post<Curso>(this.API, record);
  
  }
}
