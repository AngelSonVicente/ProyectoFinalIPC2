import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-publicidad',
  templateUrl: './publicidad.component.html',
  styleUrls: ['./publicidad.component.css']
})
export class PublicidadComponent {
  @Output() publicidadCerrada: EventEmitter<void> = new EventEmitter<void>();
  mostrarPublicidad: boolean = false;
  Publicidad!: string;

  constructor() { }

  ngOnInit(): void {

    this.mostrar();
  }

  cerrarPublicidad(): void {
    this.mostrarPublicidad = false;
    this.publicidadCerrada.emit();
  }

  mostrar(): void {

    this.Publicidad="assets/Publicidad/publicidad"+ this.generarNumeroAleatorio()+".png";
    const probabilidad = Math.random();
    // Mostrar la publicidad con una probabilidad del 30%
    this.mostrarPublicidad = probabilidad <= 0.3
    ;
  
  }

  generarNumeroAleatorio(): number {
    // Genera un número aleatorio entre 1 y 5
    return Math.floor(Math.random() * 5) + 1;
  }






}
