import { Curso } from '../model/curso';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { delay, first } from 'rxjs/operators';
import { CursoPage } from '../model/curso-page';

@Injectable({
  providedIn: 'root',
})
export class CursosService {
  private readonly API = 'api/cursos';

  constructor(private httpClient: HttpClient) {}

  list(page = 0, pageSize = 10) {
    return this.httpClient.get<CursoPage>(this.API, {params: { page, pageSize } })
      .pipe(
      // delay(5000)
      );
  }

  loadById(id: string) {
    return this.httpClient.get<Curso>(`${this.API}/${id}`);
  }

  save(record: Partial<Curso>) {
    if (record._id) {
    return this.update(record);
    }
    return this.create(record);
  }

  private create(record: Partial<Curso>) {
    return this.httpClient.post<Curso>(this.API, record).pipe(first());
  }

  private update(record: Partial<Curso>) {
    return this.httpClient
      .put<Curso>(`${this.API}/${record._id}`, record)
      .pipe(first());
  }

 public remove(id: string) {
  return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
}


}
