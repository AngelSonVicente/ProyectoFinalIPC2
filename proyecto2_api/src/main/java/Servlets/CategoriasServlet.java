/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Controller.CategoriaController;
import Datos.Categoria;
import Datos.JsonUtil;
import DatosBD.GestionCategoriaBD;
import Service.CategoriaService;
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
@WebServlet(name = "GestionCategoriasController", urlPatterns = {"/v1/GestionCategoria"})

public class CategoriasServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigo = request.getParameter("codigo");

        CategoriaController categoriaController = new CategoriaController();

        try {
            categoriaController.getCategorias(codigo, response);
        } catch (NotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoriaController categoriaController = new CategoriaController();

        String body = jsonUtil.getBody(request);
        try {
            categoriaController.CrearSolicitud(body, response);

        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Entre al servlet metodo put");

        String body = jsonUtil.getBody(request);
        CategoriaController categoriaController = new CategoriaController();

        try {
            categoriaController.ActualizarCategoria(body, response);
        } catch (InvalidDataException ex) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

}
