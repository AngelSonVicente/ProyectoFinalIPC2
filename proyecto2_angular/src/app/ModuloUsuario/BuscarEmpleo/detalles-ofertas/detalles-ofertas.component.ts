import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Oferta } from 'src/entities/Oferta';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { OfertaService } from 'src/services/OfertaService';
import { PostulacionDiaglogComponent } from '../postulacion-diaglog/postulacion-diaglog.component';
import { SolicitudesService } from 'src/services/SolicitudesService';
import { Usuario } from 'src/entities/Usuario';


@Component({
  selector: 'app-detalles-ofertas',
  templateUrl: './detalles-ofertas.component.html',
  styleUrls: ['./detalles-ofertas.component.css']
})
export class DetallesOfertasComponent implements OnInit {
  usuario!: Usuario;
  codigo!: string;
  oferta!: Oferta;
  modalRef!: BsModalRef;
  postulado!:boolean;



  constructor(private route: ActivatedRoute, private ofertaService: OfertaService, private modalService: BsModalService, private solicitudService:SolicitudesService) {}

  ngOnInit() {

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.route.params.subscribe((params) => {
      this.codigo = params['codigo'];
    });
  
    this.ofertaService.getOferta(this.codigo).subscribe({
      next: (oferta: Oferta) => {
        this.oferta = oferta;

  
      }
    });

    this.solicitudService.ExisteSolicitud(this.codigo, this.usuario.codigo.toString()).subscribe((existe: boolean) => {
      if (existe) {
    
        this.postulado=true;
        
      } else {
     
        this.postulado=false;
      }
    });
  
  }

  abrirModal() {
    const initialState = {
      codigoOferta: this.oferta.codigo
    };
  
    this.modalRef = this.modalService.show(PostulacionDiaglogComponent, { initialState });
  
    this.modalRef.content.confirmado.subscribe((postuladoo: boolean) => {
      if (postuladoo) {
        this.postulado = true;

      } else {
        this.postulado = false;

   

      }
    });
  }


 


}
