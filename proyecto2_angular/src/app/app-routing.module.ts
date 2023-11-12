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
  import { DetallesOfertasComponent } from './ModuloUsuario/BuscarEmpleo/detalles-ofertas/detalles-ofertas.component';
  import { PerfilEmpleadorComponent } from './ModuloUsuario/BuscarEmpleo/perfil-empleador/perfil-empleador.component';

import { PostulacionesRealizadasComponent } from './ModuloUsuario/PostulacionesRealizadas/postulaciones-realizadas/postulaciones-realizadas.component';
import { EntrevistasPendientesComponent } from './ModuloUsuario/EntrevistasPendientes/entrevistas-pendientes/entrevistas-pendientes.component';  



import { FormsModule } from '@angular/forms';
import { ModuloEmpleadorComponent } from './ModuloEmpleador/modulo-empleador/modulo-empleador.component';
import { GestionOfertasComponent } from './ModuloEmpleador/GestionOfertas/gestion-ofertas/gestion-ofertas.component';
import { EditarOfertaComponent } from './ModuloEmpleador/GestionOfertas/editar-oferta/editar-oferta.component';
import { CrearOfertaComponent } from './ModuloEmpleador/GestionOfertas/crear-oferta/crear-oferta.component';


const rutasAdmin = [
  {
    path: 'DashBoard',
    title: 'Dashboard',
    component: DashboardComponent,
  },
  {
    path: 'GestionCategorias',
    title: 'Gestión de Categorías',
    component: GestionCategoriaComponent,
  },
  {
    path: 'CrearCategoria',
    title: 'Crear Categoría',
    component: CrearCategoriaComponent,
  },
  {
    path: 'EditarCategoria/:codigo',
    title: 'Editar Categoría',
    component: EditarCategoriaComponent,
  },
  {
    path: 'CambiarComision',
    title: 'Cambiar Comisión',
    component: CambiarComisionComponent,
  },
  {
    path: 'Reportes',
    title: 'Reportes',
    component: ReportesAdminComponent,
  },
];


const rutasUsuario = [
  {
    path: 'Ofertas',
    title: 'Ofertas de Empleo',
    component: BuscarOfertasEmpleoComponent
  },
  {
    path: 'Ofertas/:codigoEmpresa',
    title: 'Ofertas de Empleo',
    component: BuscarOfertasEmpleoComponent
  },
  {
    path: 'Postulaciones',
    title: 'Postulaciones Realizadas',
    component: PostulacionesRealizadasComponent
  },
  {
    path: 'Entrevistas',
    title: 'Entrevistas Pendientes',
    component: EntrevistasPendientesComponent
  },
  
  {
    path: 'DetallesOferta/:codigo',
    title: 'Detalles de Oferta',
    component: DetallesOfertasComponent,
  },
  {
    path: 'Empresas/:codigo',
    title: 'Detalles de Oferta',
    component: PerfilEmpleadorComponent,
  },

  
];
const rutasEmpleador = [
  {
    path: 'GestionOfertas',
    title: 'Gestion Ofertas de Empleo',
    component: GestionOfertasComponent
  },
  {
    path: 'EditarOferta/:codigo',
    title: 'Editar Ofertas de Empleo',
    component: EditarOfertaComponent
  },
  {
    path: 'CrearOferta',
    title: 'CrearOferta',
    component: CrearOfertaComponent
  },
  
  
];



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
    path: 'Proyecto2/Administrador',
    title: 'Administrador',
    children: rutasAdmin,
  },
  {
    path: 'Proyecto2/Modulo/Usuario',
    title: "CambiarComision",
    component: ModuloUsuariooComponent 
  },

{
  path:"Proyecto2/Usuario",
  title:"Usuario",
  children: rutasUsuario,

},


{
  path: 'Proyecto2/Modulo/Empleador',
  title: "Modulo Empleador",
  component: ModuloEmpleadorComponent 
},
{
  path:"Proyecto2/Empleador",
  title:"Empleador",
  children: rutasEmpleador,

},



];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
