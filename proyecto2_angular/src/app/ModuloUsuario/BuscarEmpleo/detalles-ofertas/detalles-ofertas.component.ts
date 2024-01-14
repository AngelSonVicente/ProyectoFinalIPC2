import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Oferta } from 'src/entities/Oferta';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { OfertaService } from 'src/services/OfertaService';
import { PostulacionDiaglogComponent } from '../postulacion-diaglog/postulacion-diaglog.component';
import { SolicitudesService } from 'src/services/SolicitudesService';
import { Usuario } from 'src/entities/Usuario';


@Component({
  selector: 'app-detalles-ofertas',
  templateUrl: './detalles-ofertas.component.html',
  styleUrls: ['./detalles-ofertas.component.css']
})
export class DetallesOfertasComponent implements OnInit {
  usuario!: Usuario;
 //variable que va a recibir la variable que mandaste del componente donde lo mandaste a llamar 
 //se llena solito digamos, no tenes que hacer nada

  codigo!: string;

  oferta!: Oferta;

  modalRef!: BsModalRef;
  postulado!:boolean;



  constructor(private route: ActivatedRoute, private ofertaService: OfertaService, private modalService: BsModalService, private solicitudService:SolicitudesService, private router: Router) {}

  ngOnInit() {

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    if(this.codigo==null){

      //jalar de la URL
          this.route.params.subscribe((params) => {
      this.codigo = params['codigo'];
    });
    }

  
    //manda a llamar al servlet y llena el objeto
    this.ofertaService.getOferta(this.codigo).subscribe({
      next: (oferta: Oferta) => {
        this.oferta = oferta;

  
      }
    });

    this.solicitudService.ExisteSolicitud(this.codigo, this.usuario.codigo.toString()).subscribe((existe: boolean) => {
      if (existe) {
    
        this.postulado=true;
        
      } else {
     
        this.postulado=false;
      }
    });
  
  }

  // aca tambien mando a llamar a otro modal, pero como solo lo uso para el Usuario, lo desabilito para el empleador
  // lo desabilito en el HTML
  abrirModal() {
    
    const initialState = {
      codigoOferta: this.oferta.codigo
    };
  
    this.modalRef = this.modalService.show(PostulacionDiaglogComponent, { initialState });
  
    this.modalRef.content.confirmado.subscribe((postuladoo: boolean) => {
      if (postuladoo) {
        this.postulado = true;

      } else {
        this.postulado = false;
      }
    });



  }

  


  //aqui lo que hace es cerrar el modal y redigirilo a otra pestaña, entonces se cierra este y manda al usuario a otra pagina

  redirigirConCierreModal(codigoOferta?: string) {

    // Cierra el modal
   
    this.router.navigate(['/Proyecto2/Empleador/EditarOferta', codigoOferta]);
    console.log('Redirigiendo con cierre de modal. Código de oferta:', codigoOferta);
    // Redirige a la ruta deseada con el código de la oferta  
    this.modalService.hide();  
  }


}
