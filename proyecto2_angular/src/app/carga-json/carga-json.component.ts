import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CargaJsonService } from 'src/services/CargaJsonService';

@Component({
  selector: 'app-carga-json',
  templateUrl: './carga-json.component.html',
  styleUrls: ['./carga-json.component.css']
})
export class CargaJsonComponent {
  selectedFile!: File;
  fileUploaded: boolean = false;
  archivoValido: boolean = false;
  archivoInvalido: boolean = false;


constructor( private route: ActivatedRoute,
  private fileService: CargaJsonService) { }

  uploadToServer() : void {
    this.fileUploaded = false;


    if (this.selectedFile != null) {
      this.fileService.uploadFile("0", this.selectedFile).subscribe({
        next: () => {
          this.fileUploaded = true;
        }
      });
    }
  }

  processFile(event: Event): void {
    let files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this.selectedFile = files[0];

      if (this.selectedFile) {
        console.log('Nombre del archivo:', this.selectedFile.name);
        console.log('Tipo del archivo:', this.selectedFile.type);
  
        // Verificar si es un archivo JSON
        if (this.selectedFile.type === 'application/json') {
          this.archivoInvalido = false;
          this.archivoValido = true;

          
        } else {
          this.archivoInvalido = true;
          this.archivoValido = false;
        }
      }

    }

    
  }
}
