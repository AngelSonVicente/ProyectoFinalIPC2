package Servlets;

import Controller.FacturaController;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "FacturaPDFController", urlPatterns = {"/v1/FacturaPDF"})

public class FacturaServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String codigoOferta = request.getParameter("codigoOferta");
    String codigoEmpresa = request.getParameter("codigoEmpresa");
    FacturaController facturaController = new FacturaController();
        try {
            facturaController.GenerarFactura(codigoOferta, codigoEmpresa, response);
        } catch (InvalidDataException | NotFoundException ex) {
        
            
        }
    
    
    
    }
    
    
    
}
