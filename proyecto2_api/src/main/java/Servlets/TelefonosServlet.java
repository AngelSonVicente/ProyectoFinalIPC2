/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Service.TelefonosService;
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

@WebServlet(name = "TelefonosController", urlPatterns = {"/v1/Telefonos"})

public class TelefonosServlet extends HttpServlet{
    
    TelefonosService telefonosService = new TelefonosService();

       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String codigo = request.getParameter("codigoUsuario");
        
           System.out.println("codigo Usuario: "+ codigo);
           
        try {
            telefonosService.gerTelefonos(codigo, response);
        } catch (InvalidDataException ex) {
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
           
           
        
                
    
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    }

 
    
    
    
    
    }
    
    
    
    
    

