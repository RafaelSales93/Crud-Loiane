import { Lesson } from './lesson';
export interface Curso {
    _id: string
    nome: string;
    category: string;
    lessons?: Lesson[];
}
