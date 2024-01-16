import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Categoria } from 'src/entities/Categoria';
import { Filtro } from 'src/entities/Filtro';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { CategoriaService } from 'src/services/CategoriaService';
import { OfertaService } from 'src/services/OfertaService';


@Component({
  selector: 'app-buscar-ofertas-empleo',
  templateUrl: './buscar-ofertas-empleo.component.html',
  styleUrls: ['./buscar-ofertas-empleo.component.css']
})
export class BuscarOfertasEmpleoComponent implements OnInit {
  @Input() codigoEmpresa!: string;





  filtro: Filtro;

  ubicacion!: string;
  salario!: string;
  categoriaFiltro: string ="todas";
  modalidadaFiltro: string ="todas";

  


  busqueda!: string;

  categorias: Categoria[]=[];
  sugeridas :string="Sugeridas";
  empresaId!: string;

  ofertas: Oferta[] = [];
  usuario!: Usuario;
  constructor(private ofertaService: OfertaService, private route: ActivatedRoute
    , private categoriaService: CategoriaService) {
    this.filtro = new Filtro();
    this.filtro.categoria = "todas";
    this.filtro.modalidad = "todas";
    

  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.empresaId = params['codigoEmpresa'];
    });


    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    if (this.codigoEmpresa == null) {
      this.ofertaService.getOfertasPreferencia(this.usuario.codigo.toString()).subscribe({

        next: (list: Oferta[]) => {
          this.ofertas = list;
        }
      });


      this.categoriaService.getCategorias().subscribe({

        next: (list: Categoria[]) => {
          this.categorias = list;
        }
      });





    } else {

      this.ofertaService.getOfertasEmpresaEstado(this.codigoEmpresa, "Activo").subscribe({

        next: (list: Oferta[]) => {
          this.ofertas = list;
        }
      });

    }


  }



  TodasLasOfertas() {
    this.ofertaService.getOfertas().subscribe({

      next: (list: Oferta[]) => {
        this.ofertas = list;

        this.sugeridas=" ";
      }
    });

  }


  FiltrarOfertas() {
    this.ofertaService.FiltrarOfertas(this.filtro).subscribe({

      next: (list: Oferta[]) => {
        this.ofertas = list;

        this.sugeridas=" ";
      }
    });
    
  }



}
