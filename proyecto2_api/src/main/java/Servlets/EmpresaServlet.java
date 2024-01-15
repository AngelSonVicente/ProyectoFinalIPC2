/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Controller.EmpresaController;
import Datos.Empresa;
import Datos.JsonUtil;
import Datos.Oferta;
import Service.EmpresaService;
import com.google.gson.Gson;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
@WebServlet(name = "EmpresaCOntroller", urlPatterns = {"/v1/Empresa"})

public class EmpresaServlet extends HttpServlet {

    private EmpresaService empresaService = new EmpresaService();
    JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigo = request.getParameter("codigo");

        EmpresaController empresaController = new EmpresaController();
        try {
            empresaController.getEmpresa(codigo, response);
        } catch (NotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = jsonUtil.getBody(request);
        EmpresaController empresaController = new EmpresaController();

        try {
            empresaController.ActualizarDatosEmpresa(body, response);
        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

}
