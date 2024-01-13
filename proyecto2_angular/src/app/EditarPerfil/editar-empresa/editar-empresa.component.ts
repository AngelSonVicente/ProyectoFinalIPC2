import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/entities/Categoria';
import { Oferta } from 'src/entities/Oferta';
import { CompletarPerfilUsuario } from 'src/entities/CompletarPerfilUsuario';
import { Usuario } from 'src/entities/Usuario';
import { CategoriaService } from 'src/services/CategoriaService';
import { CompletarInformacionService } from 'src/services/CompletarInformacionService';
import { OfertaService } from 'src/services/OfertaService';
import { UsuarioService } from 'src/services/UsuarioService';
import { Empresa } from 'src/entities/Empresa';
import { TelefonosService } from 'src/services/TelefonosService';
import { EmpresaService } from 'src/services/EmpresaService';
@Component({
  selector: 'app-editar-empresa',
  templateUrl: './editar-empresa.component.html',
  styleUrls: ['./editar-empresa.component.css']
})
export class EditarEmpresaComponent {

  @Output() actualizado: EventEmitter<boolean> = new EventEmitter();


  FormularioEmpresa!: FormGroup;

  empresa!: Empresa;

  completarEmpresa!:Empresa;
  telefono!: string;
  categoriaSeleccionada!: string;
  telefonos: string[] = [];
  categoriasSeleccionadas: string[] = [];

  actualizadoo:boolean=false;
  error:boolean=false;

  usuario!: Usuario;

  constructor(private completarInformacionSerice: CompletarInformacionService, private usuarioService: UsuarioService,
    private formBuilder: FormBuilder,private telefonosService: TelefonosService,private empresaService: EmpresaService,
    private router: Router) { }

  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;





    this.telefonosService.getTelefonos(this.usuario.codigo.toString()).subscribe({

      next: (list: string[]) => {
        this.telefonos = list;
      }
    });
    this.empresaService.getEmpresa(this.usuario.codigo.toString()).subscribe({
      next: (empresa: Empresa) => {
        this.empresa = empresa;

        this.FormularioEmpresa = this.formBuilder.group({
          mision: [this.empresa.mision, [Validators.required, Validators.maxLength(200)]],
          vision: [this.empresa.vision, [Validators.required, Validators.maxLength(200)]],
          titularTarjeta: [null, [ Validators.maxLength(50)]],
          numeroTarjeta: [null, []],

          codigoSeguridad: [null, [ Validators.maxLength(3)]],
          telefonos: [this.telefonos, []],
          codigoEmpresa: [this.usuario.codigo, []],
       
          
    
        });
  
      }
    });



 
 
 
  
  }



  


  agregarTelefono(): void {
    if (this.telefono && !this.telefonos.includes(this.telefono)) {
      this.telefonos.push(this.telefono);

    }
  }

  eliminarTelefono(index: number): void {
    this.telefonos.splice(index, 1);
  }



  submit(): void {
    
    this.completarEmpresa = this.FormularioEmpresa.value as Empresa;

  
    this.empresaService.actualizarEmpresa(this.completarEmpresa).subscribe({
      next: () => {
      
        this.actualizadoo=true;
        this.error=false;
        this.emitirPerfilActualizado();

      
        },
      error: (error: any) => {
        console.log("error");
        this.actualizadoo=false;
        this.error=true;
      }
    });
  
}

emitirPerfilActualizado() {
  this.actualizado.emit(true);
}

}
