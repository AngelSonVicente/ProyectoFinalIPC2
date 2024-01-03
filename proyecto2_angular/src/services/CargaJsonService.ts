import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CargaJsonService {

  readonly API_URL = "http://localhost:8080/proyecto2_api/v1/CargaJson";

  constructor(private httpClient: HttpClient) {}

  public uploadFiles(carnet: string, jsonFile: File, pdfFiles: File[]): Observable<void> {
    let formData: FormData = new FormData();

    // Agregar el archivo JSON
    formData.append("JsonEntrada", jsonFile, jsonFile.name);

    // Agregar los archivos PDF
    for (let i = 0; i < pdfFiles.length; i++) {
      formData.append(`PdfEntrada${i}`, pdfFiles[i], pdfFiles[i].name);
    }

    return this.httpClient.post<void>(this.API_URL + "?carnet=" + carnet, formData);
  }
}
