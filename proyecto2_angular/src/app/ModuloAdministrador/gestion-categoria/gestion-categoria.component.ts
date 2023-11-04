import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/entities/Categoria';
import { Usuario } from 'src/entities/Usuario';

import {CategoriaService} from 'src/services/CategoriaService';

@Component({
  selector: 'app-gestion-categoria',
  templateUrl: './gestion-categoria.component.html',
  styleUrls: ['./gestion-categoria.component.css']

})

export class GestionCategoriaComponent implements OnInit {
  categorias: Categoria[] = [];
  usuario!: Usuario;
  constructor(private Categoriaserice: CategoriaService){

    

  }
  ngOnInit(): void{
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.Categoriaserice.getCategorias().subscribe({

      next: (list: Categoria[]) => {
        this.categorias = list;
      }
    });

  }


}
