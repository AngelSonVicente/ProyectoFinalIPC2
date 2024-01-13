import { Injectable } from "@angular/core";
import { Login } from "../../src/entities/Login";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Usuario } from 'src/entities/Usuario';
import { CompletarPerfilUsuario } from "src/entities/CompletarPerfilUsuario";
import { Empresa } from "src/entities/Empresa";
@Injectable({
  providedIn: 'root'
})
export class CurriculumService {

  readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

  constructor(private httpClient: HttpClient) { }

  

  public actualizarCurriculum(codigoUsuario: string, archivo: File): Observable<Usuario> {
    const formData: FormData = new FormData();
    formData.append('codigoUsuario', JSON.stringify(codigoUsuario));
    formData.append('CV', archivo, archivo.name);
    return this.httpClient.put<Usuario>(this.API_URL + 'Curriculum', formData);
  }



}