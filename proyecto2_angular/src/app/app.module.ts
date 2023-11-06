import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


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
    BuscarOfertasEmpleoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
