import { Component, OnInit } from '@angular/core';
import { CategoriaReporte } from 'src/entities/CategoriaReporte';
import { EmpleadorReporte } from 'src/entities/EmpleadorReporte';

import { HistorialComision } from 'src/entities/HistorialComision';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reportes-admin',
  templateUrl: './reportes-admin.component.html',
  styleUrls: ['./reportes-admin.component.css']
})
export class ReportesAdminComponent implements OnInit {
  reporte1!: HistorialComision[];
  reporte3!: EmpleadorReporte[];
  selectedReport = '';
  fecha1!: string;
fecha2!: string;
showReporte3 = false;

  constructor(private reportesService: ReportesService) { }

  ngOnInit(): void {
    this.reportesService.getReporte1Admin().subscribe(data => {
      this.reporte1 = data;
    });
  }

 

}