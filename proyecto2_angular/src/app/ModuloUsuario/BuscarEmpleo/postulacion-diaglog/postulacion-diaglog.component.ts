import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Categoria } from 'src/entities/Categoria';
import { CategoriaService } from 'src/services/CategoriaService';
import { Usuario } from 'src/entities/Usuario';
import { Solicitudes } from 'src/entities/Solicitudes';
import { SolicitudesService } from 'src/services/SolicitudesService';
@Component({
  selector: 'app-postulacion-diaglog',
  templateUrl: './postulacion-diaglog.component.html',
  styleUrls: ['./postulacion-diaglog.component.css']
})
export class PostulacionDiaglogComponent implements OnInit {

  @Output() confirmado: EventEmitter<boolean> = new EventEmitter<boolean>();

  codigoOferta!: string;
  usuario!: Usuario;
 

  codigoCreado!: string;
  FormularioSolicitud!: FormGroup;
  solicitud!: Solicitudes;
  saved: boolean;
  postulado!:boolean;


  

  constructor(private formBuilder: FormBuilder,
    private solicitudesService: SolicitudesService) {
    this.saved = false;

  }

  ngOnInit(): void {
    
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.FormularioSolicitud = this.formBuilder.group({
      mensaje: [null, [Validators.required, Validators.maxLength(200)]],
    
    });
  }

  
  submit(): void {
    if (this.FormularioSolicitud.valid) {
      this.solicitud = this.FormularioSolicitud.value as Solicitudes;
      this.solicitud.codigoOferta=this.codigoOferta;
      this.solicitud.codigoUsuario = this.usuario.codigo.toString();

      this.solicitudesService.crearSolicitud(this.solicitud).subscribe({
        next: (created: Solicitudes) => {
          this.codigoCreado=created.codigo;
          console.log("creado " + created);
          this.limpiar();
          this.saved = true;
          this.confirmarPostulacion();
        },
        error: (error: any) => {
          console.log("error");
        }
      });
    }
  }

  limpiar(): void {
    this.FormularioSolicitud.reset({

    });

  }
  confirmarPostulacion() {
    this.confirmado.emit(true);

  }
  

}
