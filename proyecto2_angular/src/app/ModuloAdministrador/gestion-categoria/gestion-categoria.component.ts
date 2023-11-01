import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/entities/Categoria';

import {CategoriaService} from 'src/services/CategoriaService';

@Component({
  selector: 'app-gestion-categoria',
  templateUrl: './gestion-categoria.component.html',
  styleUrls: ['./gestion-categoria.component.css']

})

export class GestionCategoriaComponent implements OnInit {
  categorias: Categoria[] = [];
  
  constructor(private Categoriaserice: CategoriaService){

    

  }
  ngOnInit(): void{
    this.Categoriaserice.getCategorias().subscribe({

      next: (list: Categoria[]) => {
        this.categorias = list;
      }
    });

  }


}
