import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";
import { Observable, catchError, map, of } from "rxjs";
import { Oferta } from "src/entities/Oferta";
import { OfertaEliminada } from "src/entities/OfertaEliminada";
import { Notificaciones } from "src/entities/Notificaciones";
@Injectable({
    providedIn: 'root'
})
export class notificacionesService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}


    public getNotificaciones(codigoUsuario: string): Observable<Notificaciones[]> {
        return this.httpClient.get<Notificaciones[]>(this.API_URL + "Notificaciones?codigoUsuarioDestino="+codigoUsuario);
    }
    public MarcarComoLeido(codigoUsuario: string): Observable<Notificaciones> {
        return this.httpClient.put<Notificaciones>(this.API_URL + "Notificaciones?codigoUsuario="+codigoUsuario, codigoUsuario);
    }
}