import { Component } from '@angular/core';
import { CategoriaReporte } from 'src/entities/CategoriaReporte';
import { ReportesService } from 'src/services/ReportesService';

@Component({
  selector: 'app-reporte4',
  templateUrl: './reporte4.component.html',
  styleUrls: ['./reporte4.component.css']
})
export class Reporte4Component {

  
  reporte4!: CategoriaReporte[];
  

  constructor(private reportesService: ReportesService) { }
ngOnInit(): void {

  this.reportesService.getReporte4Admin().subscribe(data => {
    this.reporte4 = data;
  });

}

}
