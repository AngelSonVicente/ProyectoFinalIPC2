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
import { PreferenciasService } from 'src/services/PreferenciasService';
@Component({
  selector: 'app-editar-telefono-categoria',
  templateUrl: './editar-telefono-categoria.component.html',
  styleUrls: ['./editar-telefono-categoria.component.css']
})
export class EditarTelefonoCategoriaComponent {
  @Output() actualizado: EventEmitter<boolean> = new EventEmitter();

  


  completarEmpresa!:Empresa;

  completarUsuario!: CompletarPerfilUsuario;


  CategoriaYTelefono: boolean = false;

  categorias!: Categoria[];
  FormularioOferta!: FormGroup;

  telefono!: string;
  categoriaSeleccionada!: string;
  telefonos: string[] = [];
  categoriasSeleccionadas: string[] = [];

  usuario!: Usuario;


  selectedJsonFile!: File;
  fileUploaded: boolean = false;
  archivoValido: boolean = false;
  archivoInvalido: boolean = false;
  continuar: boolean = false;

  error: boolean = false;
  saved: boolean = false;




  constructor(private categoriaService: CategoriaService, private completarInformacionSerice: CompletarInformacionService, private usuarioService: UsuarioService,
    private formBuilder: FormBuilder, private route: ActivatedRoute, private ofertaService: OfertaService,
    private router: Router, private telefonosService: TelefonosService, private preferenciaService: PreferenciasService) {



  }



  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;







    this.telefonosService.getTelefonos(this.usuario.codigo.toString()).subscribe({

      next: (list: string[]) => {
        this.telefonos = list;
      }
    });
    this.preferenciaService.getPreferencias(this.usuario.codigo.toString()).subscribe({

      next: (list: string[]) => {
        this.categoriasSeleccionadas = list;
      }
    });



    this.categoriaService.getCategorias().subscribe({

      next: (list: Categoria[]) => {
        this.categorias = list;
      }
    });




  
 
 


  }

  


  agregarTelefono(): void {
    if (this.telefono && !this.telefonos.includes(this.telefono)) {
      this.telefonos.push(this.telefono);

    }
  }

  agregarCategoria(): void {
    if (this.categoriaSeleccionada && !this.categoriasSeleccionadas.includes(this.categoriaSeleccionada)) {
      this.categoriasSeleccionadas.push(this.categoriaSeleccionada);

    }
  }

  eliminarTelefono(index: number): void {
    this.telefonos.splice(index, 1);
  }

  eliminarCategoria(index: number): void {
    this.categoriasSeleccionadas.splice(index, 1);
  }

  ActualizarPerfilUsuario(): void {

    this.FormularioOferta = this.formBuilder.group({
      codigoUsuario: [this.usuario.codigo, []],
      telefonos: [this.telefonos, []],
      categorias: [this.categoriasSeleccionadas, []],


    });

    console.log(this.telefonos)
    console.log(this.categoriaSeleccionada)

    this.completarUsuario = this.FormularioOferta.value as CompletarPerfilUsuario;
    this.completarInformacionSerice.ActualizarPerfilUsuario(this.completarUsuario).subscribe({
      next: () => {
       
       this.saved = true; 
       this.error=false;

       this.emitirPerfilActualizado();

      },
      error: (error: any) => {
        this.saved = false; 
      
       this. error = true;
      }
    });


  }



  

  obtenerNombreCategoria(codigoCategoria: string | number): string {
    const categoriaEncontrada = this.categorias.find(categoria => categoria.codigo.toString() === String(codigoCategoria));
    return categoriaEncontrada ? categoriaEncontrada.nombre : 'Desconocida';
  }



  emitirPerfilActualizado() {
    this.actualizado.emit(true);
  }





}
