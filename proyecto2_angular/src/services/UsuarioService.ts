import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Observable, catchError, map, of } from "rxjs";
import { Solicitudes } from "src/entities/Solicitudes";
import { Oferta } from "src/entities/Oferta";
import { Usuario } from "src/entities/Usuario";
@Injectable({
    providedIn: 'root'
})
export class UsuarioService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}



    public getUsuarioID(codigo: string): Observable<Usuario> {
        return this.httpClient.get<Usuario>(this.API_URL + "Usuario?codigoUsuario="+codigo);
    }


    
   
  

    

  private manejarRespuesta(response: HttpResponse<any>): boolean {
    if (response.status === 200) {
      return true; 
    } else {
      return false;
    }
  }
  
  private manejarError(error: any): Observable<boolean> {
    console.error(error);
    return of(false);
  }




   

 
}