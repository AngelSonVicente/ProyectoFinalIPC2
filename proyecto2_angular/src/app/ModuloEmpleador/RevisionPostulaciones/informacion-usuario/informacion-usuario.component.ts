import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Usuario } from 'src/entities/Usuario';
import { UsuarioService } from 'src/services/UsuarioService';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';


@Component({
  selector: 'app-informacion-usuario',
  templateUrl: './informacion-usuario.component.html',
  styleUrls: ['./informacion-usuario.component.css']
})
export class InformacionUsuarioComponent {
  
  @Output() confirmado: EventEmitter<boolean> = new EventEmitter<boolean>();

  usuario!: Usuario;
  postulante!: Usuario;
  codigo!: string;
  saved: boolean;
  pdfSrc!: SafeUrl; // Declarar pdfSrc como SafeUrl

  

  constructor(private formBuilder: FormBuilder,
    private usuarioService: UsuarioService, private sanitizer: DomSanitizer) {
    this.saved = false;

  }

  ngOnInit(): void {
    
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    

    this.usuarioService.getUsuarioID(this.codigo).subscribe({
      next: (usuario: Usuario) => {
        this.postulante = usuario;
        if (this.postulante.cv) {
          this.pdfSrc = this.mostrarPDF(this.postulante.cv);
        }
      }
    });
  }

  
 
 

  
  confirmarELiminacion() {
    this.confirmado.emit(true);

  }

  mostrarPDF(cv: any): any {
    if (cv instanceof Array) {
      // Convierte el array de bytes a un ArrayBuffer
      const arrayBuffer = new Uint8Array(cv).buffer;
  
      // Convierte el ArrayBuffer a Blob
      const blob = new Blob([arrayBuffer], { type: 'application/pdf' });
  
      // Crea una URL segura usando createObjectURL
      return this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(blob));
    }
  
    return null;
  }
  

}
