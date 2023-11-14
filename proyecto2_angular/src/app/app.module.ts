import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';

import { HttpClientModule } from '@angular/common/http';
import { ModuloAdminComponent } from './ModuloAdministrador/modulo-admin/modulo-admin.component';
import { DashboardComponent } from './ModuloAdministrador/dashboard/dashboard.component';
import { GestionCategoriaComponent } from './ModuloAdministrador/gestion-categoria/gestion-categoria.component';
import { CrearCategoriaComponent } from './ModuloAdministrador/crear-categoria/crear-categoria.component';
import { EditarCategoriaComponent } from './ModuloAdministrador/editar-categoria/editar-categoria.component';
import { CambiarComisionComponent } from './ModuloAdministrador/cambiar-comision/cambiar-comision.component';
import { ReportesAdminComponent } from './ModuloAdministrador/reportes-admin/reportes-admin.component';
import { ModuloUsuariooComponent } from './ModuloUsuario/modulo-usuarioo/modulo-usuarioo.component';
import { BuscarOfertasEmpleoComponent } from './ModuloUsuario/BuscarEmpleo/buscar-ofertas-empleo/buscar-ofertas-empleo.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DetallesOfertasComponent } from './ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';
import { PerfilEmpleadorComponent } from './ModuloUsuario/BuscarEmpleo/perfil-empleador/perfil-empleador.component';
import { PostulacionDiaglogComponent } from './ModuloUsuario/BuscarEmpleo/postulacion-diaglog/postulacion-diaglog.component';
import { PostulacionesRealizadasComponent } from './ModuloUsuario/PostulacionesRealizadas/postulaciones-realizadas/postulaciones-realizadas.component';
import { EntrevistasPendientesComponent } from './ModuloUsuario/EntrevistasPendientes/entrevistas-pendientes/entrevistas-pendientes.component';
import { ModuloEmpleadorComponent } from './ModuloEmpleador/modulo-empleador/modulo-empleador.component';
import { GestionOfertasComponent } from './ModuloEmpleador/GestionOfertas/gestion-ofertas/gestion-ofertas.component';
import { EditarOfertaComponent } from './ModuloEmpleador/GestionOfertas/editar-oferta/editar-oferta.component';
import { CrearOfertaComponent } from './ModuloEmpleador/GestionOfertas/crear-oferta/crear-oferta.component';
import { EliminarOfertaComponent } from './ModuloEmpleador/GestionOfertas/eliminar-oferta/eliminar-oferta.component';
import { RevisionPostulacionesComponent } from './ModuloEmpleador/RevisionPostulaciones/revision-postulaciones/revision-postulaciones.component';
import { PostulacionesOfertaComponent } from './ModuloEmpleador/RevisionPostulaciones/postulaciones-oferta/postulaciones-oferta.component';
import { InformacionUsuarioComponent } from './ModuloEmpleador/RevisionPostulaciones/informacion-usuario/informacion-usuario.component';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';

import { PdfViewerModule } from 'ng2-pdf-viewer';
import { RevisionEntrevistasComponent } from './ModuloEmpleador/RevisionEntrevistas/revision-entrevistas/revision-entrevistas.component';
import { EntrevistasOfertasComponent } from './ModuloEmpleador/RevisionEntrevistas/entrevistas-ofertas/entrevistas-ofertas.component';
import { FinalizarEntrevistaComponent } from './ModuloEmpleador/RevisionEntrevistas/finalizar-entrevista/finalizar-entrevista.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    LoginComponent,
    ModuloAdminComponent,
    DashboardComponent,
    GestionCategoriaComponent,
    CrearCategoriaComponent,
    EditarCategoriaComponent,
    CambiarComisionComponent,
    ReportesAdminComponent,
    ModuloUsuariooComponent,
    BuscarOfertasEmpleoComponent,
    DetallesOfertasComponent,
    PerfilEmpleadorComponent,
    
    PostulacionDiaglogComponent,
         PostulacionesRealizadasComponent,
         EntrevistasPendientesComponent,
         ModuloEmpleadorComponent,
         GestionOfertasComponent,
         EditarOfertaComponent,
         CrearOfertaComponent,
         EliminarOfertaComponent,
         RevisionPostulacionesComponent,
         PostulacionesOfertaComponent,
         InformacionUsuarioComponent,
         RevisionEntrevistasComponent,
         EntrevistasOfertasComponent,
         FinalizarEntrevistaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ModalModule.forRoot(),
    NgxExtendedPdfViewerModule,
    PdfViewerModule,
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
