import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";
import { Observable, catchError, map, of } from "rxjs";
import { Oferta } from "src/entities/Oferta";
import { OfertaEliminada } from "src/entities/OfertaEliminada";
import { HoraDisponible } from "src/entities/HoraDisponible";
@Injectable({
    providedIn: 'root'
})
export class HoraDisponibleService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) { }


    



  
    public getHorasDisponibles(fecha:string, ubicacion:string, codigoOferta:string): Observable<HoraDisponible[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<HoraDisponible[]>(this.API_URL + "HoraDisponible?fecha="+fecha+"&ubicacion="+ubicacion+"&codigoOferta="+codigoOferta);
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