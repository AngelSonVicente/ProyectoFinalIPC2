import { Component, OnInit } from '@angular/core';
import { Solicitudes } from 'src/entities/Solicitudes';
import { Usuario } from 'src/entities/Usuario';
import { SolicitudesService } from 'src/services/SolicitudesService';

@Component({
  selector: 'app-postulaciones-realizadas',
  templateUrl: './postulaciones-realizadas.component.html',
  styleUrls: ['./postulaciones-realizadas.component.css']
})
export class PostulacionesRealizadasComponent implements OnInit{

  eliminado!: boolean;
  solicitudes: Solicitudes[] = [];
  usuario!: Usuario;
  constructor(private solicitudesService: SolicitudesService){}

  confirmarEliminar(codigo: string) {
    const confirmacion = window.confirm('¿Estás seguro de que quieres eliminar esta postulación?');
  
    if (confirmacion) {
      this.eliminar(codigo);
    }
  }
  
  eliminar(codigoSoli: string) {
    this.solicitudesService.BorrarSolicitud(codigoSoli).subscribe((borrado: boolean) => {
      if (borrado) {
        window.location.reload();
        
      } 
    });
  }

  ngOnInit(): void{
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.solicitudesService.getSolicitudesUsuario(this.usuario.codigo.toString()).subscribe({

      next: (list: Solicitudes[]) => {
        this.solicitudes = list;
      }
    });

  }

}
