/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Datos.Empresa;
import Datos.Oferta;
import DatosBD.ConexionBD;
import Service.EmpresaService;
import Service.OfertaService;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
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
public class FacturaController {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    public void GenerarFactura(String codigoOferta, String CodigoEmpresa, HttpServletResponse response) throws InvalidDataException, NotFoundException {

        if (codigoOferta == null || CodigoEmpresa == null
                || codigoOferta.isEmpty() || CodigoEmpresa.isEmpty()) {

            throw new InvalidDataException("Los codigos no existen");

        }
        EmpresaService empresaService = new EmpresaService();

        Empresa empresa = empresaService.getEmpresa(CodigoEmpresa);

        String ultimosDigitostarjeta = "";
        if (empresa.getNumeroTarjeta().length() > 4) {

            ultimosDigitostarjeta = empresa.getNumeroTarjeta().substring(empresa.getNumeroTarjeta().length() - 4, empresa.getNumeroTarjeta().length());

        } else {
            ultimosDigitostarjeta = empresa.getNumeroTarjeta();
        }

        OfertaService ofertaService = new OfertaService();

        Oferta oferta = ofertaService.getOferta(codigoOferta);

        Map parametros = new HashMap();
        parametros.put("numeroTarjeta",  ultimosDigitostarjeta );
        parametros.put("titularTarjeta",  empresa.getTitularTarjeta() );
        parametros.put("nombreOferta",  oferta.getNombre() );
        parametros.put("nombreEmpresa",  oferta.getNombreEmpresa() );

        
        generarReporte( oferta.getNombre(),parametros, response);
        
        
    }

    String PATH = "C:/IPC2/ProyectoFinalIPC2/proyecto2_api/src/main/java/Jasper/";

    private void generarReporte(String nombre, Map parametros, HttpServletResponse response) {

        try (InputStream inputStream = new FileInputStream(PATH + "Factura.jasper")) {

            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "attachment; filename=" + nombre +" Factura.pdf");
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
