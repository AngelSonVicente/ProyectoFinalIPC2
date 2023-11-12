import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Oferta } from "src/entities/Oferta";
@Injectable({
    providedIn: 'root'
})
export class OfertaService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

    public crearOferta(oferta: Oferta): Observable<Oferta> {
        console.log('connectando con el BE: ' + oferta);
        return this.httpClient.post<Oferta>(this.API_URL + "Ofertas", oferta);
    }

    public getOfertas(): Observable<Oferta[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Oferta[]>(this.API_URL + "Ofertas");
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
   

 
}