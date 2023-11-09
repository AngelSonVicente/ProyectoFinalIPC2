import { Component, OnInit } from '@angular/core';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { OfertaService } from 'src/services/OfertaService';


@Component({
  selector: 'app-buscar-ofertas-empleo',
  templateUrl: './buscar-ofertas-empleo.component.html',
  styleUrls: ['./buscar-ofertas-empleo.component.css']
})
export class BuscarOfertasEmpleoComponent  implements OnInit{

  ofertas: Oferta[] = [];
  usuario!: Usuario;
  constructor(private ofertaService: OfertaService){

  }
  ngOnInit(): void{
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.ofertaService.getOfertas().subscribe({

      next: (list: Oferta[]) => {
        this.ofertas = list;
      }
    });

  }


  verDetalles() {
  
  }

}
