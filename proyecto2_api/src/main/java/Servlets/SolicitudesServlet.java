/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.Comision;
import Datos.Solicitudes;
import Service.SolicitudesService;
import com.google.gson.Gson;
import exceptions.InvalidDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author MSI
 */
    @WebServlet(name = "SolicitudesController", urlPatterns = {"/v1/Solicitudes"})

public class SolicitudesServlet extends HttpServlet{

        private SolicitudesService solicitudService = new SolicitudesService();

     private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String codigoOferta = request.getParameter("codigoOferta");
        String codigoUsuario = request.getParameter("codigoSuuario");
        
        if(solicitudService.ExisteSolicitud(codigoUsuario, codigoOferta)){
               response.setStatus(HttpServletResponse.SC_OK);
     
        }else{
            
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
             
        }
        
        
    
    }
     
     

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        System.out.println("entramos el Servlet SOlicitudes");
        
          var buffer = new StringBuilder();
        var reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String payload = buffer.toString();
        Solicitudes solicitudFE = gson.fromJson(payload, Solicitudes.class);

        System.out.println("codigo empleo: " + solicitudFE.getCodigoOferta());
        System.out.println("codigo usuario: " + solicitudFE.getCodigoUsuario());
        System.out.println("Mensaje: " + solicitudFE.getMensaje());
        // Usuario usuario = loginService.IsLogin(logincito.getPassword(), logincito.getUsuario());

        //   response.setStatus(HttpServletResponse.SC_OK);
        try {
            
          Solicitudes  solicitud =solicitudService.crearSOlicitud(solicitudFE);
            
             String json = new Gson().toJson(solicitud);
            // Configura la respuesta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Envía el JSON como respuesta
            response.getWriter().write(json);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (InvalidDataException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        
        
    }
    
    
    
}
