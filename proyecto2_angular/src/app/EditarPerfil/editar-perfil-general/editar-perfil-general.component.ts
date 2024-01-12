
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/entities/Categoria';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { CategoriaService } from 'src/services/CategoriaService';
import { OfertaService } from 'src/services/OfertaService';
import { UsuarioService } from 'src/services/UsuarioService';

@Component({
  selector: 'app-editar-perfil-general',
  templateUrl: './editar-perfil-general.component.html',
  styleUrls: ['./editar-perfil-general.component.css']
})
export class EditarPerfilGeneralComponent {
  @Output() usuarioActualizado: EventEmitter<Usuario> = new EventEmitter();


 

  FormularioOferta!: FormGroup;
  saved : boolean=false;
  editar : boolean=false;
  error: boolean=false;

  UsuarioCreacion!:Usuario;
  codigoCreado!:string;

  usuario!: Usuario;


  constructor(private usuarioService: UsuarioService, private formBuilder: FormBuilder, private route: ActivatedRoute, private ofertaService: OfertaService, private router: Router) { }
  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    this.FormularioOferta = this.formBuilder.group({
      nombre: [this.usuario.nombre, [Validators.required, Validators.maxLength(60)]],
      direccion: [this.usuario.direccion, [Validators.required,Validators.maxLength(100)]],
      correo: [this.usuario.correo, [Validators.required,Validators.maxLength(69)]],
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
     
          this.usuario = created;
          localStorage.setItem('usuario', JSON.stringify(this.usuario));
         

          this.saved = true;
          this.error=false;

         this.emitirUsuarioActualizado(this.usuario);
          
        },
        error: (error: any) => {
          this.error=true;
        }
      });
    }
  }

  emitirUsuarioActualizado(usuario: Usuario) {
    this.usuarioActualizado.emit(usuario);
  }



}
