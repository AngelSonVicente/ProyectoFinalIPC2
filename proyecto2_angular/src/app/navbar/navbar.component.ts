import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/entities/Usuario';
import { notificacionesService } from 'src/services/NotificacionesService';

import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { NotificacionesComponent } from '../notificaciones/notificaciones.component';
import { Notificaciones } from 'src/entities/Notificaciones';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  usuario!: Usuario;
  notificacion: boolean = false;

  modalRef!: BsModalRef;


  notificaciones: Notificaciones[] = [];

  constructor(private router: Router, private modalService: BsModalService,
    private notificacionesService: notificacionesService) { }

  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario =
      jsonUsuario ? JSON.parse(jsonUsuario) : null;


    this.geNotificaciones();



  }

  geNotificaciones() {
    this.notificacionesService.getNotificaciones(this.usuario.codigo.toString()).subscribe({

      next: (list: Notificaciones[]) => {
        this.notificaciones = list;
      }
    });

  }


  userLoggedIn() {
    return localStorage.getItem('usuario') ? true : false;
  }

  CerrarSesion() {
    localStorage.removeItem('usuario');
    this.router.navigate(['/Proyecto2/Menu']);
  }
  contarNotificacionesNoLeidas(): number {
    return this.notificaciones.filter(notificacion => notificacion.estado === 'No leido').length;
  }

  getTipoUsuario(): string {
    return this.usuario ? this.usuario.tipo : 'Invitado';
  }
  getLinkPerfil(): string {
    if (this.usuario.tipo == 'Admin') {
      return '/Proyecto2/Administrador/Perfil';
    }
    if (this.usuario.tipo == 'Usuario') {
      return '/Proyecto2/Usuario/Perfil';
    }
    if (this.usuario.tipo == 'Empleador') {
      return '/Proyecto2/Empleador/Perfil';
    }

    return '#';
  }

  getNombreUsuario() {
    return this.usuario ? this.usuario.nombre : '';
  }

  abrirModal() {
    const config = {
      class: 'modal-right',
    };
    this.modalRef = this.modalService.show(NotificacionesComponent, config);

    // Suscribirse al observable para recibir eventos del modal
    this.modalRef.content.marcarComoLeido.subscribe((marcadoLeido: boolean) => {
      if (marcadoLeido) {
        this.geNotificaciones();
      }



    });
  }






}
