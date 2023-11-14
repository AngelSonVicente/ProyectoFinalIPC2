import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";
import { Observable, catchError, map, of } from "rxjs";
import { Oferta } from "src/entities/Oferta";
import { OfertaEliminada } from "src/entities/OfertaEliminada";
import { FinalizarOferta } from "src/entities/FinalizarOferta";
@Injectable({
    providedIn: 'root'
})
export class FinalizarcionOfertaService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

 
    public OfertaEliminada(codigo: string): Observable<boolean> {
        const params = new HttpParams().set('codigoOferta', codigo);
      
        return this.httpClient.get<HttpResponse<any>>(`${this.API_URL}TerminarOferta`, { params, observe: 'response' }).pipe(
          map((response) => this.manejarRespuesta(response)),
          catchError((error) => this.manejarError(error))
        );
      }
      
 

      public FinalizarOFerta(oferta: string, usuario: string, empresa: string): Observable<FinalizarOferta> {
        console.log('Conectando con el BE Oferta: ' + oferta);
        console.log('Conectando con el BE Usuario: ' + usuario);
        console.log('Conectando con el BE Empresa: ' + empresa);
    
        // Crear un objeto que represente los parámetros a enviar
        const body = {
            codigoOferta: oferta,
            codigoUsuarioElegido: usuario,
            codigoEmpresa: empresa
        };
    
        // Enviar la solicitud POST con los parámetros en el cuerpo
        return this.httpClient.post<FinalizarOferta>(this.API_URL + 'TerminarOferta', body);
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