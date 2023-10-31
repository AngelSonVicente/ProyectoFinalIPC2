import { Injectable } from "@angular/core";
import { Login } from "../../src/entities/Login";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {Usuario} from 'src/entities/Usuario';
@Injectable({
    providedIn: 'root'
})
export class LoginService {

    readonly API_URL = "http://localhost:8080/proyecto2_api/v1/";

    constructor(private httpClient: HttpClient) {}

    public logear(usuario: Usuario): Observable<Usuario> {

        console.log('connectando con el BE: ' + usuario);
        return this.httpClient.post<Usuario>(this.API_URL + "login", usuario);
    }

 
}