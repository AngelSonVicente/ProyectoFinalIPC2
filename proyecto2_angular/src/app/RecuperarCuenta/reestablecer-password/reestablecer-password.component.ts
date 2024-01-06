import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RecuperarCuenta } from 'src/entities/RecuperarCuenta';
import { ReestablcerPasswordService } from 'src/services/ReestablcerPasswordService';

@Component({
  selector: 'app-reestablecer-password',
  templateUrl: './reestablecer-password.component.html',
  styleUrls: ['./reestablecer-password.component.css']
})
export class ReestablecerPasswordComponent {
  cuenta!: RecuperarCuenta;
  token!: string;

  PasswordCambaida=false;

  tokenValido!: boolean;
  error: boolean = false;

  constructor(private route: ActivatedRoute, private reestablcerPasswordService: ReestablcerPasswordService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.token = params['token'];
    });


    if (this.token != null) {
      this.reestablcerPasswordService.getInformacionToken(this.token).subscribe({
        next: (confirmacion: RecuperarCuenta) => {

          this.tokenValido = true;
          this.cuenta = confirmacion;


        },
        error: (error: any) => {
          this.error = true;

        }
      });
    } else {
      this.error = true;

    }


    






  }

  PasswordNueva() {

  this.reestablcerPasswordService.recuperarPassword(this.cuenta).subscribe({
        next: (confirmacion: RecuperarCuenta) => {

          this.PasswordCambaida = true;
          this.error = false;
        },
        error: (error: any) => {
          this.error = true;
          this.PasswordCambaida = false;

        }
      });
    

  }

}
