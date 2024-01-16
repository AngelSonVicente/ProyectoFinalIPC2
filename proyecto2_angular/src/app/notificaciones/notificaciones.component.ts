import { Component, EventEmitter, Output } from '@angular/core';
import { Notificaciones } from 'src/entities/Notificaciones';
import { Usuario } from 'src/entities/Usuario';
import { notificacionesService } from 'src/services/NotificacionesService';

@Component({
  selector: 'app-notificaciones',
  templateUrl: './notificaciones.component.html',
  styleUrls: ['./notificaciones.component.css']
})
export class NotificacionesComponent {
  @Output() marcarComoLeido: EventEmitter<boolean> = new EventEmitter<boolean>();

  notificaciones: Notificaciones[] = [];
  

  usuario!: Usuario;


 constructor(private notificacionesService: notificacionesService) { }
  
    ngOnInit(): void {
      let jsonUsuario = localStorage.getItem('usuario');
      this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
  
      this.notificacionesService.getNotificaciones(this.usuario.codigo.toString()).subscribe({

        next: (list: Notificaciones[]) => {
          this.notificaciones = list;

          this.notificaciones = this.notificaciones.sort((a, b) => {
            const fechaA = new Date(a.fecha).getTime(); // Convertir cadena a tiempo en milisegundos
            const fechaB = new Date(b.fecha).getTime();
            return fechaB - fechaA;
          });
        }
      });


    
    }
    MarcarComoLeido(){


      this.notificacionesService.MarcarComoLeido(this.usuario.codigo.toString()).subscribe({

        next: () => {
          
          this.notificacionesService.getNotificaciones(this.usuario.codigo.toString()).subscribe({

            next: (list: Notificaciones[]) => {
              this.notificaciones = list;

              this.notificaciones = this.notificaciones.sort((a, b) => {
                const fechaA = new Date(a.fecha).getTime(); // Convertir cadena a tiempo en milisegundos
                const fechaB = new Date(b.fecha).getTime();
                return fechaB - fechaA;
              });

              
            }
          });

        }


      });

     this.emitirNotificacionesMarcarComoleido();
         

    }

    


     getDiasNotificacion(fechaString: string): string {
      const hoy = new Date();
      const fechaEntrada = new Date(fechaString);
      hoy.setHours(0, 0, 0, 0);
      fechaEntrada.setHours(0, 0, 0, 0);
      const diferenciaEnMilisegundos = hoy.getTime() - fechaEntrada.getTime();
      const diferenciaEnDias = Math.floor(diferenciaEnMilisegundos / (1000 * 60 * 60 * 24));
    
      if (diferenciaEnDias === 0) {
        return 'Hoy';
      } else if (diferenciaEnDias === 1) {
        return 'Ayer';
      } else {
        return `Hace ${diferenciaEnDias} días`;
      }
    }


    emitirNotificacionesMarcarComoleido() {
      this.marcarComoLeido.emit(true);
    }


}
