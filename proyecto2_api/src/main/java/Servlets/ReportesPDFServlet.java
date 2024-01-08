/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.Reporte;
import Service.ReportesPDFService;
import exceptions.InvalidDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */

@WebServlet(name = "ReportesPDFController", urlPatterns = {"/v1/ReportesPDF"})
public class ReportesPDFServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Reporte reporte = new Reporte(request.getParameter("tipo"), request.getParameter("reporte"), request.getParameter("codigo"), request.getParameter("fecha1"),request.getParameter("fecha2") );

        ReportesPDFService reporteService = new ReportesPDFService();
        
        try {
            reporteService.getReporte(reporte, response);
        } catch (InvalidDataException ex) {
            Logger.getLogger(ReportesPDFServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

    }

}
