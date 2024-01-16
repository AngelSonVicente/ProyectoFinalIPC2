import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Comision } from 'src/entities/Comision';
import { Empresa } from 'src/entities/Empresa';
import { Oferta } from 'src/entities/Oferta';
import { Usuario } from 'src/entities/Usuario';
import { ComisionService } from 'src/services/ComisionService';
import { EmpresaService } from 'src/services/EmpresaService';
import { FacturaService } from 'src/services/FacturaService';
import { OfertaService } from 'src/services/OfertaService';

@Component({
  selector: 'app-facturacion',
  templateUrl: './facturacion.component.html',
  styleUrls: ['./facturacion.component.css']
})
export class FacturacionComponent {
  codigo!: string;
  oferta!: Oferta;
  empresa!: Empresa;
  usuario!:Usuario;
  comision!: Comision;
  downloadUrl: string;
  

  constructor(private route: ActivatedRoute, private empresaService: EmpresaService, 
    private ofertaService: OfertaService, private comisionService : ComisionService,
    private fileService: FacturaService) { 

      this.downloadUrl='';
 

    }

  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario= jsonUsuario ? JSON.parse(jsonUsuario) : null;    
  


    this.route.params.subscribe((params) => {
      this.codigo = params['codigo'];
    });


    this.ofertaService.getOferta(this.codigo).subscribe({
      next: (ofertaBAck: Oferta) => {
        
        this.oferta = ofertaBAck;
        
       

      },
      error: (error: any) => {
        console.log("error");
      }
    });
    this.empresaService.getEmpresa(this.usuario.codigo.toString()).subscribe({
      next: (empresaBack: Empresa) => {
        

        this.empresa = empresaBack;

      },
      error: (error: any) => {
        console.log("error");
      }
    });
    this.comisionService.getComision("ultimo").subscribe({
      next: (comisionBack: Comision) => {
        

        this.comision = comisionBack;

      },
      error: (error: any) => {
        console.log("error");
      }
    });


    this.downloadUrl = this.fileService.generarfacturaPDF(this.codigo,this.usuario.codigo.toString());
  

  }

  getTarjeta(): string {
    if (this.empresa && this.empresa.numeroTarjeta && this.empresa.numeroTarjeta.length >= 4) {
      return this.empresa.numeroTarjeta.slice(-4);
    } else {
      return this.empresa.numeroTarjeta;  
    }
  }

  PDF(){


  }

  
  


}
