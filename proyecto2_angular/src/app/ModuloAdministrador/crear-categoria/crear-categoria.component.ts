import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Categoria } from 'src/entities/Categoria';
import { CategoriaService } from 'src/services/CategoriaService';
import { Usuario } from 'src/entities/Usuario';
declare var bootstrap: any;


@Component({
  selector: 'app-crear-categoria',
  templateUrl: './crear-categoria.component.html',
  styleUrls: ['./crear-categoria.component.css']
})
export class CrearCategoriaComponent implements OnInit  {
  usuario!: Usuario;

  codigoCreado!: number;
  FormularioCategoria!: FormGroup;
  categoria!: Categoria;
  saved: boolean;

  constructor(private formBuilder: FormBuilder,
    private categoriaService: CategoriaService) {
    this.saved = false;

  }


  ngOnInit(): void {
    
  
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.FormularioCategoria = this.formBuilder.group({
      nombre: [null, [Validators.required, Validators.maxLength(100)]],
      descripcion: [null, [Validators.required, Validators.maxLength(200)]],
    
    });
  }


  submit(){
  

    if (this.FormularioCategoria.valid) {
      this.categoria = this.FormularioCategoria.value as Categoria;
      this.categoriaService.crearCategoria(this.categoria).subscribe({
        next: (created: Categoria) => {
          this.codigoCreado=created.codigo;
          console.log("creado " + created);
          this.limpiar();
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
