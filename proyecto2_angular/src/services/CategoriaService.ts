import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Categoria } from "src/entities/Categoria";
@Injectable({
    providedIn: 'root'
})
export class CategoriaService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

    public crearCategoria(categoria: Categoria): Observable<Categoria> {
        console.log('connectando con el BE: ' + categoria);
        return this.httpClient.post<Categoria>(this.API_URL + "GestionCategoria", categoria);
    }
    public actualizarCategoria(categoria: Categoria): Observable<Categoria> {
        return this.httpClient.put<Categoria>(this.API_URL + "GestionCategoria", categoria);
    }

    public getCategorias(): Observable<Categoria[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Categoria[]>(this.API_URL + "GestionCategoria");
    }
    public getCategoria(codigo: string): Observable<Categoria> {
        return this.httpClient.get<Categoria>(this.API_URL + "GestionCategoria?codigo="+codigo);
    }

   

 
}