import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Oferta } from 'src/entities/Oferta';
import { OfertaService } from 'src/services/OfertaService';

@Component({
  selector: 'app-detalles-ofertas',
  templateUrl: './detalles-ofertas.component.html',
  styleUrls: ['./detalles-ofertas.component.css']
})
export class DetallesOfertasComponent implements OnInit {
  codigo!: string;
  oferta!: Oferta;
  


  constructor(private route: ActivatedRoute, private ofertaService: OfertaService) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.codigo = params['codigo'];
      // Utiliza el código para cargar los detalles de la oferta
    });
  
    this.ofertaService.getOferta(this.codigo).subscribe({
      next: (oferta: Oferta) => {
        this.oferta = oferta;

  
      }
    });
  
  }

}
