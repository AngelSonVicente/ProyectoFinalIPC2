import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Empresa } from "src/entities/Empresa";
import { RecuperarCuenta } from "src/entities/RecuperarCuenta";
@Injectable({
    providedIn: 'root'
})
export class ReestablcerPasswordService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

 

    public getInformacionToken(token: String): Observable<RecuperarCuenta> {
        return this.httpClient.get<RecuperarCuenta>(this.API_URL + "ReestablecerPasword?token="+token);
    }

    public recuperarPassword(cuenta: RecuperarCuenta): Observable<RecuperarCuenta> {
        console.log('connectando con el BE: ' + cuenta);
        return this.httpClient.post<RecuperarCuenta>(this.API_URL + "ReestablecerPasword", cuenta);
    }
   

 
}