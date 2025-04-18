import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router'
import { Login } from '../../entities/Login';
import { Usuario } from '../../entities/Usuario'
import { LoginService } from '../../services/LoginService';
import { Empresa } from 'src/entities/Empresa';
import { EmpresaService } from 'src/services/EmpresaService';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  usuario!: Usuario;
  invitado!: boolean;

  empresa!: Empresa;
  error: boolean = false;



  userRole?: string;



  constructor(private empresaService: EmpresaService, private formBuilder: FormBuilder, private route: ActivatedRoute, private loginService: LoginService, private router: Router) {


    this.usuario = new Usuario;


  }




  onSubmit() {
    this.loginService.logear(this.usuario).subscribe(
      usuario => {


        if (usuario) {
          localStorage.setItem('usuario', JSON.stringify(usuario));
          this.invitado = false;
          console.log("Tipo de Usuario en el Local Storage: " + usuario.tipo);
          if (usuario.tipo == "Admin") {
            this.router.navigate(['Proyecto2/Modulo/Administrador']);

          }

          if (usuario.tipo == "Usuario") {

            if (usuario.cv == null) {
              this.router.navigate(['Proyecto2/CompletarInformacion']);
            } else {
              this.router.navigate(['Proyecto2/Modulo/Usuario']);
            }


          }

          if (usuario.tipo == "Invitado") {
            this.router.navigate(['Proyecto2/Modulo/Usuario']);

          }
          if(usuario.tipo=="Empleador"){

            this.empresaService.getEmpresa(usuario.codigo.toString()).subscribe({
              next: (empresa: Empresa) => {
                this.router.navigate(['Proyecto2/Modulo/Empleador']);
  
              },
              error: (error: any) => {
                this.router.navigate(['Proyecto2/CompletarInformacion']);
  
  
              }
            });


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

  Invitado() {
    this.usuario.tipo = "Invitado";

    this.invitado = true;
    this.onSubmit();
  }



}


