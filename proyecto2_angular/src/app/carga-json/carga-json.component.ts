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
    }
  }
}
