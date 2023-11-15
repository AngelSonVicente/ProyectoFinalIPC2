import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";
import { Observable, catchError, map, of } from "rxjs";
import { Oferta } from "src/entities/Oferta";
import { HistorialComision } from "src/entities/HistorialComision";
import { EmpleadorReporte } from "src/entities/EmpleadorReporte";
import { CategoriaReporte } from "src/entities/CategoriaReporte";
import { Entrevista } from "src/entities/Entrevista";
import { HistorialCobros } from "src/entities/HistorialCobros";
import { SolicitudRetirada } from "src/entities/SolicitudRetirada";
import { Solicitudes } from "src/entities/Solicitudes";
@Injectable({
    providedIn: 'root'
})
export class ReportesService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}


    public getReporte1Admin(): Observable<HistorialComision[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<HistorialComision[]>(this.API_URL + "Reportes?tipo=admin&reporte=1");
    }


    public getReporte2Admin(): Observable<EmpleadorReporte[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<EmpleadorReporte[]>(this.API_URL + "Reportes?tipo=admin&reporte=2");
    }
    public getReporte3Admin(): Observable<EmpleadorReporte[]> {
        console.log('connectando con el BE: ');
            return this.httpClient.get<EmpleadorReporte[]>(this.API_URL + "Reportes?tipo=admin&reporte=3");
    
        
    }
    public getReporte4Admin(): Observable<CategoriaReporte[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<CategoriaReporte[]>(this.API_URL + "Reportes?tipo=admin&reporte=4");
    }




    public getReporte1Empleador(codigo:string,fecha1: string, fecha2:string): Observable<Oferta[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Oferta[]>(this.API_URL + "Reportes?tipo=empleador&reporte=1&codigo="+codigo+"&fecha1="+fecha1+"&fecha2="+fecha2);
    }
    public getReporte2Empleador(codigo:string,fecha1: string): Observable<Entrevista[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Entrevista[]>(this.API_URL + "Reportes?tipo=empleador&reporte=2&codigo="+codigo+"&fecha1="+fecha1);
    }
    public getReporte3Empleador(codigo:string): Observable<HistorialCobros[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<HistorialCobros[]>(this.API_URL + "Reportes?tipo=empleador&reporte=3&codigo="+codigo);
    }



    public getReporte1Usuario(codigo:string,fecha1: string, fecha2:string): Observable<SolicitudRetirada[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<SolicitudRetirada[]>(this.API_URL + "Reportes?tipo=usuario&reporte=1&codigo="+codigo+"&fecha1="+fecha1+"&fecha2="+fecha2);
    }
    public getReporte2Usuario(codigo:string): Observable<Solicitudes[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Solicitudes[]>(this.API_URL + "Reportes?tipo=usuario&reporte=2&codigo="+codigo);
    }
    public getReporte3Usuario(codigo:string): Observable<Solicitudes[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Solicitudes[]>(this.API_URL + "Reportes?tipo=usuario&reporte=3&codigo="+codigo);
    }
    public getReporte4Usuario(codigo:string): Observable<Solicitudes[]> {
        console.log('connectando con el BE: ');
        return this.httpClient.get<Solicitudes[]>(this.API_URL + "Reportes?tipo=usuario&reporte=4&codigo="+codigo);
    }

 
}