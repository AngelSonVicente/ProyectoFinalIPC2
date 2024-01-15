/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Controller.UsuarioController;
import Datos.DashBoard;
import Datos.JsonUtil;
import Datos.Usuario;
import Service.UsuarioService;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/v1/Usuario"})
public class UsuarioServlet extends HttpServlet {

    JsonUtil jsonUtil = new JsonUtil<Usuario>();
    JsonUtil<DashBoard> jsonUtil2 = new JsonUtil<DashBoard>();
    UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigo = request.getParameter("codigoUsuario");
        String dash = request.getParameter("dash");

        System.out.println("codigo usuario: " + codigo);

        UsuarioController usuarioController = new UsuarioController();
        try {
            usuarioController.GetUsuario(codigo, dash, response);
        } catch (NotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
 

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("----------------Entramos al servlet Crear Usuario-----------------------");
    
        String bosy = jsonUtil.getBody(request);
        
        UsuarioController usuarioController = new UsuarioController();
        try {
            usuarioController.CrearUsurio(bosy, response);
        } catch (InvalidDataException ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }
        
  
            
         
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body=jsonUtil.getBody(request);
        
        try {
            usuarioService.ActualizarUsuario(body, response);
        } catch (InvalidDataException |NotFoundException ex) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        } 
        
    }
    
}
