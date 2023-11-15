import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class CargaJsonService {



    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/files";
    constructor(private httpClient: HttpClient) {}

    public uploadFile(carnet: string, fileToUpload: File): Observable<void> {
      let reader = new FileReader();
      reader.readAsText(fileToUpload);
  
      return new Observable<void>((observer) => {
        reader.onloadend = () => {
          if (reader.result != null) {
            let formData: FormData = new FormData();
            formData.append("carnet", carnet);
            formData.append("jsonData", reader.result.toString());
  
            this.httpClient.post<void>(this.API_URL + "/v1/CargaJson", formData)
              .subscribe(
                () => {
                  observer.next();
                  observer.complete();
                },
                (error) => {
                  observer.error(error);
                }
              );
          } else {
            console.error("El resultado del lector es nulo.");
            observer.error("Error al leer el archivo JSON");
          }
        };
      });
    }
}