import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/entities/Categoria';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { CategoriaService } from 'src/services/CategoriaService';
import { OfertaService } from 'src/services/OfertaService';
import { PreferenciasService } from 'src/services/PreferenciasService';
import { TelefonosService } from 'src/services/TelefonosService';
import { UsuarioService } from 'src/services/UsuarioService';
@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.component.html',
  styleUrls: ['./perfil-usuario.component.css']

})
export class PerfilUsuarioComponent {



  categorias!: Categoria[];
  FormularioOferta!: FormGroup;
  saved: boolean = false;
  editarInformacion: boolean = false;
  editarTelefonoCategoria: boolean = false;
  editarCv: boolean = false;
  cambiarContrasena: boolean = false;

  UsuarioCreacion!: Usuario;
  codigoCreado!: string;

  usuario!: Usuario;
  telefonos: string[] = [];
  categoriaPreferencia: string[] = [];




  constructor(private categoriaService: CategoriaService, private usuarioService: UsuarioService, private formBuilder: FormBuilder,
    private route: ActivatedRoute, private ofertaService: OfertaService,
    private router: Router, private telefonosService: TelefonosService, private preferenciaService: PreferenciasService) { }
  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    this.FormularioOferta = this.formBuilder.group({
      nombre: [this.usuario.nombre, [Validators.required, Validators.maxLength(60)]],
      direccion: [this.usuario.direccion, [Validators.required, Validators.maxLength(100)]],
      correo: [this.usuario.correo, [Validators.required]],
      cui: [this.usuario.cui, [Validators.required, Validators.maxLength(20)]],
      birth: [this.usuario.birth, [Validators.required]],


    });

    this.categoriaService.getCategorias().subscribe({

      next: (list: Categoria[]) => {
        this.categorias = list;
      }
    });
 


    this.getPerfilUsuario();







  }

  obtenerNombreCategoria(codigoCategoria: string | number): string {
    const categoriaEncontrada = this.categorias.find(categoria => categoria.codigo.toString() === String(codigoCategoria));
    return categoriaEncontrada ? categoriaEncontrada.nombre : 'Desconocida';
  }

  getPerfilUsuario() {
    this.preferenciaService.getPreferencias(this.usuario.codigo.toString()).subscribe({

      next: (list: string[]) => {
        this.categoriaPreferencia = list;
      }
    });



 
    this.telefonosService.getTelefonos(this.usuario.codigo.toString()).subscribe({

      next: (list: string[]) => {
        this.telefonos = list;
      }
    });
 
 
  }


  EditarInformacion(): void {
    this.editarInformacion = true;
    this.editarCv = false;
    this.editarTelefonoCategoria = false;
    this.cambiarContrasena = false;
  }

  EditarTelefonoCategoria(): void {
    this.editarInformacion = false;
    this.editarCv = false;
    this.editarTelefonoCategoria = true;
    this.cambiarContrasena = false;

  }

  EditarCV(): void {
    this.editarInformacion = false;
    this.editarCv = true;
    this.editarTelefonoCategoria = false;
    this.cambiarContrasena = false;

  }

  CambiarContrasena(): void {
    this.editarInformacion = false;
    this.editarCv = false;
    this.editarTelefonoCategoria = false;
    this.cambiarContrasena = true;

  }



  submit(): void {
    if (this.FormularioOferta.valid) {
      this.UsuarioCreacion = this.FormularioOferta.value as Usuario;
      this.UsuarioCreacion.codigo = this.usuario.codigo;
      this.UsuarioCreacion.tipo = this.usuario.tipo;



      this.usuarioService.actualizarUsuario(this.UsuarioCreacion).subscribe({
        next: (created: Usuario) => {
          console.log("creado " + created);
          this.codigoCreado = created.codigo.toString();
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


  recibirUsuario(usuarioActualizado: Usuario) {
    this.usuario = usuarioActualizado;
  }
  actualizoCategoriasTelefonos() {


    this.getPerfilUsuario();


  }





}
