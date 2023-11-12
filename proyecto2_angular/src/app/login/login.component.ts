import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router'
import { Login } from '../../entities/Login';
import { Usuario } from '../../entities/Usuario'
import { LoginService } from '../../services/LoginService';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  usuario!: Usuario;

  error: boolean = false;



  userRole?: string;



  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private loginService: LoginService, private router: Router) {


    this.usuario = new Usuario;


  }




  onSubmit() {
    this.loginService.logear(this.usuario).subscribe(
      usuario => {
        if (usuario) {
          localStorage.setItem('usuario', JSON.stringify(usuario));
          if (usuario.tipo == "Admin") {
            this.router.navigate(['Proyecto2/Modulo/Administrador']);

          }

          if (usuario.tipo == "Usuario") {
            this.router.navigate(['Proyecto2/Modulo/Usuario']);

          }
          if (usuario.tipo == "Empleador") {
            this.router.navigate(['Proyecto2/Modulo/Empleador']);

          }





        } else {
          this.error = true;
        }
      },
      error => {
        console.log('Error: ', error);
        this.error = true;
      }
    );
  }



}


