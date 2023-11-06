import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/entities/Usuario';

@Component({
  selector: 'app-modulo-usuarioo',
  templateUrl: './modulo-usuarioo.component.html',
  styleUrls: ['./modulo-usuarioo.component.css']
})
export class ModuloUsuariooComponent implements OnInit{

  usuario!: Usuario;

ngOnInit(): void{
  let jsonUsuario = localStorage.getItem('usuario');
  this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
}

}
