import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { OfertaService } from 'src/services/OfertaService';


@Component({
  selector: 'app-buscar-ofertas-empleo',
  templateUrl: './buscar-ofertas-empleo.component.html',
  styleUrls: ['./buscar-ofertas-empleo.component.css']
})
export class BuscarOfertasEmpleoComponent  implements OnInit{
  @Input() codigoEmpresa!: string;
  

  empresaId!: string;

  ofertas: Oferta[] = [];
  usuario!: Usuario;
  constructor(private ofertaService: OfertaService, private route: ActivatedRoute){

  }
  ngOnInit(): void{
    this.route.params.subscribe(params => {
      this.empresaId = params['codigoEmpresa'];
    });

    
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    if(this.codigoEmpresa==null){
  this.ofertaService.getOfertas().subscribe({

      next: (list: Oferta[]) => {
        this.ofertas = list;
      }
    });
    }else{

      this.ofertaService.getOfertasEmpresaEstado(this.codigoEmpresa,"Activo").subscribe({

        next: (list: Oferta[]) => {
          this.ofertas = list;
        }
      });
      
    }
  

  }




}
