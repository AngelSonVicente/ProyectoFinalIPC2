import { Component, Input, OnInit } from '@angular/core';
import { EmpleadorReporte } from 'src/entities/EmpleadorReporte';
import { ReportesPDFService } from 'src/services/ReportesPDFService';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reporte3',
  templateUrl: './reporte3.component.html',
  styleUrls: ['./reporte3.component.css']
})
export class Reporte3Component {
  downloadUrl: string;
  fecha1: String ="sinfecha";
  fecha2: String ="sinfecha";
  reporte3!: EmpleadorReporte[];


  constructor(private fileService: ReportesPDFService, private reportesService: ReportesService) {
    this.downloadUrl = '';
  }
  ngOnInit(): void {

    this.reportesService.getReporte3Admin().subscribe(data => {
      this.reporte3 = data;
    });
   

  }

  PDF() {
    this.downloadUrl = this.fileService.getReport3Admin(this.fecha1, this.fecha2);
    window.open(this.downloadUrl);
  }

}