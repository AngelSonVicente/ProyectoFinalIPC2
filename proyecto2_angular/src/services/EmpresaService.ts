import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Empresa } from "src/entities/Empresa";
@Injectable({
    providedIn: 'root'
})
export class EmpresaService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

 

    public getEmpresa(codigoEmpresa: String): Observable<Empresa> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Empresa>(this.API_URL + "Empresa?codigo="+codigoEmpresa);
    }

   

 
}