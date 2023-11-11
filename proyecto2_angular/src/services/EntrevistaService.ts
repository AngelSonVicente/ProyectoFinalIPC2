import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Observable, catchError, map, of } from "rxjs";

import { Entrevista } from "src/entities/Entrevista";
@Injectable({
    providedIn: 'root'
})
export class EntrevistasService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}




    public getEntrevistasUsuario(usuario: string): Observable<Entrevista[]> {
        return this.httpClient.get<Entrevista[]>(this.API_URL + "Entrevistas?codigoUsuario="+usuario);
    }

    
   
  





   

 
}


