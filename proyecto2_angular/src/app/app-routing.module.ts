import { NgModule } from '@angular/core';
import { MenuComponent } from './menu/menu.component';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ModuloAdminComponent } from './ModuloAdministrador/modulo-admin/modulo-admin.component';
  import { DashboardComponent } from './ModuloAdministrador/dashboard/dashboard.component';
  import { GestionCategoriaComponent } from './ModuloAdministrador/gestion-categoria/gestion-categoria.component';
  import { CrearCategoriaComponent } from './ModuloAdministrador/crear-categoria/crear-categoria.component';
  import { EditarCategoriaComponent } from './ModuloAdministrador/editar-categoria/editar-categoria.component';
  import { CambiarComisionComponent } from './ModuloAdministrador/cambiar-comision/cambiar-comision.component';
  import { ReportesAdminComponent } from './ModuloAdministrador/reportes-admin/reportes-admin.component';

import { ModuloUsuariooComponent } from './ModuloUsuario/modulo-usuarioo/modulo-usuarioo.component';
  import { BuscarOfertasEmpleoComponent } from './ModuloUsuario/BuscarEmpleo/buscar-ofertas-empleo/buscar-ofertas-empleo.component';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
  {
    path: "",
    redirectTo: "/Proyecto2/Menu",
    pathMatch: "full"
  }, 
  {
    path: "Proyecto2/Menu",
    title: "Menu",
    component: MenuComponent
  },
  {
    path: 'login/:userRole',
    title: "Login",
    component: LoginComponent 
  },
  {
    path: 'Proyecto2/Modulo/Administrador',
    title: "Modulo Administrador",
    component: ModuloAdminComponent 
  },
  {
    path: 'Proyecto2/Administrador/DashBoard',
    title: "DashBorad",
    component: DashboardComponent 
  },
  {
    path: 'Proyecto2/Administrador/GestionCategorias',
    title: "Gestion de Categorias",
    component: GestionCategoriaComponent 
  },
  {
    path: 'Proyecto2/Administrador/CrearCategoria',
    title: "Crear de Categorias",
    component: CrearCategoriaComponent 
  },
  {
    path: 'Proyecto2/Administrador/EditarCategoria/:codigo',
    title: "EditarCategoria",
    component: EditarCategoriaComponent 
  },
  {
    path: 'Proyecto2/Administrador/CambiarComision',
    title: "CambiarComision",
    component: CambiarComisionComponent 
  },
  {
    path: 'Proyecto2/Administrador/Reportes',
    title: "CambiarComision",
    component: ReportesAdminComponent 
  },
  {
    path: 'Proyecto2/Modulo/Usuario',
    title: "CambiarComision",
    component: ModuloUsuariooComponent 
  },

  { path: 'Proyecto2/Usuario',
        children:[
          { path: 'Ofertas', component: BuscarOfertasEmpleoComponent },
        ] 
      },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
