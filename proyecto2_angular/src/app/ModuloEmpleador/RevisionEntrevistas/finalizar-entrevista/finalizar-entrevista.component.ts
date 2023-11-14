import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Categoria } from 'src/entities/Categoria';
import { CategoriaService } from 'src/services/CategoriaService';
import { Usuario } from 'src/entities/Usuario';
import { Solicitudes } from 'src/entities/Solicitudes';
import { SolicitudesService } from 'src/services/SolicitudesService';
import { OfertaEliminada } from 'src/entities/OfertaEliminada';
import { OfertaService } from 'src/services/OfertaService';
import { BsModalService } from 'ngx-bootstrap/modal';
import { EntrevistasService } from 'src/services/EntrevistaService';
import { Entrevista } from 'src/entities/Entrevista';
@Component({
  selector: 'app-finalizar-entrevista',
  templateUrl: './finalizar-entrevista.component.html',
  styleUrls: ['./finalizar-entrevista.component.css']
})
export class FinalizarEntrevistaComponent implements OnInit {




  @Output() confirmado: EventEmitter<boolean> = new EventEmitter<boolean>();

  codigoEntrevista!: string;


  codigoOferta!: string;
  usuario!: Usuario;

  entrevista!:Entrevista;
  

  finalizar!: boolean;

  nota!: string;


  codigoCreado!: string;
  FormularioSolicitud!: FormGroup;
  oferta!: OfertaEliminada;
  saved: boolean;
  postulado!:boolean;


  

  constructor(private formBuilder: FormBuilder,
    private solicitudesService: SolicitudesService, private ofertaService:OfertaService, 
    private modalService: BsModalService, private entrevistaService:EntrevistasService) {
    this.saved = false;

  }

  ngOnInit(): void {
    
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
    
    this.FormularioSolicitud = this.formBuilder.group({
      nota: [null, [Validators.required, Validators.maxLength(500)]],
    
    });
  }

  
  submit(): void {
    if (this.FormularioSolicitud.valid) {
      this.entrevista = this.FormularioSolicitud.value as Entrevista;
      this.entrevista.codigo = this.codigoEntrevista;
      
      this.entrevistaService.finalizarEntrevista(this.entrevista).subscribe( {
          next: (created: Entrevista) => {
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
    this.FormularioSolicitud.reset({

    });

  }
  confirmarELiminacion() {
    this.confirmado.emit(true);

  }

}
