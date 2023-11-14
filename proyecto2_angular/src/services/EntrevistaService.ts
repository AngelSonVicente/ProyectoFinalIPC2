import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Observable, catchError, map, of } from "rxjs";

import { Entrevista } from "src/entities/Entrevista";
import { Solicitudes } from "src/entities/Solicitudes";
@Injectable({
    providedIn: 'root'
})
export class EntrevistasService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}




    public getEntrevistasOferta(codigoOferta: string): Observable<Entrevista[]> {
        return this.httpClient.get<Entrevista[]>(this.API_URL + "Entrevistas?codigoOferta="+codigoOferta);
    }

    public getEntrevistasUsuario(usuario: string): Observable<Entrevista[]> {
        return this.httpClient.get<Entrevista[]>(this.API_URL + "Entrevistas?codigoUsuario="+usuario);
    }
    
    public crearEntrevista(entrevista: Entrevista): Observable<Entrevista> {
        console.log('connectando con el BE: ' + entrevista);
        return this.httpClient.post<Entrevista>(this.API_URL + "Entrevistas", entrevista);
    }

    public finalizarEntrevista(entrevista: Entrevista): Observable<Entrevista> {
        return this.httpClient.put<Entrevista>(this.API_URL + "Entrevistas", entrevista);
    }
   
    






   

 
}


