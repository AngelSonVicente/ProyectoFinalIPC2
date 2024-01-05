import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Categoria } from "src/entities/Categoria";
import { RecuperarCuenta } from "src/entities/RecuperarCuenta";
@Injectable({
    providedIn: 'root'
})
export class RecuperarCuentaService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

    public enviarCorreo(cuenta: RecuperarCuenta): Observable<RecuperarCuenta> {
        console.log('connectando con el BE: ' + cuenta);
        return this.httpClient.post<RecuperarCuenta>(this.API_URL + "EnviarCorreoRecuperacion", cuenta);
    }

    public EnviarCorreo(correo: String): Observable<String> {
        console.log('connectando con el BE: ' + correo);
        return this.httpClient.post<String>(this.API_URL + "EnviarCorreoRecuperacion", correo);
    }
}