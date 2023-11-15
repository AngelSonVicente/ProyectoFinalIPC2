import { Component } from '@angular/core';
import { Oferta } from 'src/entities/Oferta';
import { SolicitudRetirada } from 'src/entities/SolicitudRetirada';
import { Usuario } from 'src/entities/Usuario';
import { ReportesService } from 'src/services/ReportesService';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { DetallesOfertasComponent } from 'src/app/ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';
import { Entrevista } from 'src/entities/Entrevista';

@Component({
  selector: 'app-reporte-e2',
  templateUrl: './reporte-e2.component.html',
  styleUrls: ['./reporte-e2.component.css']
})
export class ReporteE2Component {
  reporte2!: Entrevista[];
  usuario!: Usuario;
  
  
  modalRef!: BsModalRef;
  fecha11!:string;
  fecha22!:string;
  constructor(private reportesService: ReportesService, private modalService: BsModalService) { }
  
    ngOnInit(): void {
  
      let jsonUsuario = localStorage.getItem('usuario');
      this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
    
    }
  
    realizarAccion(datosFormulario: any): void {
      // Verifica si las fechas están definidas y realiza la acción correspondiente
      if (datosFormulario.fecha1) {
        console.log('Fechas seleccionadas:', datosFormulario.fecha1, datosFormulario.fecha2);
        // Llama a tu función con las fechas seleccionadas
        this.reportesService.getReporte2Empleador(this.usuario.codigo.toString(), datosFormulario.fecha1 ).subscribe(data => {
          this.reporte2 = data;
        });
      } else {
        this.fecha11="nada";
  
        console.log('No se seleccionaron fechas. Se utilizarán valores nulos.');
        this.reportesService.getReporte2Empleador(this.usuario.codigo.toString(),this.fecha11 ).subscribe(data => {
          this.reporte2 = data;
        });
        // Llama a tu función con valores nulos o realiza la acción correspondiente
      }
    
  
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

