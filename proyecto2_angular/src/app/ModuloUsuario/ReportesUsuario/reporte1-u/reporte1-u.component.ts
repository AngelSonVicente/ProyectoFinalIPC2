import { Component } from '@angular/core';
import { SolicitudRetirada } from 'src/entities/SolicitudRetirada';
import { Usuario } from 'src/entities/Usuario';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reporte1-u',
  templateUrl: './reporte1-u.component.html',
  styleUrls: ['./reporte1-u.component.css']
})
export class Reporte1UComponent {
  reporte1!: SolicitudRetirada[];
usuario!: Usuario;

fecha11!:string;
fecha22!:string;
constructor(private reportesService: ReportesService) { }

  ngOnInit(): void {

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
  
  }

  realizarAccion(datosFormulario: any): void {
    // Verifica si las fechas están definidas y realiza la acción correspondiente
    if (datosFormulario.fecha1 && datosFormulario.fecha2) {
      console.log('Fechas seleccionadas:', datosFormulario.fecha1, datosFormulario.fecha2);
      // Llama a tu función con las fechas seleccionadas
      this.reportesService.getReporte1Usuario(this.usuario.codigo.toString(), datosFormulario.fecha1 , datosFormulario.fecha2 ).subscribe(data => {
        this.reporte1 = data;
      });
    } else {
      this.fecha11="nada";
      this.fecha22="nada";

      console.log('No se seleccionaron fechas. Se utilizarán valores nulos.');
      this.reportesService.getReporte1Usuario(this.usuario.codigo.toString(),this.fecha11 ,this.fecha22 ).subscribe(data => {
        this.reporte1 = data;
      });
      // Llama a tu función con valores nulos o realiza la acción correspondiente
    }
  

  }

}
