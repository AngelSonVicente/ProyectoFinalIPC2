import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Empresa } from 'src/entities/Empresa';
import { EmpresaService } from 'src/services/EmpresaService';

@Component({
  selector: 'app-perfil-empleador',
  templateUrl: './perfil-empleador.component.html',
  styleUrls: ['./perfil-empleador.component.css']
})
export class PerfilEmpleadorComponent {
  codigo!: string;
  empresa!: Empresa;


  constructor(private route: ActivatedRoute, private empresaService: EmpresaService) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.codigo = params['codigo'];
    });

    this.empresaService.getEmpresa(this.codigo).subscribe({
      next: (empresa: Empresa) => {
        this.empresa = empresa;

  
      }
    });
  
  
  
  }
}
