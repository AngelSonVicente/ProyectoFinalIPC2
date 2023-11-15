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
import { Reporte1Component } from './ModuloAdministrador/reporte1/reporte1.component';
import { Reporte2Component } from './ModuloAdministrador/reporte2/reporte2.component';
import { Reporte3Component } from './ModuloAdministrador/reporte3/reporte3.component';
import { Reporte4Component } from './ModuloAdministrador/reporte4/reporte4.component';
import { ReportesComponent } from './ModuloUsuario/ReportesUsuario/reportes/reportes.component';
import { Reporte1UComponent } from './ModuloUsuario/ReportesUsuario/reporte1-u/reporte1-u.component';
import { Reporte2UComponent } from './ModuloUsuario/ReportesUsuario/reporte2-u/reporte2-u.component';
import { Reporte3UComponent } from './ModuloUsuario/ReportesUsuario/reporte3-u/reporte3-u.component';
import { Reporte4UComponent } from './ModuloUsuario/ReportesUsuario/reporte4-u/reporte4-u.component';
import { ReportesEmpleadorComponent } from './ModuloEmpleador/ReportesEmpleador/reportes-empleador/reportes-empleador.component';
import { ReporteE1Component } from './ModuloEmpleador/ReportesEmpleador/reporte-e1/reporte-e1.component';
import { ReporteE2Component } from './ModuloEmpleador/ReportesEmpleador/reporte-e2/reporte-e2.component';
import { ReporteE3Component } from './ModuloEmpleador/ReportesEmpleador/reporte-e3/reporte-e3.component';

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
         FinalizarEntrevistaComponent,
         Reporte1Component,
         Reporte2Component,
         Reporte3Component,
         Reporte4Component,
         ReportesComponent,
         Reporte1UComponent,
         Reporte2UComponent,
         Reporte3UComponent,
         Reporte4UComponent,
         ReportesEmpleadorComponent,
         ReporteE1Component,
         ReporteE2Component,
         ReporteE3Component,
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
