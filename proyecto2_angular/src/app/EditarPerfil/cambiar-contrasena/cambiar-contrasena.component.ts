import { Component } from '@angular/core';
import { CambiarPassword } from 'src/entities/CambiarPassword';
import { Usuario } from 'src/entities/Usuario';
import { ReestablcerPasswordService } from 'src/services/ReestablcerPasswordService';

@Component({
  selector: 'app-cambiar-contrasena',
  templateUrl: './cambiar-contrasena.component.html',
  styleUrls: ['./cambiar-contrasena.component.css']
})
export class CambiarContrasenaComponent {
  usuario!: Usuario;
  cambiarPassword: CambiarPassword;

  error:boolean=false;
  actualizado:boolean=false;

  constructor(private reestablecerPasswordService : ReestablcerPasswordService) {
    this.cambiarPassword = new CambiarPassword();

   }



  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;


  }
  CambiarPassword(): void {
    this.cambiarPassword.codigoUsuario = this.usuario.codigo.toString();

    
    this.reestablecerPasswordService.actualizarPassword(this.cambiarPassword).subscribe({
      next: () => {

      this.actualizado = true;
      this.error=false;

      },
      error: (error: any) => {

        this.actualizado = false;
        this.error=true;
      }
    });


  }


}
