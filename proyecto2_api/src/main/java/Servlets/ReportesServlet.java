/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Controller.ReporteController;
import Datos.CategoriaReporte;
import Datos.EmpleadorReporte;
import Datos.Entrevista;
import Datos.HistorialCobros;
import Datos.HistorialComision;
import Datos.JsonUtil;
import Datos.Oferta;
import Datos.Reporte;
import Datos.SolicitudRetirada;
import Datos.Solicitudes;
import DatosBD.ReportesEmpleador;
import Service.ReportesAdministradorService;
import Service.ReportesEmpleadorService;
import Service.ReportesUsuarioService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MSI
 */
@WebServlet(name = "ReportesController", urlPatterns = {"/v1/Reportes"})

public class ReportesServlet extends HttpServlet {

    ReportesAdministradorService reportesAdmin = new ReportesAdministradorService();
    ReportesEmpleadorService reportesEmpleador = new ReportesEmpleadorService();
    ReportesUsuarioService reportesUsuario = new ReportesUsuarioService();
    
    JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      
        
        

           Reporte reporte = new Reporte(request.getParameter("tipo"), request.getParameter("reporte"), request.getParameter("codigo"), request.getParameter("fecha1"),request.getParameter("fecha2") );

           ReporteController reporteController = new ReporteController();
           reporteController.GetReportes(reporte, response);
        

    }

}
