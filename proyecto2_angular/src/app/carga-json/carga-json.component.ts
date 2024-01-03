import { Component, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CargaJsonService } from 'src/services/CargaJsonService';

@Component({
  selector: 'app-carga-json',
  templateUrl: './carga-json.component.html',
  styleUrls: ['./carga-json.component.css']
})
export class CargaJsonComponent {
  selectedJsonFile!: File;
  pdfFiles: File[][] = [[]];
  fileUploaded: boolean = false;
  archivoValido: boolean = false;
  archivoInvalido: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private fileService: CargaJsonService,  private renderer: Renderer2
  ) {}

  onJsonFileSelected(event: any): void {
    const selectedFiles: FileList = event.target.files;
    if (selectedFiles) {
      const jsonFile: File = selectedFiles[0];
      if (jsonFile.type === 'application/json') {
        this.selectedJsonFile = jsonFile;
        this.archivoInvalido = false;
        this.archivoValido = true;
      } else {
        this.archivoInvalido = true;
        this.archivoValido = false;
        console.error(`El archivo ${jsonFile.name} no es un archivo JSON válido.`);
      }
    }
  }
  
  seleccionarArchivo(index: number): void {
    const fileInput = this.renderer.selectRootElement(`#fileInput${index}`);
    fileInput.click();
  }
  
  onPdfFileSelected(event: any, index: number): void {
    const selectedFiles: FileList = event.target.files;
    if (selectedFiles) {
      this.pdfFiles[index] = [];
      for (let i = 0; i < selectedFiles.length; i++) {
        const file: File = selectedFiles[i];
        if (file.type === 'application/pdf') {
          this.pdfFiles[index].push(file);
        } else {
          console.error(`El archivo ${file.name} no es un archivo PDF válido.`);
        }
      }
    }
  }

  agregarInputArchivo(): void {
    this.pdfFiles.push([]);
  }
  eliminarPdf(index: number): void {
    this.pdfFiles[index] = [];
  }

  uploadToServer(): void {
    this.fileUploaded = false;

    if (this.archivoValido && this.pdfFiles.length > 0) {
      // Puedes hacer algo con los archivos JSON y PDF seleccionados
      console.log('JSON:', this.selectedJsonFile);
      console.log('PDF:', this.pdfFiles);
      
      // Luego, puedes llamar a tu servicio para cargar los archivos al servidor
      this.fileService.uploadFiles("0", this.selectedJsonFile, this.pdfFiles.flat()).subscribe({
        next: () => {
          this.fileUploaded = true;
        }
      });
    }
  }
}
