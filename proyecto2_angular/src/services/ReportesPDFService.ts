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
export class ReportesPDFService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/ReportesPDF";
    readonly DOWNLOAD_URL = "http://localhost:8080/proyecto2_api/v1/ReportesPDF";


    constructor(private httpClient: HttpClient) {}

    public getReport1Admin(): string {
        return this.DOWNLOAD_URL + "?tipo=Admin&reporte=1";
    }
    public getReport2Admin(): string {
        return this.DOWNLOAD_URL + "?tipo=Admin&reporte=2";
    }
    public getReport3Admin(fecahaIncio: String,fechaFinal: String): string {
        return this.DOWNLOAD_URL + "?tipo=Admin&reporte=3&fecha1="+fecahaIncio+"&fecha2="+ fechaFinal;
    }
    public getReport4Admin(fecahaIncio: String,fechaFinal: String): string {
        return this.DOWNLOAD_URL + "?tipo=Admin&reporte=4&fecha1="+fecahaIncio+"&fecha2="+ fechaFinal;
    }
    public getReport1Empleador(fecahaIncio: String,fechaFinal: String, codigo: String): string {
        return this.DOWNLOAD_URL + "?tipo=Empleador&reporte=1&fecha1="+fecahaIncio+"&fecha2="+ fechaFinal+"&codigo="+codigo;
    }
    public getReporte2Empleador(fecahaIncio: String, codigo: String): string {
        return this.DOWNLOAD_URL + "?tipo=Empleador&reporte=2&fecha1="+fecahaIncio+"&codigo="+codigo;
    }
    public getReporte3Empleador( codigo: String): string {
        return this.DOWNLOAD_URL + "?tipo=Empleador&reporte=3&codigo="+codigo;
    }
    public getReporte1Usuario(fecahaIncio: String,fechaFinal: String, codigo: String): string {
        return this.DOWNLOAD_URL + "?tipo=Usuario&reporte=1&fecha1="+fecahaIncio+"&fecha2="+ fechaFinal+"&codigo="+codigo;
    }

    public getReporte2Usuario( codigo: String): string {
        return this.DOWNLOAD_URL + "?tipo=Usuario&reporte=2&codigo="+codigo;
    }
    public getReporte3Usuario( codigo: String): string {
        return this.DOWNLOAD_URL + "?tipo=Usuario&reporte=3&codigo="+codigo;
    }
    public getReporte4Usuario( codigo: String): string {
        return this.DOWNLOAD_URL + "?tipo=Usuario&reporte=4&codigo="+codigo;
    }

    



 
}



