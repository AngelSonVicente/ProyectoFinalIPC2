import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reestablecer-password',
  templateUrl: './reestablecer-password.component.html',
  styleUrls: ['./reestablecer-password.component.css']
})
export class ReestablecerPasswordComponent {

  token!: string;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Obtener el token de la URL
    this.route.params.subscribe(params => {
      this.token = params['token'];
      // Ahora puedes usar el token como necesites
      console.log('Token:', this.token);
    });
  }

}
