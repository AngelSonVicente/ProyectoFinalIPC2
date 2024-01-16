/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.Comision;
import Datos.JsonUtil;
import Datos.Solicitudes;
import Service.SolicitudesService;
import com.google.gson.Gson;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
@WebServlet(name = "SolicitudesController", urlPatterns = {"/v1/Solicitudes"})

public class SolicitudesServlet extends HttpServlet {

    private SolicitudesService solicitudService = new SolicitudesService();

    JsonUtil jsonUtil = new JsonUtil();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigoOferta = request.getParameter("codigoOferta");
        String codigoUsuario = request.getParameter("codigoSuuario");
        String Usuario = request.getParameter("usuario");

        try {
            solicitudService.GetSolicitudes(codigoOferta, codigoUsuario, response);
        } catch (InvalidDataException ex) {
       
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("entramos el Servlet SOlicitudes");

        String body= jsonUtil.getBody(request);
        
        try {
            solicitudService.CrearSolicitud(body, response);
        } catch (InvalidDataException | NotFoundException | SQLException ex) {
              response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigo = request.getParameter("codigo");

        try {
            if (solicitudService.borrarSolicitud(codigo)) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (InvalidDataException ex) {
            Logger.getLogger(SolicitudesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFoundException ex) {
            Logger.getLogger(SolicitudesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        System.out.println("Entrando al put de solicitudes");
        String codigoSolicitud=request.getParameter("codigoSoli");
        String codigoOferta=request.getParameter("codigoOferta");
        String Estado=request.getParameter("estado");
        
        Solicitudes soli = new Solicitudes(codigoSolicitud, codigoOferta, null, request.getParameter("codigoUsuario"), null, null, Estado);
       
        try {
            solicitudService.ActualizarSolicitud(soli,response);
        } catch (InvalidDataException | NotFoundException ex) {
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       
        }
      
       
     
    }
    
    

}
