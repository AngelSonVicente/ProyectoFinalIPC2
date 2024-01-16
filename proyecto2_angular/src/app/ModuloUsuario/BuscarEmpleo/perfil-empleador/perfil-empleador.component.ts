import { Component, EventEmitter, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Empresa } from 'src/entities/Empresa';
import { EmpresaService } from 'src/services/EmpresaService';
import { TelefonosService } from 'src/services/TelefonosService';

@Component({
  selector: 'app-perfil-empleador',
  templateUrl: './perfil-empleador.component.html',
  styleUrls: ['./perfil-empleador.component.css']
})
export class PerfilEmpleadorComponent {
  codigo!: string;
  empresa!: Empresa;


  telefonos: string[] = [];
  constructor(private route: ActivatedRoute, private empresaService: EmpresaService
    , private telefonosService : TelefonosService) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.codigo = params['codigo'];
    });

    this.empresaService.getEmpresa(this.codigo).subscribe({
      next: (empresa: Empresa) => {
        this.empresa = empresa;

  
      }
    });

    this.telefonosService.getTelefonos(this.codigo).subscribe({

      next: (list: string[]) => {
        this.telefonos = list;
      }
    });
  
  
  
  }

  
 
}
