import { Component, OnInit } from '@angular/core';
import { HistorialComision } from 'src/entities/HistorialComision';

import { ReportesService } from 'src/services/ReportesService';
@Component({
  selector: 'app-reporte1',
  templateUrl: './reporte1.component.html',
  styleUrls: ['./reporte1.component.css']
})
export class Reporte1Component {
  reporte1!: HistorialComision[];

  constructor(private reportesService: ReportesService) { }

  ngOnInit(): void {
    this.reportesService.getReporte1Admin().subscribe(data => {
      this.reporte1 = data;
    });
  }

}
