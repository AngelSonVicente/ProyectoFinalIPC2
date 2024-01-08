import { Component } from '@angular/core';
import { CategoriaReporte } from 'src/entities/CategoriaReporte';
import { ReportesPDFService } from 'src/services/ReportesPDFService';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reporte4',
  templateUrl: './reporte4.component.html',
  styleUrls: ['./reporte4.component.css']
})
export class Reporte4Component {
  fecha1: String = "sinfecha";
  fecha2: String = "sinFecha";
  downloadUrl: string;




  reporte4!: CategoriaReporte[];


  constructor(private fileService: ReportesPDFService, private reportesService: ReportesService) {
    this.downloadUrl = '';
  }
  ngOnInit(): void {

    this.reportesService.getReporte4Admin().subscribe(data => {
      this.reporte4 = data;
    });



  }

  PDF() {
    this.downloadUrl = this.fileService.getReport4Admin(this.fecha1, this.fecha2);
    window.open(this.downloadUrl);
  }
}
