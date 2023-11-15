import { Component, OnInit } from '@angular/core';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';

import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { OfertaService } from 'src/services/OfertaService';
import { EliminarOfertaComponent } from '../../GestionOfertas/eliminar-oferta/eliminar-oferta.component';
import { DetallesOfertasComponent } from 'src/app/ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-revision-entrevistas',
  templateUrl: './revision-entrevistas.component.html',
  styleUrls: ['./revision-entrevistas.component.css']
})
export class RevisionEntrevistasComponent implements OnInit{

  
  eliminado!:boolean;
  modalRef!: BsModalRef;

//declaras tu lista de objetos que vas a usar

  ofertas: Oferta[] = [];
  
  
  usuario!: Usuario;

  //instancias el service del modal
  constructor(private ofertaService: OfertaService,  private modalService: BsModalService, private sanitizer: DomSanitizer){}
  
  //codigo que al inciar el el componente se llene la lista de objetos
  ngOnInit(): void{

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    //mandas a llamar el servlet y llenas tus objtos
    this.ofertaService.getOfertasEmpresaEstado(this.usuario.codigo.toString(),"Entrevista").subscribe({

      next: (list: Oferta[]) => {
        this.ofertas = list;

        
      }
    });


   

}


EliminarOferta(codigoOF:string) {
    
  const initialState = {
    codigoOferta: codigoOF
  };

  this.modalRef = this.modalService.show(EliminarOfertaComponent, { initialState });

  this.modalRef.content.confirmado.subscribe((postuladoo: boolean) => {
    if (postuladoo) {
      this.eliminado = true;
      window.location.reload();
      
    } else {
      this.eliminado = false;
    }
  });



}


mostrarPDF(pdfData: ArrayBuffer | null): any {
  if (pdfData) {
    const blob = new Blob([pdfData], { type: 'application/pdf' });
    return this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
  }
  return null;
}

//aqui abris tu modal y mandas toda las variables u objetos que querras mandar de este componente al modal
abrirModal(codigoOfertaa: string) {
  //manda todas las variables al modal
  const initialState = {
    codigo: codigoOfertaa
  
  };
  //abre el modal

  this.modalRef = this.modalService.show(DetallesOfertasComponent, { initialState });

}

}
