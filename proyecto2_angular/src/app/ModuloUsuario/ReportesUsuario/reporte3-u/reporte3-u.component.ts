import { Component } from '@angular/core';
import { Solicitudes } from 'src/entities/Solicitudes';
import { Usuario } from 'src/entities/Usuario';
import { ReportesService } from 'src/services/ReportesService';
@Component({
  selector: 'app-reporte3-u',
  templateUrl: './reporte3-u.component.html',
  styleUrls: ['./reporte3-u.component.css']
})
export class Reporte3UComponent {

  reporte2!: Solicitudes[];
  usuario!: Usuario;
  
  fecha11!:string;
  fecha22!:string;
  constructor(private reportesService: ReportesService) { }
  
    ngOnInit(): void {
  
      let jsonUsuario = localStorage.getItem('usuario');
      this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
    
      this.reportesService.getReporte3Usuario(this.usuario.codigo.toString() ).subscribe(data => {
        this.reporte2 = data;
      });

    }
}
