import { Component } from '@angular/core';
import { Oferta } from 'src/entities/Oferta';
import { SolicitudRetirada } from 'src/entities/SolicitudRetirada';
import { Usuario } from 'src/entities/Usuario';
import { ReportesService } from 'src/services/ReportesService';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { DetallesOfertasComponent } from 'src/app/ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';
import { Entrevista } from 'src/entities/Entrevista';
import { ReportesPDFService } from 'src/services/ReportesPDFService';

@Component({
  selector: 'app-reporte-e2',
  templateUrl: './reporte-e2.component.html',
  styleUrls: ['./reporte-e2.component.css']
})
export class ReporteE2Component {
  reporte2!: Entrevista[];
  usuario!: Usuario;
  fecha1: string = "sinfecha";
  downloadUrl: string;


  modalRef!: BsModalRef;
  fecha11!: string;
  fecha22!: string;
  constructor(private fileService: ReportesPDFService, private reportesService: ReportesService, private modalService: BsModalService) {
    this.downloadUrl = '';
  }

  ngOnInit(): void {

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

  }

  realizarAccion(): void {

    console.log('No se seleccionaron fechas. Se utilizarán valores nulos.');
    this.reportesService.getReporte2Empleador(this.usuario.codigo.toString(), this.fecha1).subscribe(data => {
      this.reporte2 = data;
    });



  }
  PDF() {
    this.downloadUrl = this.fileService.getReporte2Empleador(this.fecha1, this.usuario.codigo.toString());
  
  }

  abrirModal(codigoOfertaa: string) {
    //manda todas las variables al modal
    const initialState = {
      codigo: codigoOfertaa

    };
    //abre el modal

    this.modalRef = this.modalService.show(DetallesOfertasComponent, { initialState });

  }

}

