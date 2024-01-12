import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Empresa } from "src/entities/Empresa";
import { RecuperarCuenta } from "src/entities/RecuperarCuenta";
@Injectable({
    providedIn: 'root'
})
export class PreferenciasService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

 

    public getPreferencias(codigo: string): Observable<string[]> {
        return this.httpClient.get<string[]>(this.API_URL + "Preferencias?codigoUsuario="+codigo);
    }

   
   

 
}