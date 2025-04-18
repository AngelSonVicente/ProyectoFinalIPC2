import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/entities/Usuario';

@Component({
  selector: 'app-modulo-admin',
  templateUrl: './modulo-admin.component.html',
  styleUrls: ['./modulo-admin.component.css']
})
export class ModuloAdminComponent implements OnInit {
usuario!: Usuario;

ngOnInit(): void{
  let jsonUsuario = localStorage.getItem('usuario');
  this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
}

}
