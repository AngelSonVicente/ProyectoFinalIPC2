/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

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
import Service.ReportesAdministradorService;
import Service.ReportesEmpleadorService;
import Service.ReportesUsuarioService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ReporteController {
    
    
    ReportesAdministradorService reportesAdmin = new ReportesAdministradorService();
    ReportesEmpleadorService reportesEmpleador = new ReportesEmpleadorService();
    ReportesUsuarioService reportesUsuario = new ReportesUsuarioService();
    
    JsonUtil jsonUtil = new JsonUtil();

    
    public void GetReportes(Reporte reporte, HttpServletResponse response) throws IOException{
    
        //Administrador
        if (reporte.getTipo().equals("admin")) {
            if (reporte.getReporte().equals("1")) {

                List<HistorialComision> reportes = reportesAdmin.Reporte1();

               jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.getReporte().equals("2")) {
                List<EmpleadorReporte> reportes = reportesAdmin.Reporte2();
                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.getReporte().equals("3")) {
                List<EmpleadorReporte> reportes = reportesAdmin.Reporte3Fecha(reporte.getFecha1(), reporte.getFecha2());
                System.out.println("enviando> " +reportes.toString());
                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.getReporte().equals("4")) {
                List<CategoriaReporte> reportes = reportesAdmin.Reporte4();
                jsonUtil.EnviarListaJson(response, reportes);

            }

        }
        if (reporte.getTipo().equals("usuario")) {
            if (reporte.getReporte().equals("1")) {
                List<SolicitudRetirada> reportes = reportesUsuario.Reporte1(reporte.getCodigo(), reporte.getFecha1(), reporte.getFecha2());
                jsonUtil.EnviarListaJson(response, reportes);
            }
            if (reporte.getReporte().equals("2")) {
                List<Solicitudes> reportes = reportesUsuario.Reporte2(reporte.getCodigo());
                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.getReporte().equals("3")) {
                List<Solicitudes> reportes = reportesUsuario.Reporte3(reporte.getCodigo());
                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.getReporte().equals("4")) {
                List<Solicitudes> reportes = reportesUsuario.Reporte4(reporte.getCodigo());
                jsonUtil.EnviarListaJson(response, reportes);

            }

        }
        //Empleador
        if (reporte.getTipo().equals("empleador")) {
            if (reporte.getReporte().equals("1")) {
                List<Oferta> reportes = reportesEmpleador.Reporte1(reporte.getCodigo(), reporte.getFecha1(), reporte.getFecha2());
                jsonUtil.EnviarListaJson(response, reportes);
            }
            if (reporte.getReporte().equals("2")) {
                List<Entrevista> reportes = reportesEmpleador.Reporte2Fecha(reporte.getCodigo(), reporte.getFecha1());

                jsonUtil.EnviarListaJson(response, reportes);

            }
            if (reporte.getReporte().equals("3")) {
                List<HistorialCobros> reportes = reportesEmpleador.Reporte3(reporte.getCodigo());
                jsonUtil.EnviarListaJson(response, reportes);

            }

        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        
    }
    
}
