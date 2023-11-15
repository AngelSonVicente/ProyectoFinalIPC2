import { Component, OnInit } from '@angular/core';
import { Oferta } from 'src/entities/Oferta';
import { SolicitudRetirada } from 'src/entities/SolicitudRetirada';
import { Usuario } from 'src/entities/Usuario';
import { ReportesService } from 'src/services/ReportesService';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { DetallesOfertasComponent } from 'src/app/ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';
import { HistorialCobros } from 'src/entities/HistorialCobros';

@Component({
  selector: 'app-reporte-e3',
  templateUrl: './reporte-e3.component.html',
  styleUrls: ['./reporte-e3.component.css']
})
export class ReporteE3Component implements OnInit {
  reporte3!: HistorialCobros[];
  usuario!: Usuario;
  
  
  modalRef!: BsModalRef;
  fecha11!:string;
  fecha22!:string;

  constructor(private reportesService: ReportesService, private modalService: BsModalService) { }

  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;  
    // Llama a tu función con las fechas seleccionadas
    this.reportesService.getReporte3Empleador(this.usuario.codigo.toString()).subscribe(data => {
      this.reporte3 = data;
    });
  }
}

