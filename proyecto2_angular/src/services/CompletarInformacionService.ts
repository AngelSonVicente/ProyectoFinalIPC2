import { Injectable } from "@angular/core";
import { Login } from "../../src/entities/Login";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {Usuario} from 'src/entities/Usuario';
import { CompletarPerfilUsuario } from "src/entities/CompletarPerfilUsuario";
import { Empresa } from "src/entities/Empresa";
@Injectable({
    providedIn: 'root'
})
export class CompletarInformacionService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

    public completarUsuario(informacion: CompletarPerfilUsuario, archivo: File): Observable<CompletarPerfilUsuario> {
        const formData: FormData = new FormData();
        formData.append('CompletarPerfi', JSON.stringify(informacion));
        formData.append('CV', archivo, archivo.name);
    
        console.log('Conectando con el BE:', informacion);
    
        return this.httpClient.post<CompletarPerfilUsuario>(this.API_URL + 'CompletarInformacion', formData);
      }
      
    public completarEmpresa(informacion: Empresa): Observable<Empresa> {
        const formData: FormData = new FormData();
        formData.append('CompletarPerfi', JSON.stringify(informacion));
       
        console.log('Conectando con el BE:', informacion);
    
        return this.httpClient.post<Empresa>(this.API_URL + 'CompletarInformacion', formData);
      }
   

 
}