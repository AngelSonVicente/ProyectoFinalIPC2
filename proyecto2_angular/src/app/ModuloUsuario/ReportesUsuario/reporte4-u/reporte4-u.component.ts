import { Component } from '@angular/core';
import { Solicitudes } from 'src/entities/Solicitudes';
import { Usuario } from 'src/entities/Usuario';
import { ReportesPDFService } from 'src/services/ReportesPDFService';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reporte4-u',
  templateUrl: './reporte4-u.component.html',
  styleUrls: ['./reporte4-u.component.css']
})
export class Reporte4UComponent {
  downloadUrl: String;

  
  reporte2!: Solicitudes[];
  usuario!: Usuario;
  
  fecha11!:string;
  fecha22!:string;
  constructor(private reportesService: ReportesService, private fileService: ReportesPDFService) { 

    this.downloadUrl = '';

  }
  
    ngOnInit(): void {
  
      let jsonUsuario = localStorage.getItem('usuario');
      this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
    
      this.reportesService.getReporte4Usuario(this.usuario.codigo.toString() ).subscribe(data => {
        this.reporte2 = data;
      });

      this.downloadUrl = this.fileService.getReporte4Usuario(this.usuario.codigo.toString());

    }
}
