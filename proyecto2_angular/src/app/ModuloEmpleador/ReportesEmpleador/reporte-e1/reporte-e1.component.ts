import { Component } from '@angular/core';
import { Oferta } from 'src/entities/Oferta';
import { SolicitudRetirada } from 'src/entities/SolicitudRetirada';
import { Usuario } from 'src/entities/Usuario';
import { ReportesService } from 'src/services/ReportesService';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { DetallesOfertasComponent } from 'src/app/ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';
import { ReportesPDFService } from 'src/services/ReportesPDFService';

@Component({
  selector: 'app-reporte-e1',
  templateUrl: './reporte-e1.component.html',
  styleUrls: ['./reporte-e1.component.css']
})
export class ReporteE1Component {
  reporte1!: Oferta[];
  usuario!: Usuario;
  downloadUrl: string;


  modalRef!: BsModalRef;
  fecha1: string = "sinfecha";
  fecha2: string = "sinfecha";
  constructor(private fileService: ReportesPDFService,private reportesService: ReportesService, private modalService: BsModalService) {
    this.downloadUrl = '';
   }

  ngOnInit(): void {

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

  }

  realizarAccion(): void {
   
      console.log('No se seleccionaron fechas. Se utilizarán valores nulos.');
      this.reportesService.getReporte1Empleador(this.usuario.codigo.toString(), this.fecha1, this.fecha2).subscribe(data => {
        this.reporte1 = data;
      });
   


  }

  PDF() {
    this.downloadUrl = this.fileService.getReport1Empleador(this.fecha1, this.fecha2,this.usuario.codigo.toString());
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
