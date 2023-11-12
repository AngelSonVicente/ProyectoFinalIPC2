import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/entities/Usuario';

@Component({
  selector: 'app-modulo-empleador',
  templateUrl: './modulo-empleador.component.html',
  styleUrls: ['./modulo-empleador.component.css']
})
export class ModuloEmpleadorComponent implements OnInit {


  usuario!: Usuario;

  ngOnInit(): void{
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
  }
}
