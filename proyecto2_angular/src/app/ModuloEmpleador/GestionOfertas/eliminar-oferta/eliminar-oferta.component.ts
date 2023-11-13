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

@Component({
  selector: 'app-eliminar-oferta',
  templateUrl: './eliminar-oferta.component.html',
  styleUrls: ['./eliminar-oferta.component.css']
})
export class EliminarOfertaComponent implements OnInit {



  @Output() confirmado: EventEmitter<boolean> = new EventEmitter<boolean>();

  codigoOferta!: string;
  usuario!: Usuario;

  

  codigoCreado!: string;
  FormularioSolicitud!: FormGroup;
  oferta!: OfertaEliminada;
  saved: boolean;
  postulado!:boolean;


  

  constructor(private formBuilder: FormBuilder,
    private solicitudesService: SolicitudesService, private ofertaService:OfertaService, private modalService: BsModalService) {
    this.saved = false;

  }

  ngOnInit(): void {
    
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
    
    this.FormularioSolicitud = this.formBuilder.group({
      motivo: [null, [Validators.required, Validators.maxLength(200)]],
    
    });
  }

  
  submit(): void {
    if (this.FormularioSolicitud.valid) {
      this.oferta = this.FormularioSolicitud.value as OfertaEliminada;
      this.oferta.codigoOferta=this.codigoOferta;
      
      this.ofertaService.eliminarOferta(this.oferta).subscribe((borrado: boolean) => {
        if (borrado) {
          this.confirmarELiminacion();
          this.modalService.hide();  
  
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
