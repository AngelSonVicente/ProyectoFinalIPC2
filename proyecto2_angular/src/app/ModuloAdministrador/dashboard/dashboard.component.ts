import { Component } from '@angular/core';
import { DashBoard } from 'src/entities/DashBoard';
import { UsuarioService } from 'src/services/UsuarioService';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
dash!: DashBoard

  constructor(private suarioService: UsuarioService) { }

  ngOnInit(): void {

    this.suarioService.getDashoBoard().subscribe({
      next: (dashb: DashBoard) => {
        console.log("creado " + dashb);
       this.dash=dashb;
      },
      error: (error: any) => {
        console.log("error");
      }
    }); 
  }
}
