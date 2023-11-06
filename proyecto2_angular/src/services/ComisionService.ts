import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Comision } from "src/entities/Comision";

@Injectable({
    providedIn: 'root'
})
export class ComisionService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

    public crearComision(comision: Comision): Observable<Comision> {
        console.log('connectando con el BE: ' + comision);
        return this.httpClient.post<Comision>(this.API_URL + "GestionComision", comision);
    }

    public getHistorialComisiones(): Observable<Comision[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Comision[]>(this.API_URL + "GestionComision?list)");
    }
    public getComision(list: string): Observable<Comision> {
        return this.httpClient.get<Comision>(this.API_URL + "GestionComision?list="+list);
    }

   

 
}