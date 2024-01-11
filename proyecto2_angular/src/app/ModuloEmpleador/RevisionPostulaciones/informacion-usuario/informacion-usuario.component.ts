import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, MaxLengthValidator, Validators } from '@angular/forms';
import { Usuario } from 'src/entities/Usuario';
import { UsuarioService } from 'src/services/UsuarioService';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Categoria } from 'src/entities/Categoria';
import { Oferta } from 'src/entities/Oferta';
import { HoraDisponibleService } from 'src/services/HoraDisponibleService';
import { HoraDisponible } from 'src/entities/HoraDisponible';
import { EntrevistasService } from 'src/services/EntrevistaService';
import { Entrevista } from 'src/entities/Entrevista';
import { Solicitudes } from 'src/entities/Solicitudes';
import { SolicitudesService } from 'src/services/SolicitudesService';


@Component({
  selector: 'app-informacion-usuario',
  templateUrl: './informacion-usuario.component.html',
  styleUrls: ['./informacion-usuario.component.css']
})
export class InformacionUsuarioComponent {

  @Output() confirmado: EventEmitter<boolean> = new EventEmitter<boolean>();

  codigoSolicitud!: string;
  codigoCreado!: string;
  agendarEntrevista!: boolean;


  entrevista!: Entrevista;
  solicitud!: Solicitudes;
  horasDisponibles: HoraDisponible[] = [];
  codigoOferta!: string;
  FomularioEntrevista!: FormGroup;
  oferta!: Oferta;
  categorias!: Categoria[];
  usuario!: Usuario;
  postulante!: Usuario;
  codigo!: string;
  saved: boolean;
  rechazado!: boolean;
  pdfSrc!: SafeUrl; // Declarar pdfSrc como SafeUrl



  constructor(private formBuilder: FormBuilder,
    private usuarioService: UsuarioService, private sanitizer: DomSanitizer
    , private horaService: HoraDisponibleService, private entrevistaService: EntrevistasService,
    private solicitudService: SolicitudesService) {
    this.saved = false;

    this.solicitud = new Solicitudes();


  }

  ngOnInit(): void {

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    this.usuarioService.getUsuarioID(this.codigo).subscribe({
      next: (usuario: Usuario) => {
        this.postulante = usuario;
        if (this.postulante.cv) {
          this.pdfSrc = this.mostrarPDF(this.postulante.cv);
        }
      }
    });

    this.FomularioEntrevista = this.formBuilder.group({
      fecha: [null, [Validators.required, this.fechaNoMenorQueHoyValidator]],
      ubicacion: [null, [Validators.required, Validators.maxLength(100)]],

      hora: [null, [Validators.required]],



    });





  }

  fechaNoMenorQueHoyValidator(control: any) {
    const fechaIngresada = new Date(control.value);
    const fechaHoy = new Date();

    //le quitamos un dia porque no jala con la fecha actual :c
    fechaHoy.setDate(fechaHoy.getDate() - 1);

    if (fechaIngresada.getTime() < fechaHoy.getTime()) {
      return { fechaInvalida: true };
    }

    return null;
  }

  onFechaUbicacionChange() {
    const fechaSeleccionada = this.FomularioEntrevista.get('fecha')?.value;
    const ubicacionSeleccionada = this.FomularioEntrevista.get('ubicacion')?.value;
    this.horaService.getHorasDisponibles(fechaSeleccionada, ubicacionSeleccionada, this.codigoOferta).subscribe({

      next: (list: HoraDisponible[]) => {
        this.horasDisponibles = list;

      }
    });

  }


  submit(): void {

    if (this.FomularioEntrevista.valid) {
      console.log("entro a submit");
      this.entrevista = this.FomularioEntrevista.value as Entrevista;
      this.entrevista.codigoOferta = this.codigoOferta;
      this.entrevista.codigoUsuario = this.postulante.codigo.toString();

      this.solicitud.codigo = this.codigoSolicitud;
      this.solicitud.codigoOferta = this.codigoOferta;
      this.entrevista.codigoSolicitud = this.codigoSolicitud;
      this.solicitud.estado = "Entrevista";

      this.entrevistaService.crearEntrevista(this.entrevista).subscribe({
        next: (created: Entrevista) => {
          console.log("creado " + created);
          this.codigoCreado = created.codigo;
          this.saved = true;
          this.confirmar();
          this.limpiar()

        },
        error: (error: any) => {
          console.log("error");
        }
      });



    }
  }
  limpiar(): void {
    this.FomularioEntrevista.reset({

    });

  }




  confirmar() {
    this.confirmado.emit(true);
  }

  rechazarPostulacion() {



    this.solicitudService.actualizarEstadoSolicitud(this.codigoSolicitud, this.codigoOferta, "Rechazado").subscribe({
      next: (created: Solicitudes) => {
        console.log("rechazado " + created);
        this.rechazado = true;
        this.confirmar();
      },
      error: (error: any) => {
        console.log("error -----------------------------");
      }
    });



  }

  mostrarPDF(cv: any): any {
    if (cv instanceof Array) {
      const arrayBuffer = new Uint8Array(cv).buffer;
      const blob = new Blob([arrayBuffer], { type: 'application/pdf' });
      return this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(blob));
    }
    return null;
  }


}
