import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class CargaJsonService {



    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/CargaJson";
    constructor(private httpClient: HttpClient) {}

    public uploadFile(carnet: string, fileToUpload: File): Observable<void> {
      let formData: FormData = new FormData();
      formData.append("datafile", fileToUpload, fileToUpload.name);

      return this.httpClient.post<void>(this.API_URL + "?carnet=" + carnet, formData);
  }
}