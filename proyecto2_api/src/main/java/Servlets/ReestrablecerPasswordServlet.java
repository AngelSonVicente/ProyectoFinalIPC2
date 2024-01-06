/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.JsonUtil;
import Datos.RecuperarCuenta;
import Service.ReestablecerPasswordService;
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
@WebServlet(name = "ReestablecerPasswordController", urlPatterns = {"/v1/ReestablecerPasword"})

public class ReestrablecerPasswordServlet extends HttpServlet {

    JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String token = request.getParameter("token");

        ReestablecerPasswordService tokenValido = new ReestablecerPasswordService();
        try {
            tokenValido.getInformaiconToken(token, response);

        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ReestablecerPasswordService reestablecerPasswordService = new ReestablecerPasswordService();

        String body = jsonUtil.getBody(request);

        try {
            reestablecerPasswordService.CambiarPassword(body, response);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

}
