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
import { CurriculumService } from 'src/services/CurriculumService';
@Component({
  selector: 'app-editar-cv',
  templateUrl: './editar-cv.component.html',
  styleUrls: ['./editar-cv.component.css']
})
export class EditarCVComponent {
  pdfSrc!: SafeUrl; // Declarar pdfSrc como SafeUrl
  usuario!: Usuario;


  selectedPDFFile!: File;
  fileUploaded: boolean = false;
  archivoValido: boolean = false;
  archivoInvalido: boolean = false;
  error: boolean = false;
  actualizado: boolean = false;

  constructor(private formBuilder: FormBuilder,
    private usuarioService: UsuarioService, private sanitizer: DomSanitizer
    , private horaService: HoraDisponibleService, private entrevistaService: EntrevistasService,
    private solicitudService: SolicitudesService, private curriculumService: CurriculumService){

  }

  ngOnInit(): void {

    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;

    this.pdfSrc = this.mostrarPDF(this.usuario.cv);
        
  }

  mostrarPDF(cv: any): any {
    if (cv instanceof Array) {
      const arrayBuffer = new Uint8Array(cv).buffer;
      const blob = new Blob([arrayBuffer], { type: 'application/pdf' });
      return this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(blob));
    }
    return null;
  }
  onPDFFileSelected(event: any): void {
    const selectedFiles: FileList = event.target.files;
    if (selectedFiles) {
      const jsonFile: File = selectedFiles[0];
      if (jsonFile.type === 'application/pdf') {
        this.selectedPDFFile = jsonFile;
        this.archivoInvalido = false;
        this.archivoValido = true;
      } else {
        this.archivoInvalido = true;
        this.archivoValido = false;
        console.error(`El archivo ${jsonFile.name} no es un archivo PDF válido.`);
      }
    }
  }


  ActualizarCurriculum(): void {

    this.curriculumService.actualizarCurriculum(this.usuario.codigo.toString(), this.selectedPDFFile).subscribe({
      next: (actualizado: Usuario) => {

        this.usuario = actualizado;

        this.actualizado = true;
        this.error=false;
        localStorage.setItem('usuario', JSON.stringify(this.usuario));
        this.pdfSrc =  this.mostrarPDF(actualizado.cv);

      },
      error: (error: any) => {

        this.actualizado = false;
        this.error=true;
      }
    });


  }


}
