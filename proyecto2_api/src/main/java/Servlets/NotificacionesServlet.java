/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.JsonUtil;
import Service.NotificacionesService;
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
@WebServlet(name = "NotificacionesController", urlPatterns = {"/v1/Notificaciones"})

public class NotificacionesServlet extends HttpServlet {
    JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String CodigoUsuarioDestino = request.getParameter("codigoUsuarioDestino");

        System.out.println("Servlet notificaciones GET");

        NotificacionesService notificacionesService = new NotificacionesService();

        try {
            notificacionesService.getNotificaciones(CodigoUsuarioDestino, response);
        } catch (InvalidDataException ex) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        System.out.println("Servlet notificaciones PUT");

        
        String codigoUsuario = jsonUtil.getBody(request);
        
        System.out.println("codigo Usuario: "+ codigoUsuario);
              NotificacionesService notificacionesService = new NotificacionesService();

        try {
            notificacionesService.MarcarComoNotificacionesComoLeido(codigoUsuario, response);
        } catch (InvalidDataException ex) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
        
    }
    
    
    

}
