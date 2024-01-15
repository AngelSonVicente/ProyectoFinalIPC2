/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.JsonUtil;
import Service.RecuperarCuentaService;
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
@WebServlet(name = "EnviarCorreoRecuperacionController", urlPatterns = {"/v1/EnviarCorreoRecuperacion"})

public class EnviarCorreoRecuperacionServlet extends HttpServlet {

    JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = jsonUtil.getBody(request);

        RecuperarCuentaService recuperarCuenta = new RecuperarCuentaService();
        try {
            recuperarCuenta.procesarSolicitud(body,response);

        } catch (InvalidDataException ex) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

        System.out.println("body: " + body);
    
    }

}
