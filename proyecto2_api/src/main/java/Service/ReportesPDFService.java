/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Reporte;
import Datos.TipoUsuario;
import DatosBD.ConexionBD;
import exceptions.InvalidDataException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author MSI
 */
public class ReportesPDFService {

    static Connection conexion = ConexionBD.getInstancia().getConexion();
    private String sinFecha = "sinfecha";
    String PATH = "C:/IPC2/ProyectoFinalIPC2/proyecto2_api/src/main/java/Jasper/";

    public Reporte getReporte(Reporte reporte, HttpServletResponse response) throws InvalidDataException, IOException {

        System.out.println("Body Reporte: " + reporte.toString());

        // Reportes del admin
        if (reporte.getTipo().equals(TipoUsuario.Admin.name())) {

            if (reporte.getReporte().equals("1")) {
                generarReporte("AdminReporte1", null, response);

//                Map<String, Object> parametros = new HashMap<>();
//                parametros.put("nombre parametros", "valor paramtero");            
            }
            if (reporte.getReporte().equals("2")) {
                generarReporte("AdminReporte2", null, response);
            }
            if (reporte.getReporte().equals("3")) {

                if (!reporte.getFecha1().equals(sinFecha) && !reporte.getFecha2().equals(sinFecha)) {
                    //reporte con fecha

                    Map parametros = new HashMap();
                    parametros.put("fechaInicio", "'" + reporte.getFecha1() + "'");
                    parametros.put("fechaFinal", "'" + reporte.getFecha2() + "'");

                    generarReporte("AdminReporte3", parametros, response);

                } else {
                    generarReporte("AdminReporte3SF", null, response);
                }

            }
            if (reporte.getReporte().equals("4")) {
                if (!reporte.getFecha1().equals(sinFecha) && !reporte.getFecha2().equals(sinFecha)) {
                    //reporte con fecha

                    Map parametros = new HashMap();
                    parametros.put("fechaInicio", "'" + reporte.getFecha1() + "'");
                    parametros.put("fechaFinal", "'" + reporte.getFecha2() + "'");

                    generarReporte("AdminReporte4", parametros, response);

                } else {
                    generarReporte("AdminReporte4SF", null, response);
                }

            }

        }

        //-----------------------------Empleador----------------------------------------------------  
        if (reporte.getTipo().equals(TipoUsuario.Empleador.name())) {

            if (reporte.getReporte().equals("1")) {

                if (!reporte.getFecha1().equals(sinFecha) && !reporte.getFecha2().equals(sinFecha)) {
                    //reporte con fecha

                    Map parametros = new HashMap();
                    parametros.put("fechaInicio", "'" + reporte.getFecha1() + "'");
                    parametros.put("fechaFinal", "'" + reporte.getFecha2() + "'");
                    parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                    generarReporte("EmpleadorReporte1", parametros, response);

                } else {
                    Map parametros = new HashMap();
                    parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                    generarReporte("EmpleadorReporte1SF", parametros, response);
                }

            }
            if (reporte.getReporte().equals("2")) {

                if (!reporte.getFecha1().equals(sinFecha)) {
                    //reporte con fecha

                    Map parametros = new HashMap();
                    parametros.put("fecha", "'" + reporte.getFecha1() + "'");
                    parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                    generarReporte("EmpleadorReporte2", parametros, response);

                } else {
                    Map parametros = new HashMap();
                    parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                    generarReporte("EmpleadorReporte2SF", parametros, response);
                }

            }
            if (reporte.getReporte().equals("3")) {

                Map parametros = new HashMap();
                parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                generarReporte("EmpleadorReporte3", parametros, response);

            }

        }
        
        
        //----------------------USUARIO----------------
        
         if (reporte.getTipo().equals(TipoUsuario.Usuario.name())) {

            if (reporte.getReporte().equals("1")) {

                if (!reporte.getFecha1().equals(sinFecha) && !reporte.getFecha2().equals(sinFecha)) {
                    //reporte con fecha

                    Map parametros = new HashMap();
                    parametros.put("fechaInicio", "'" + reporte.getFecha1() + "'");
                    parametros.put("fechaFinal", "'" + reporte.getFecha2() + "'");
                    parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                    generarReporte("UsuarioReporte1", parametros, response);

                } else {
                    Map parametros = new HashMap();
                    parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                    generarReporte("UsuarioReporte1SF", parametros, response);
                }

            }
              if (reporte.getReporte().equals("2")) {
                  
                       Map parametros = new HashMap();
                    parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                    generarReporte("UsuarioReporte2", parametros, response);
               
             
              }
              if (reporte.getReporte().equals("3")) {
                  
                       Map parametros = new HashMap();
                    parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                    generarReporte("UsuarioReporte3", parametros, response);
               
             
              }
              if (reporte.getReporte().equals("4")) {
                  
                       Map parametros = new HashMap();
                    parametros.put("codigoUsuario", "'" + reporte.getCodigo() + "'");

                    generarReporte("UsuarioReporte4", parametros, response);
               
             
              }
            
            
            
            
            
            
            
         }
        
        
        
        
        
        

        return reporte;
    }

    private void generarReporte(String nombreReporte, Map parametros, HttpServletResponse response) {

        try (InputStream inputStream = new FileInputStream(PATH + nombreReporte + ".jasper")) {
            System.out.println("Enviando Reporte: " + nombreReporte);

            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "attachment; filename=" + nombreReporte + ".pdf");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);

            OutputStream out = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, out);

            out.flush();
            out.close();

        } catch (IOException | JRException e) {
            System.out.println("error: " + e);
        }

    }

}
