import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Usuario } from 'src/entities/Usuario';
import { ActivatedRoute } from '@angular/router';
import { SolicitudesService } from 'src/services/SolicitudesService';
import { Solicitudes } from 'src/entities/Solicitudes';
import { InformacionUsuarioComponent } from '../informacion-usuario/informacion-usuario.component';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-postulaciones-oferta',
  templateUrl: './postulaciones-oferta.component.html',
  styleUrls: ['./postulaciones-oferta.component.css']
})
export class PostulacionesOfertaComponent implements OnInit{
  usuario!: Usuario;
  codigo!: string;

  modalRef!: BsModalRef;

  eliminado!: boolean;;
  solicitudes: Solicitudes[] = [];


  constructor(private formBuilder: FormBuilder,private route: ActivatedRoute, private solicitudesService: SolicitudesService,
     private modalService: BsModalService){}

    ngOnInit(){


      
      this.route.params.subscribe((params) => {
        this.codigo = params['codigo'];
      });


      let jsonUsuario = localStorage.getItem('usuario');
      this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
      console.log('pdf: '+this.usuario.cv);

      this.solicitudesService.getSolicitudesOferta(this.codigo).subscribe({

        next: (list: Solicitudes[]) => {
          this.solicitudes = list;
          
        }
      });


  

    }


 

    informacionUsuario(codigousuario:string) {
    
      const initialState = {
        codigo: codigousuario
      };
    
      this.modalRef = this.modalService.show(InformacionUsuarioComponent, { initialState });
    
      this.modalRef.content.confirmado.subscribe((postuladoo: boolean) => {
        if (postuladoo) {
          this.eliminado = true;
          window.location.reload();
          
        } else {
          this.eliminado = false;
        }
      });
    
    
    
    }





}
