import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'category'
})
export class CategoryPipe implements PipeTransform {

  transform(value: string): string {
    switch(value){
      case 'fronte-end': return'code'
      case 'back-end': return'computer'
    }
    return 'code';
  }

}
