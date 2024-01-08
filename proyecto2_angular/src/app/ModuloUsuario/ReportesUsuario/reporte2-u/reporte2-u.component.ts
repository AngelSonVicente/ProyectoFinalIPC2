import { Component } from '@angular/core';
import { Solicitudes } from 'src/entities/Solicitudes';
import { Usuario } from 'src/entities/Usuario';
import { ReportesPDFService } from 'src/services/ReportesPDFService';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reporte2-u',
  templateUrl: './reporte2-u.component.html',
  styleUrls: ['./reporte2-u.component.css']
})
export class Reporte2UComponent {

  reporte2!: Solicitudes[];
  usuario!: Usuario;
  downloadUrl: String;
  
  fecha11!:string;
  fecha22!:string;
  constructor(private reportesService: ReportesService, private fileService: ReportesPDFService) {

    this.downloadUrl = '';

   }
  
    ngOnInit(): void {
  
      let jsonUsuario = localStorage.getItem('usuario');
      this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
    
      this.reportesService.getReporte2Usuario(this.usuario.codigo.toString() ).subscribe(data => {
        this.reporte2 = data;
      });

      this.downloadUrl = this.fileService.getReporte2Usuario(this.usuario.codigo.toString());
    
    }

}
