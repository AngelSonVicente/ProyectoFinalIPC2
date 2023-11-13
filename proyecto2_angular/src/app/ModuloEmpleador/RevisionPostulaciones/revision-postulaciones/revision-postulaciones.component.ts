import { Component, OnInit } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { OfertaService } from 'src/services/OfertaService';
import { EliminarOfertaComponent } from '../../GestionOfertas/eliminar-oferta/eliminar-oferta.component';
import { DetallesOfertasComponent } from 'src/app/ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-revision-postulaciones',
  templateUrl: './revision-postulaciones.component.html',
  styleUrls: ['./revision-postulaciones.component.css']
})
export class RevisionPostulacionesComponent implements OnInit{

  
  eliminado!:boolean;
  modalRef!: BsModalRef;
  ofertas: Oferta[] = [];
  usuario!: Usuario;
  constructor(private ofertaService: OfertaService,  private modalService: BsModalService, private sanitizer: DomSanitizer){}
  ngOnInit(): void{
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.ofertaService.getOfertasEmpresaEstado(this.usuario.codigo.toString(),"Seleccion").subscribe({

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


abrirModal(codigoOfertaa: string) {
  const initialState = {
    codigo: codigoOfertaa
  };

  this.modalRef = this.modalService.show(DetallesOfertasComponent, { initialState });


}

}
