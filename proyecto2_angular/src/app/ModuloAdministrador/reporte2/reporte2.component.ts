import { Component, OnInit } from '@angular/core';
import { EmpleadorReporte } from 'src/entities/EmpleadorReporte';
import { ReportesPDFService } from 'src/services/ReportesPDFService';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reporte2',
  templateUrl: './reporte2.component.html',
  styleUrls: ['./reporte2.component.css']
})
export class Reporte2Component implements OnInit {
  reporte2!: EmpleadorReporte[];
  downloadUrl: string;


  constructor(private fileService: ReportesPDFService,private reportesService: ReportesService) {
    this.downloadUrl = '';

  }

  ngOnInit(): void {
    this.reportesService.getReporte2Admin().subscribe(data => {
      this.reporte2 = data;
    });
    this.downloadUrl = this.fileService.getReport2Admin();
  

  }
}