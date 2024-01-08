import { Component, OnInit } from '@angular/core';
import { HistorialComision } from 'src/entities/HistorialComision';
import { ReportesPDFService } from 'src/services/ReportesPDFService';

import { ReportesService } from 'src/services/ReportesService';
@Component({
  selector: 'app-reporte1',
  templateUrl: './reporte1.component.html',
  styleUrls: ['./reporte1.component.css']
})
export class Reporte1Component {
  reporte1!: HistorialComision[];

  downloadUrl: string;

  constructor(private fileService: ReportesPDFService ,private reportesService: ReportesService) {
    this.downloadUrl = '';
 
   }

  ngOnInit(): void {
    this.reportesService.getReporte1Admin().subscribe(data => {
      this.reporte1 = data;
    });
    this.downloadUrl = this.fileService.getReport1Admin();
  


  }
  PDF(){


  }



}
