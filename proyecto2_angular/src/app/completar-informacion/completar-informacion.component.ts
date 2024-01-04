import { Component, OnInit } from '@angular/core';
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

@Component({
  selector: 'app-completar-informacion',
  templateUrl: './completar-informacion.component.html',
  styleUrls: ['./completar-informacion.component.css']
})
export class CompletarInformacionComponent {


  completarEmpresa!:Empresa;

  completarUsuario!: CompletarPerfilUsuario;


  CategoriaYTelefono: boolean = false;

  categorias!: Categoria[];
  FormularioOferta!: FormGroup;
  FormularioEmpresa!: FormGroup;

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



  constructor(private categoriaService: CategoriaService, private completarInformacionSerice: CompletarInformacionService, private usuarioService: UsuarioService,
    private formBuilder: FormBuilder, private route: ActivatedRoute, private ofertaService: OfertaService,
    private router: Router) {



  }



  ngOnInit(): void {
    let jsonUsuario = localStorage.getItem('usuario');
    this.usuario = jsonUsuario ? JSON.parse(jsonUsuario) : null;




    this.categoriaService.getCategorias().subscribe({

      next: (list: Categoria[]) => {
        this.categorias = list;
      }
    });




    this.FormularioOferta = this.formBuilder.group({
      codigoUsuario: [this.usuario.codigo, []],
      telefonos: [this.telefonos, []],
      categorias: [this.categoriasSeleccionadas, []],


    });
 
 
 
    this.FormularioEmpresa = this.formBuilder.group({
      mision: [null, [Validators.required, Validators.maxLength(200)]],
      vision: [null, [Validators.required, Validators.maxLength(200)]],
      titularTarjeta: [null, [Validators.required, Validators.maxLength(50)]],
      numeroTarjeta: [null, [Validators.required]],
      codigoSeguridad: [null, [Validators.required, Validators.maxLength(3)]],
      telefonos: [this.telefonos, []],
      codigoEmpresa: [this.usuario.codigo, []],
   
      

    });


  }

  onJsonFileSelected(event: any): void {
    const selectedFiles: FileList = event.target.files;
    if (selectedFiles) {
      const jsonFile: File = selectedFiles[0];
      if (jsonFile.type === 'application/pdf') {
        this.selectedJsonFile = jsonFile;
        this.archivoInvalido = false;
        this.archivoValido = true;
      } else {
        this.archivoInvalido = true;
        this.archivoValido = false;
        console.error(`El archivo ${jsonFile.name} no es un archivo PDF válido.`);
      }
    }
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

  CompletarPerfilUsuario(): void {

    this.completarUsuario = this.FormularioOferta.value as CompletarPerfilUsuario;
    this.completarInformacionSerice.completarUsuario(this.completarUsuario, this.selectedJsonFile).subscribe({
      next: (created: CompletarPerfilUsuario) => {
        console.log("creado " + created);
        if(this.usuario.tipo == "Usuario"){
          this.router.navigate(['/Proyecto2/Modulo/Usuario']);
          
        }

      },
      error: (error: any) => {
        console.log("error");
      }
    });


  }

  submit(): void {
    
      this.completarEmpresa = this.FormularioEmpresa.value as Empresa;

    
      this.completarInformacionSerice.completarEmpresa(this.completarEmpresa).subscribe({
        next: (created: Empresa) => {
          if(this.usuario.tipo == "Empleador"){
            this.router.navigate(['/Proyecto2/Modulo/Empleador']);
            
          }
        
          },
        error: (error: any) => {
          console.log("error");
        }
      });
    
  }


  obtenerNombreCategoria(codigoCategoria: string | number): string {
    const categoriaEncontrada = this.categorias.find(categoria => categoria.codigo.toString() === String(codigoCategoria));
    return categoriaEncontrada ? categoriaEncontrada.nombre : 'Desconocida';
  }









}