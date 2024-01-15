/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.Entrevista;
import Datos.FinalizarOferta;
import Datos.JsonUtil;
import Service.FinalizarOfertaService;
import Service.OfertaService;
import com.google.gson.Gson;
import exceptions.InvalidDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
@WebServlet(name = "FinalizacionOfertaController", urlPatterns = {"/v1/TerminarOferta"})
public class FinalizarOfertaServlet extends HttpServlet {

    FinalizarOfertaService finalizarService = new FinalizarOfertaService();
    OfertaService ofertaService = new OfertaService();
    JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = jsonUtil.getBody(request);

        try {
            finalizarService.FinalizarOFerta(body, response);
        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigo = request.getParameter("codigoOferta");
        
        try {
            finalizarService.OfertaFinalizada(codigo, response);
        } catch (InvalidDataException ex) {
    
                 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
       

    }

}
