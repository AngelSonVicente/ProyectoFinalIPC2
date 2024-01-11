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
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.css']
})
export class RegistrarseComponent implements OnInit {

  FormularioOferta!: FormGroup;
  saved : boolean=false;

  error: boolean = false;
  UsuarioCreacion!:Usuario;
  codigoCreado!:string;

  usuario!: Usuario;


  constructor(private usuarioService: UsuarioService, private formBuilder: FormBuilder, private route: ActivatedRoute, private ofertaService: OfertaService, private router: Router) { }
  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    this.FormularioOferta = this.formBuilder.group({
      nombre: [null, [Validators.required, Validators.maxLength(60)]],
      usuario: [null, [Validators.required, Validators.maxLength(50)]],
      direccion: [null, [Validators.required,Validators.maxLength(100)]],
      correo: [null, [Validators.required]],
      password: [null, [Validators.required,Validators.maxLength(100)]],
      cui: [null, [Validators.required, Validators.maxLength(20)]],
      birth: [null, [Validators.required]],
      tipo: [null, [Validators.required]],
      

    });

 

  }

  submit(): void {
    if (this.FormularioOferta.valid) {
      this.UsuarioCreacion = this.FormularioOferta.value as Usuario;

    
      this.usuarioService.crearUsuario(this.UsuarioCreacion).subscribe({
        next: (created: Usuario) => {
          console.log("creado " + created);
          this.codigoCreado=created.codigo.toString();
          this.saved = true;
          this.error=false;
          this.limpiar()
        },
        error: (error: any) => {
          console.log("error");
          this.error=true;

        }
      });
    }
  }
  limpiar(): void {
    this.FormularioOferta.reset({

    });

  }



}
