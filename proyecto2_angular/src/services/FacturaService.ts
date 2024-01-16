import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class FacturaService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/FacturaPDF";
    readonly DOWNLOAD_URL = "http://localhost:8080/proyecto2_api/v1/FacturaPDF";


    constructor(private httpClient: HttpClient) {}



    public generarfacturaPDF( codigoOferta: string, codigoEmpresa:string): string {
        return this.DOWNLOAD_URL + "?codigoOferta="+codigoOferta+"&codigoEmpresa="+codigoEmpresa;
    }
    

    



 
}
