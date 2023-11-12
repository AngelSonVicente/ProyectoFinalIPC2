import { Component, OnInit } from '@angular/core';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { OfertaService } from 'src/services/OfertaService';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { DetallesOfertasComponent } from 'src/app/ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';

@Component({
  selector: 'app-gestion-ofertas',
  templateUrl: './gestion-ofertas.component.html',
  styleUrls: ['./gestion-ofertas.component.css']
})
export class GestionOfertasComponent implements OnInit {

  modalRef!: BsModalRef;
  ofertas: Oferta[] = [];
  usuario!: Usuario;
  constructor(private ofertaService: OfertaService,  private modalService: BsModalService){}
  ngOnInit(): void{
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.ofertaService.getOfertasEmpresa(this.usuario.codigo.toString()).subscribe({

      next: (list: Oferta[]) => {
        this.ofertas = list;

        
      }
    });

}




abrirModal(codigoOfertaa: string) {
  const initialState = {
    codigo: codigoOfertaa
  };

  this.modalRef = this.modalService.show(DetallesOfertasComponent, { initialState });


}

}