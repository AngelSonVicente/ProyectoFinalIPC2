/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Controller.OfertaController;
import Datos.JsonUtil;
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
@WebServlet(name = "FiltrarOfertasController", urlPatterns = {"/v1/FiltrarOfertas"})

public class FiltrarOfertasServlet extends HttpServlet{

    JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
    
       String body= jsonUtil.getBody(req);
       
       OfertaController ofertaController= new OfertaController();
        try {
            ofertaController.FiltrarOfertas(body, response);
        
         
        } catch (InvalidDataException ex) {
            Logger.getLogger(FiltrarOfertasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 
    
    
    
    
}
