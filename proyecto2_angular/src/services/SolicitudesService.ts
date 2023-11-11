import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Observable, catchError, map, of } from "rxjs";
import { Solicitudes } from "src/entities/Solicitudes";
import { Oferta } from "src/entities/Oferta";
@Injectable({
    providedIn: 'root'
})
export class SolicitudesService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}


    public crearSolicitud(solicitud: Solicitudes): Observable<Solicitudes> {
        return this.httpClient.post<Solicitudes>(this.API_URL + "Solicitudes", solicitud);
    }

    public getSolicitudesUsuario(usuario: string): Observable<Solicitudes[]> {
        return this.httpClient.get<Solicitudes[]>(this.API_URL + "Solicitudes?codigoSuuario="+usuario);
    }

    
   
  

    
  public BorrarSolicitud(codigo: string): Observable<boolean> {
    console.log('Conectando con el BE: ');
    return this.httpClient.delete<HttpResponse<any>>(this.API_URL + `Solicitudes?codigo=${codigo}`, { observe: 'response' }).pipe(
      map((response) => this.manejarRespuesta(response)),
      catchError((error) => this.manejarError(error))
    );
  }
  public ExisteSolicitud(oferta: string, usuario: string): Observable<boolean> {
    console.log('Conectando con el BE: ');
    return this.httpClient.get<HttpResponse<any>>(this.API_URL + `Solicitudes?codigoOferta=${oferta}&codigoSuuario=${usuario}`, { observe: 'response' }).pipe(
      map((response) => this.manejarRespuesta(response)),
      catchError((error) => this.manejarError(error))
    );
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