import { Component } from '@angular/core';
import { RecuperarCuenta } from 'src/entities/RecuperarCuenta';
import { RecuperarCuentaService } from 'src/services/RecuperarCuentaService';

@Component({
  selector: 'app-buscar-usuario',
  templateUrl: './buscar-usuario.component.html',
  styleUrls: ['./buscar-usuario.component.css']
})
export class BuscarUsuarioComponent {
  error: boolean = false;
  corrreoEnviado: boolean = false;

  cuenta!: RecuperarCuenta;

  constructor(private recuperarCuenta: RecuperarCuentaService) {
    this.cuenta = new RecuperarCuenta;
  }

  enviarCorreo() {


    if (this.cuenta.correo != null) {
      this.recuperarCuenta.enviarCorreo(this.cuenta).subscribe({
        next: (confirmacion: RecuperarCuenta) => {

          this.corrreoEnviado = true;
          this.error = false;
        },
        error: (error: any) => {
          this.error = true;
          this.corrreoEnviado = false;

        }
      });
    }

  }



}
