import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Entrevista } from 'src/entities/Entrevista';
import { Usuario } from 'src/entities/Usuario';
import { forkJoin, interval } from 'rxjs';
import { switchMap } from 'rxjs/operators';

import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { EntrevistasService } from 'src/services/EntrevistaService';
import { DetallesOfertasComponent } from 'src/app/ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';
import { InformacionUsuarioComponent } from '../../RevisionPostulaciones/informacion-usuario/informacion-usuario.component';
import { FinalizarEntrevistaComponent } from '../finalizar-entrevista/finalizar-entrevista.component';
import { FinalizarcionOfertaService } from 'src/services/FinalizarOfertaService';
import { FinalizarOferta } from 'src/entities/FinalizarOferta';

@Component({
  selector: 'app-entrevistas-ofertas',
  templateUrl: './entrevistas-ofertas.component.html',
  styleUrls: ['./entrevistas-ofertas.component.css']
})
export class EntrevistasOfertasComponent {

  Finalizado: boolean = false;
  modalRef!: BsModalRef;
  nombreOferta!: string;
  codigoOferta!: string;
  entrevistas: Entrevista[] = [];
  usuario!: Usuario;
 
  ofertaFinalizada: boolean = false;

  constructor(private entrevistaService: EntrevistasService,  private route: ActivatedRoute,  
     private modalService: BsModalService, private finalizarService: FinalizarcionOfertaService,
     private router: Router){
      
      

  
     }

 
  ngOnInit(): void{
    this.route.params.subscribe((params) => {
      this.codigoOferta = params['codigo'];
    });

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

      
interval(2000)
.pipe(
  switchMap(() => forkJoin(
    this.entrevistaService.getEntrevistasOferta(this.codigoOferta),
    this.finalizarService.OfertaEliminada(this.codigoOferta)
  ))
)
.subscribe({
  next: ([entrevistas, borrado]: [Entrevista[], boolean]) => {
    this.entrevistas = entrevistas;
    this.nombreOferta = this.entrevistas[1].nombreOferta;
    this.ofertaFinalizada = this.entrevistas.every(entrevista => entrevista.estado === 'Finalizado');

    if (borrado) {
      this.Finalizado = true;
    }
  },
  error: (error) => {
    // Manejar errores aquí
  }
});

  }

  solicitarConfirmacion(codigoUsuario: string, ) {
    const confirmacion = window.confirm('Está seguro que desea contratar a esta persona?');

    if (confirmacion) {
      console.log("Finalizando Oferta ");
      console.log("codigo suaurio " + codigoUsuario);
      console.log("codigo empresa " + this.usuario.codigo);

      const finalizarOferaa = new FinalizarOferta();
 
          this.finalizarService.FinalizarOFerta(this.codigoOferta,codigoUsuario,this.usuario.codigo.toString()).subscribe({
        next: (created: FinalizarOferta) => {
          console.log("creado " + created);

          this.router.navigate(['/Proyecto2/Empleador/Facturacion', this.codigoOferta]);
    
         

        },
        error: (error: any) => {
          console.log("error");
        }
      });


    } 
  }

  


  
abrirModal(codigoOfertaa: string) {
  const initialState = {
    codigo: codigoOfertaa
  };

  this.modalRef = this.modalService.show(DetallesOfertasComponent, { initialState });


}



informacionUsuario(codigousuario: string) {
  const initialState = {
    codigo: codigousuario,
 
    agendarEntrevista:false,
  };

  this.modalRef = this.modalService.show(InformacionUsuarioComponent, { initialState });
}

MostrarNotas(notaa: string) {
  const initialState = {
    finalizar: true,
    nota:notaa,
 
  };

  this.modalRef = this.modalService.show(FinalizarEntrevistaComponent, { initialState });
}
FinalizarEntrevista(codigoentrevista: string) {
  const initialState = {
    codigoEntrevista: codigoentrevista,
 
  };

  this.modalRef = this.modalService.show(FinalizarEntrevistaComponent, { initialState });
}

  
}
