/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.CategoriaReporte;
import Datos.EmpleadorReporte;
import Datos.Entrevista;
import Datos.HistorialCobros;
import Datos.HistorialComision;
import Datos.JsonUtil;
import Datos.Oferta;
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

        String tipo = request.getParameter("tipo");
        String reporte = request.getParameter("reporte");
        String codigo = request.getParameter("codigo");
        String fecha1 = request.getParameter("fecha1");
        String fecha2 = request.getParameter("fecha2");
        System.out.println("tipo: " + tipo);
        System.out.println("reporte: " + reporte);
        System.out.println("codigousuario: " + codigo);
        System.out.println("fecha1: " + fecha1);
        System.out.println("fecha2: " + fecha2);

        //Administrador
        if (tipo.equals("admin")) {
            if (reporte.equals("1")) {

                List<HistorialComision> reportes = reportesAdmin.Reporte1();

                String json = new Gson().toJson(reportes);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Envía el JSON como respuesta
                response.getWriter().write(json);

            }
            if (reporte.equals("2")) {
                List<EmpleadorReporte> reportes = reportesAdmin.Reporte2();
                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.equals("3")) {
                List<EmpleadorReporte> reportes = reportesAdmin.Reporte3Fecha(fecha1, fecha2);
                System.out.println("enviando> " +reportes.toString());
                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.equals("4")) {
                List<CategoriaReporte> reportes = reportesAdmin.Reporte4();
                jsonUtil.EnviarListaJson(response, reportes);

            }

        }
        if (tipo.equals("usuario")) {
            if (reporte.equals("1")) {
                List<SolicitudRetirada> reportes = reportesUsuario.Reporte1(codigo, fecha1, fecha2);
                jsonUtil.EnviarListaJson(response, reportes);
            }
            if (reporte.equals("2")) {
                List<Solicitudes> reportes = reportesUsuario.Reporte2(codigo);
                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.equals("3")) {
                List<Solicitudes> reportes = reportesUsuario.Reporte3(codigo);
                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.equals("4")) {
                List<Solicitudes> reportes = reportesUsuario.Reporte4(codigo);
                jsonUtil.EnviarListaJson(response, reportes);

            }

        }
        //Empleador
        if (tipo.equals("empleador")) {
            if (reporte.equals("1")) {
                List<Oferta> reportes = reportesEmpleador.Reporte1(codigo, fecha1, fecha2);
                jsonUtil.EnviarListaJson(response, reportes);
            }
            if (reporte.equals("2")) {
                List<Entrevista> reportes = reportesEmpleador.Reporte2Fecha(codigo, fecha1);

                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.equals("3")) {
                List<HistorialCobros> reportes = reportesEmpleador.Reporte3(codigo);
                jsonUtil.EnviarListaJson(response, reportes);

            }

        }

        response.setStatus(HttpServletResponse.SC_OK);

    }

}
