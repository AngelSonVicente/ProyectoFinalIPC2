import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/entities/Categoria';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { CategoriaService } from 'src/services/CategoriaService';
import { OfertaService } from 'src/services/OfertaService';

@Component({
  selector: 'app-crear-oferta',
  templateUrl: './crear-oferta.component.html',
  styleUrls: ['./crear-oferta.component.css']
})
export class CrearOfertaComponent implements OnInit {
  FormularioOferta!: FormGroup;
  saved : boolean=false;
  oferta!:Oferta;
  codigoCreado!:string;
  categorias!:Categoria[];

  usuario!: Usuario;


  constructor(private categoriaService:CategoriaService, private formBuilder: FormBuilder, private route: ActivatedRoute, private ofertaService: OfertaService, private router: Router) { }
  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    this.FormularioOferta = this.formBuilder.group({
      nombre: [null, [Validators.required, Validators.maxLength(50)]],
      descripcion: [null, [Validators.required, Validators.maxLength(500)]],
      categoria: [null, [Validators.required]],
      fechaLimite: [null, [Validators.required]],
      salario: [null, [Validators.required,this.validarNumeroMayorQueCero]],
      modadidad: [null, [Validators.required, Validators.maxLength(30)]],
      ubicacion: [null, [Validators.required, Validators.maxLength(100)]],
      detalle: [null, [Validators.required, Validators.maxLength(2000)]],
    

    });

    this.categoriaService.getCategorias().subscribe({

      next: (list: Categoria[]) => {
        this.categorias = list;
      }
    });

  }

  submit(): void {
    if (this.FormularioOferta.valid) {
      this.oferta = this.FormularioOferta.value as Oferta;

      this.oferta.codigoEmpresa = this.usuario.codigo.toString();

      this.ofertaService.crearOferta(this.oferta).subscribe({
        next: (created: Oferta) => {
          console.log("creado " + created);
          this.codigoCreado=created.codigo;
          this.saved = true;
          this.limpiar()
        },
        error: (error: any) => {
          console.log("error");
        }
      });
    }
  }
  limpiar(): void {
    this.FormularioOferta.reset({

    });

  }
  
validarNumeroMayorQueCero(control: any) {
  if (isNaN(control.value) || control.value <= 0) {
    return { 'numeroInvalido': true };
  }
  return null;
}


}
