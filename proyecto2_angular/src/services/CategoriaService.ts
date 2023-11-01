import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {Usuario} from 'src/entities/Usuario';
import { Categoria } from "src/entities/Categoria";
@Injectable({
    providedIn: 'root'
})
export class CategoriaService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

    public getCategorias(): Observable<Categoria[]> {

        console.log('connectando con el BE: ');
        return this.httpClient.get<Categoria[]>(this.API_URL + "GestionCategoria");
   
    }

 
}