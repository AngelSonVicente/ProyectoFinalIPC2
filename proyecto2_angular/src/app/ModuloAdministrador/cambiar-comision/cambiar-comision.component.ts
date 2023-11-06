import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from 'src/entities/Usuario';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Categoria } from 'src/entities/Categoria';
import { CategoriaService } from 'src/services/CategoriaService';
import { ComisionService } from 'src/services/ComisionService'

import { Comision } from 'src/entities/Comision';


@Component({
  selector: 'app-cambiar-comision',
  templateUrl: './cambiar-comision.component.html',
  styleUrls: ['./cambiar-comision.component.css']
})
export class CambiarComisionComponent {

  usuario!: Usuario;
  codigo!: string;
 
  FormularioComision!: FormGroup;
  comision!: Comision;
  saved: boolean;

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder,
    private comisionService: ComisionService) {
    this.saved = false;
   }

  ngOnInit() {
  
     let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

  this.comisionService.getComision("ultimo").subscribe({
    next: (comision: Comision) => {
      this.comision = comision;
     
    
      this.FormularioComision = this.formBuilder.group({
        comision: [this.comision.comision, [Validators.required, this.validarNumeroMayorQueCero]],
       
      });

    }
  });


 
}


validarNumeroMayorQueCero(control: any) {
  if (isNaN(control.value) || control.value <= 0) {
    return { 'numeroInvalido': true };
  }
  return null;
}



  submit(): void {
    if (this.FormularioComision.valid) {
      this.comision = this.FormularioComision.value as Comision;
      this.comisionService.crearComision(this.comision).subscribe({
        next: (created: Comision) => {
          console.log("creado " + created);
          this.saved = true;
        },
        error: (error: any) => {
          console.log("error");
        }
      });
    }
  }
  limpiar(): void {
    this.FormularioComision.reset({

    });

  }



}
