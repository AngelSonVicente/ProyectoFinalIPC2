import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Usuario } from 'src/entities/Usuario';
import { ActivatedRoute } from '@angular/router';
import { SolicitudesService } from 'src/services/SolicitudesService';
import { Solicitudes } from 'src/entities/Solicitudes';
import { InformacionUsuarioComponent } from '../informacion-usuario/informacion-usuario.component';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { interval, Subject } from 'rxjs';
import { takeUntil, switchMap, map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-postulaciones-oferta',
  templateUrl: './postulaciones-oferta.component.html',
  styleUrls: ['./postulaciones-oferta.component.css']
})
export class PostulacionesOfertaComponent implements OnInit, OnDestroy {
  usuario!: Usuario;
  codigo!: string;

  modalRef!: BsModalRef;
  eliminado!: boolean;
  solicitudes: Solicitudes[] = [];

  private destroy$ = new Subject<void>();

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private solicitudesService: SolicitudesService,
    private modalService: BsModalService
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.codigo = params['codigo'];
    });

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    // Realizar la primera solicitud al cargar el componente
    this.obtenerSolicitudes();

    // Configurar intervalo de 1 segundo para realizar peticiones periódicas
    interval(1000)
      .pipe(
        // Cancelar la suscripción cuando el componente se destruye
        takeUntil(this.destroy$),
        // Cambiar a una nueva solicitud después de cada intervalo
        switchMap(() => this.obtenerSolicitudes())
    )
    .subscribe();
  }

  ngOnDestroy() {
    // Liberar recursos y cancelar la suscripción al destruir el componente
    this.destroy$.next();
    this.destroy$.complete();
  }

  obtenerSolicitudes(): Observable<void> {
    return this.solicitudesService.getSolicitudesOferta(this.codigo).pipe(
      map((list: Solicitudes[]) => {
        this.solicitudes = list;
      })
    );
  }

  informacionUsuario(codigousuario: string, codigoSoli: string) {
    const initialState = {
      codigo: codigousuario,
      codigoOferta: this.codigo,
      codigoSolicitud: codigoSoli
    };

    this.modalRef = this.modalService.show(InformacionUsuarioComponent, { initialState });
  }
}
