import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from 'src/entities/Usuario';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Categoria } from 'src/entities/Categoria';
import { CategoriaService } from 'src/services/CategoriaService';


@Component({
  selector: 'app-editar-categoria',
  templateUrl: './editar-categoria.component.html',
  styleUrls: ['./editar-categoria.component.css']
})
export class EditarCategoriaComponent {
  usuario!: Usuario;
  codigo!: string;
 
  FormularioCategoria!: FormGroup;
  categoria!: Categoria;
  saved: boolean;

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder,
    private categoriaService: CategoriaService) {
    this.saved = false;
   }

  ngOnInit() {
     const cod = this.route.snapshot.paramMap.get('codigo');
   let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
  if (cod !== null) {
    this.codigo = cod;
  } else {
    this.codigo = 'sinCodigo';
  }


  this.categoriaService.getCategoria(this.codigo).subscribe({
    next: (categoria: Categoria) => {
      this.categoria = categoria;
     
    
      this.FormularioCategoria = this.formBuilder.group({
        nombre: [this.categoria.nombre, [Validators.required, Validators.maxLength(100)]],
        descripcion: [this.categoria.descripcion, [Validators.required, Validators.maxLength(200)]],
      
      });

    }
  });


 
}

  submit(): void {
    if (this.FormularioCategoria.valid) {
      this.categoria = this.FormularioCategoria.value as Categoria;
      this.categoria.codigo=parseInt(this.codigo);
      this.categoriaService.actualizarCategoria(this.categoria).subscribe({
        next: (created: Categoria) => {
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
    this.FormularioCategoria.reset({

    });

  }




  
  }


