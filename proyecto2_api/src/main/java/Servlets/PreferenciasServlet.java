/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Service.PreferenciasService;
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
@WebServlet(name = "PreferenciasController", urlPatterns = {"/v1/Preferencias"})

public class PreferenciasServlet extends HttpServlet {

    private PreferenciasService preferenciasService = new PreferenciasService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String codigo = request.getParameter("codigoUsuario");

        try {
            preferenciasService.getPreferencias(codigo, response);
        } catch (InvalidDataException ex) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   

    }
}
