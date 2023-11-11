import { Component, OnInit } from '@angular/core';
import { Entrevista } from 'src/entities/Entrevista';
import { Usuario } from 'src/entities/Usuario';
import { EntrevistasService } from 'src/services/EntrevistaService';

@Component({
  selector: 'app-entrevistas-pendientes',
  templateUrl: './entrevistas-pendientes.component.html',
  styleUrls: ['./entrevistas-pendientes.component.css']
})
export class EntrevistasPendientesComponent implements OnInit {

  entrevistas: Entrevista[] = [];
  usuario!: Usuario;
  constructor(private entrevistaService: EntrevistasService){}

 
  


  ngOnInit(): void{
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.entrevistaService.getEntrevistasUsuario(this.usuario.codigo.toString()).subscribe({

      next: (list: Entrevista[]) => {
        this.entrevistas = list;
      }
    });

  }

}
