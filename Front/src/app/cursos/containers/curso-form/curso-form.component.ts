import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  NonNullableFormBuilder,
  Validators,
  ReactiveFormsModule,
  UntypedFormArray,
} from '@angular/forms';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { FormUtilsService } from './../../../shared/form/form-utils.service';
import { Lesson } from './../../model/lesson';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { CursosService } from '../../services/cursos.service';
import { Curso } from '../../model/curso';
@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.scss'],
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatOptionModule,
  ],
})
export class CursoFormComponent implements OnInit {
  form!: FormGroup;

  constructor(
    private readonly formBuilder: NonNullableFormBuilder,
    private readonly service: CursosService,
    private readonly snackBar: MatSnackBar,
    private readonly location: Location,
    private readonly route: ActivatedRoute,
    public formUtils: FormUtilsService
  ) {}

  ngOnInit(): void {
    const curso: Curso = this.route.snapshot.data['curso'];
    this.form = this.formBuilder.group({
      _id: [curso?._id],
      nome: [
        curso.nome,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(100),
        ],
      ],
      categoria: [curso.categoria, [Validators.required]],
      lessons: this.formBuilder.array(this.obterAulas(curso)),
    });
  }

  private obterAulas(curso: Curso) {
    const lessons = [];
    if (curso?.lessons) {
      curso.lessons.forEach((lesson) => {
        lessons.push(this.criarAula(lesson));
      });
    } else {
      lessons.push(this.criarAula());
    }
    return lessons;
  }

  private criarAula(lesson: Lesson = { id: '', nome: '', youtubeUrl: '' }) {
    return this.formBuilder.group({
      id: [lesson.id],
      nome: [
        lesson.nome,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(100),
        ],
      ],
      youtubeUrl: [
        lesson.youtubeUrl,
        [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(100),
        ],
      ],
    });
  }

  getLessonsFormArray() {
    return (<UntypedFormArray>this.form.get('lessons')).controls;
  }

  addNewLesson() {
    const lessons = this.form.get('lessons') as UntypedFormArray;
    lessons.push(this.criarAula());
  }

  removeLesson(index: number) {
    const lessons = this.form.get('lessons') as UntypedFormArray;
    lessons.removeAt(index);
  }

  onSubmit() {
    if (this.form.valid) {
      this.service.save(this.form.value).subscribe(
        (result) => this.onSuccess(),
        (error) => this.onError()
      );
    } else {
      this.formUtils.validateAllFormFields(this.form);
    }
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Curso salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar curso.', '', { duration: 5000 });
  }
}
