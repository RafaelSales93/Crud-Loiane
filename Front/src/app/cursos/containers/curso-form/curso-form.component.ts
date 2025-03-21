import { Lesson } from './../../model/lesson';
import { Component, OnInit } from '@angular/core';
import { Form, FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { CursosService } from '../../services/cursos.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Curso } from '../../model/curso';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.scss'],
})
export class CursoFormComponent implements OnInit {
  form!: FormGroup;

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: CursosService,
    private _snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const curso: Curso = this.route.snapshot.data['curso'];
    this.form = this.formBuilder.group({
        _id: [curso._id],
        nome: [curso.nome, [Validators.required, 
        Validators.minLength(5),
        Validators.maxLength(100)]],    
        categoria: [curso.categoria, ],
        lessons: this.formBuilder.array(this.obterAulas(curso),Validators.required)
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

  private criarAula(lesson: Lesson = { _id: '', nome: '', youtubeUrl: '' }) {
    return this.formBuilder.group({
      _id: [lesson._id],
      nome: [lesson.nome, [Validators.required, 
        Validators.minLength(5),
        Validators.maxLength(100)]],
      youtubeUrl: [lesson.youtubeUrl, [Validators.required, 
        Validators.minLength(10),
        Validators.maxLength(100)]],
    });
  }

  getLessonsFormArray(): UntypedFormArray {
    return this.form.get('lessons') as UntypedFormArray;
  }

  addNweLesson(){
    const lesson = this.form.get('lessons') as UntypedFormArray;
    lesson.push(this.criarAula());
  }

  removeLesson(index: number){
    const lesson = this.form.get('lessons') as UntypedFormArray;
    lesson.removeAt(index);
  }

  

  onSubmit() {
    if (this.form.valid) {
      this.service.save(this.form.value).subscribe(
        (result) => this.onSuccess(),
        (error) => this.onErro()
      );
    } else {
     alert('Formulario invalido');
    }
  }
  onCancel() {
    this.location.back();
  }

  private onErro() {
    this._snackBar.open('Erro ao salvar curso', '', { duration: 5000 });
  }
  private onSuccess() {
    const isUpdate = !!this.form.get('_id')?.value;

    const message = isUpdate
      ? 'Curso atualizado com sucesso!'
      : 'Curso salvo com sucesso!';

    this._snackBar.open(message, '', { duration: 5000 });
    this.onCancel();
  }

  getErrorMessage(fieldNome: string) {
    const field = this.form.get(fieldNome);

    if (field?.hasError('required')) {
      return 'Campo Obrigatorio';
    }
    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors
        ? field.errors['minlength']['requiredLength']
        : 5;
      return `Tamanho minimo precisa ser de ${requiredLength} caracteres.`;
    }
    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors
        ? field.errors['maxlength']['requiredLength']
        : 200;
      return `Tamanho maximo excedido de ${requiredLength} caracteres.`;
    }
    return 'Campo Inválido';
  }

  isFormArrayInvalid() {
    const lessons = this.form.get('lessons') as UntypedFormArray;
    return !lessons.valid && lessons.hasError('required') && lessons.touched;
  }

}
