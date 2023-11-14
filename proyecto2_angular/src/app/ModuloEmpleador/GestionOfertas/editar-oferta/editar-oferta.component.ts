import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { OfertaService } from 'src/services/OfertaService';

@Component({
  selector: 'app-editar-oferta',
  templateUrl: './editar-oferta.component.html',
  styleUrls: ['./editar-oferta.component.css']
})
export class EditarOfertaComponent {
  codigo!: string;
  usuario!: Usuario;
  oferta !: Oferta;
  ofertaActualziada !: Oferta;
  FormularioOferta!: FormGroup;
  saved: boolean = false;


  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private ofertaService: OfertaService, private router: Router) { }


  ngOnInit() {

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    this.route.params.subscribe((params) => {
      this.codigo = params['codigo'];
    });



    this.ofertaService.getOferta(this.codigo).subscribe({
      next: (oferta: Oferta) => {
        this.oferta = oferta;
        this.FormularioOferta = this.formBuilder.group({
          nombre: [this.oferta.nombre, [Validators.required, Validators.maxLength(50)]],
          descripcion: [this.oferta.descripcion, [Validators.required, Validators.maxLength(500)]],
          fechaLimite: [this.oferta.fechaLimite, [Validators.required]],
          salario: [this.oferta.salario, [Validators.required, this.validarNumeroMayorQueCero]],
          modadidad: [this.oferta.modadidad, [Validators.required, Validators.maxLength(30)]],
          ubicacion: [this.oferta.ubicacion, [Validators.required, Validators.maxLength(100)]],
          detalle: [this.oferta.detalle, [Validators.required, Validators.maxLength(2000)]],


        });
      }
    });


  }


  validarNumeroMayorQueCero(control: any) {
    if (isNaN(control.value) || control.value <= 0) {
      return { 'numeroInvalido': true };
    }
    return null;
  }

  submit(): void {
    if (this.FormularioOferta.valid) {
      this.ofertaActualziada = this.FormularioOferta.value as Oferta;
      this.oferta.nombre = this.ofertaActualziada.nombre;
      this.oferta.descripcion = this.ofertaActualziada.descripcion;
      this.oferta.fechaLimite = this.ofertaActualziada.fechaLimite;
      this.oferta.salario = this.ofertaActualziada.salario;
      this.oferta.modadidad = this.ofertaActualziada.modadidad;
      this.oferta.ubicacion = this.ofertaActualziada.ubicacion;
      this.oferta.detalle = this.ofertaActualziada.detalle;

      this.ofertaService.actualizarOferta(this.oferta).subscribe({
        next: (created: Oferta) => {
          console.log("creado " + created);
          this.saved = true;
        },
        error: (error: any) => {
          console.log("error");
        }
      });
    }
  }
  limpiar(): void {
    this.FormularioOferta.reset({

    });

  }

}
