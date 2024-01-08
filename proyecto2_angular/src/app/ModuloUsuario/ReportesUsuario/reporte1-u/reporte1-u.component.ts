import { Component } from '@angular/core';
import { SolicitudRetirada } from 'src/entities/SolicitudRetirada';
import { Usuario } from 'src/entities/Usuario';
import { ReportesPDFService } from 'src/services/ReportesPDFService';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reporte1-u',
  templateUrl: './reporte1-u.component.html',
  styleUrls: ['./reporte1-u.component.css']
})
export class Reporte1UComponent {
  reporte1!: SolicitudRetirada[];
usuario!: Usuario;

fecha1:string = "sinfecha";
fecha2:string= "sinfecha";

downloadUrl: string;


constructor(private fileService: ReportesPDFService,private reportesService: ReportesService) { 
  this.downloadUrl = '';
}

  ngOnInit(): void {

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
  
  }

  realizarAccion(): void {
 
      this.reportesService.getReporte1Usuario(this.usuario.codigo.toString(),this.fecha1 ,this.fecha2 ).subscribe(data => {
        this.reporte1 = data;
      });
    }

    PDF() {
      this.downloadUrl = this.fileService.getReporte1Usuario(this.fecha1, this.fecha2,this.usuario.codigo.toString());
    
    }
  

  

}
