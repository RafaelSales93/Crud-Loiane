
import { Injectable } from '@angular/core';
import { FormArray, UntypedFormArray, UntypedFormControl, UntypedFormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class FormUtilsService {

  constructor() { }

  validateAllFormFields(formGroup: UntypedFormGroup | UntypedFormArray) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);

      if (control instanceof UntypedFormControl) {
        control.markAsTouched({ onlySelf: true });        
      } else if (control instanceof UntypedFormGroup || control instanceof FormArray) {
        control.markAsTouched({ onlySelf: true });
        this.validateAllFormFields(control);
      }
    });
  }
    
  


  getErrorMessage(formGroup: UntypedFormGroup, fieldNome: string) {
    const field = formGroup.get(fieldNome) as UntypedFormControl;
    return this.getErrorMessageFormField(field);
  }

  getErrorMessageFormField(field: UntypedFormControl) {
   
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

  getFormArrayErrorMessage(formGroup: UntypedFormGroup, formArrayNome: string,
    fieldNome: string, index: number) {
    const formArray = formGroup.get(formArrayNome) as UntypedFormArray;
    const field = formArray.controls[index].get(fieldNome) as UntypedFormControl;
    return this.getErrorMessageFormField(field);
  }

  isFormArrayRequired(formGroup: UntypedFormGroup, formArrayNome: string) {
    const formArray = formGroup.get(formArrayNome) as UntypedFormArray;
    return !formArray.valid && formArray.hasError('required') && formArray.touched;
  }

}
