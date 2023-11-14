/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.HoraDisponible;
import Datos.JsonUtil;
import Service.HorasDisponiblesService;
import exceptions.InvalidDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
@WebServlet(name = "HorasDisponiblesController", urlPatterns = {"/v1/HoraDisponible"})

public class HoraDisponibleServlet extends HttpServlet{

    
    HorasDisponiblesService Horas = new HorasDisponiblesService();
    JsonUtil jsonUtil = new JsonUtil();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        System.out.println("Entrando al servlet HOra disponible");
        
        String fecha= request.getParameter("fecha");
        String ubicacion= request.getParameter("ubicacion");
        String codigoOferta= request.getParameter("codigoOferta");
        
        System.out.println("fecha: " + fecha);
        System.out.println("ubicacion: " + ubicacion);
        System.out.println("codigo Oferta: " + codigoOferta);
        
        try {
          
            List<HoraDisponible> horasDisponibles = Horas.getHorasDisponibles(fecha, ubicacion, codigoOferta);
            jsonUtil.EnviarListaJson(response, horasDisponibles);
            System.out.println("se envio lista de horad");
            
         
            
        } catch (InvalidDataException ex) {
            System.out.println("No se envio nada");
        
                 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
   
        }
        
        
        
    
    }
    
    
    
    
}
