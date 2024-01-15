import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";
import { Observable, catchError, map, of } from "rxjs";
import { Oferta } from "src/entities/Oferta";
import { OfertaEliminada } from "src/entities/OfertaEliminada";
@Injectable({
    providedIn: 'root'
})
export class OfertaService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

 
    public eliminarOferta(oferta: OfertaEliminada): Observable<boolean> {
        const params = new HttpParams().set('codigoOferta', oferta.codigoOferta).set('motivo', oferta.motivo);
      
        return this.httpClient.delete<HttpResponse<any>>(`${this.API_URL}Ofertas`, { params, observe: 'response' }).pipe(
          map((response) => this.manejarRespuesta(response)),
          catchError((error) => this.manejarError(error))
        );
      }
      
 

    public crearOferta(oferta: Oferta): Observable<Oferta> {
        console.log('connectando con el BE: ' + oferta);
        return this.httpClient.post<Oferta>(this.API_URL + "Ofertas", oferta);
    }

    public getOfertas(): Observable<Oferta[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Oferta[]>(this.API_URL + "Ofertas");
    }
    public getOfertasPreferencia(codigoUsuario: string): Observable<Oferta[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Oferta[]>(this.API_URL + "Ofertas?codigoUsuario="+codigoUsuario);
    }

    public getOfertasEmpresa(codigoEmpresa: String): Observable<Oferta[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Oferta[]>(this.API_URL + "Ofertas?empresa="+codigoEmpresa);
    }
    
    public getOfertasEmpresaEstado(codigoEmpresa: String, estado:string ): Observable<Oferta[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Oferta[]>(this.API_URL + "Ofertas?empresa="+codigoEmpresa+"&estado="+estado);
    }
    public getOferta(codigo: string): Observable<Oferta> {
        return this.httpClient.get<Oferta>(this.API_URL + "Ofertas?codigo="+codigo);
    }
    public actualizarOferta(oferta: Oferta): Observable<Oferta> {
        return this.httpClient.put<Oferta>(this.API_URL + "Ofertas", oferta);
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