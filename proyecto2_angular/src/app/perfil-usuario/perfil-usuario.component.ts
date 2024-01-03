import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/entities/Categoria';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { CategoriaService } from 'src/services/CategoriaService';
import { OfertaService } from 'src/services/OfertaService';
import { UsuarioService } from 'src/services/UsuarioService';
@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']

})
export class PerfilUsuarioComponent {

 

  FormularioOferta!: FormGroup;
  saved : boolean=false;
  editar : boolean=false;

  UsuarioCreacion!:Usuario;
  codigoCreado!:string;

  usuario!: Usuario;


  constructor(private usuarioService: UsuarioService, private formBuilder: FormBuilder, private route: ActivatedRoute, private ofertaService: OfertaService, private router: Router) { }
  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    this.FormularioOferta = this.formBuilder.group({
      nombre: [this.usuario.nombre, [Validators.required, Validators.maxLength(60)]],
      usuario: [this.usuario.usuario, [Validators.required, Validators.maxLength(50)]],
      direccion: [this.usuario.direccion, [Validators.required,Validators.maxLength(100)]],
      correo: [this.usuario.correo, [Validators.required]],
      cui: [this.usuario.cui, [Validators.required, Validators.maxLength(20)]],
      birth: [this.usuario.birth, [Validators.required]],
      

    });

 

  }
  Editar(): void {
    this.editar = true;
  }

  submit(): void {
    if (this.FormularioOferta.valid) {
      this.UsuarioCreacion = this.FormularioOferta.value as Usuario;
      this.UsuarioCreacion.codigo=this.usuario.codigo;
      this.UsuarioCreacion.tipo=this.usuario.tipo;
      

    
      this.usuarioService.actualizarUsuario(this.UsuarioCreacion).subscribe({
        next: (created: Usuario) => {
          console.log("creado " + created);
          this.codigoCreado=created.codigo.toString();
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


  
}
