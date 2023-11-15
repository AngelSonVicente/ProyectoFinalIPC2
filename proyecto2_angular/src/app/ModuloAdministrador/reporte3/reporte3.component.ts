import { Component, Input, OnInit } from '@angular/core';
import { EmpleadorReporte } from 'src/entities/EmpleadorReporte';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reporte3',
  templateUrl: './reporte3.component.html',
  styleUrls: ['./reporte3.component.css']
})
export class Reporte3Component {

  reporte3!: EmpleadorReporte[];
  

  constructor(private reportesService: ReportesService) { }
ngOnInit(): void {

  this.reportesService.getReporte3Admin().subscribe(data => {
    this.reporte3 = data;
  });

}
 
}