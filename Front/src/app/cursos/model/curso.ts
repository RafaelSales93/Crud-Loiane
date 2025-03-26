import { Lesson } from './lesson';
export interface Curso {
    _id: string
    nome: string;
    categoria: string;
    lessons?: Lesson[];
}
