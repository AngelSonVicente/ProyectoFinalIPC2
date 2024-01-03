import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/entities/Usuario';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  usuario!: Usuario;
  constructor(private router: Router) { }

  ngOnInit(): void{
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
  
  }

  
  userLoggedIn() {
    return localStorage.getItem('usuario') ? true : false;
}

CerrarSesion() {
    localStorage.removeItem('usuario');
    this.router.navigate(['/Proyecto2/Menu']);
}

getTipoUsuario(): string {
  return this.usuario ? this.usuario.tipo : 'Invitado';
}
getLinkPerfil(): string {
  if(this.usuario.tipo=='Admin'){
    return '/Proyecto2/Administrador/Perfil';
  } 
  if(this.usuario.tipo=='Usuario'){
    return '/Proyecto2/Usuario/Perfil';
  }
  if(this.usuario.tipo=='Empleador'){
    return '/Proyecto2/Empleador/Perfil';
  }

  return '#';
}

getNombreUsuario() {
    return this.usuario ? this.usuario.nombre : '';
}



}
