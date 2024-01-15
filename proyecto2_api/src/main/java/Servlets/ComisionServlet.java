/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Controller.ComisionController;
import Datos.Categoria;
import Datos.Comision;
import Datos.JsonUtil;
import DatosBD.GestionCategoriaBD;
import Service.CategoriaService;
import Service.ComisionService;
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
@WebServlet(name = "GestionComisionController", urlPatterns = {"/v1/GestionComision"})

public class ComisionServlet extends HttpServlet {

    JsonUtil jsonUtil = new JsonUtil();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String list = request.getParameter("list");

        System.out.println("list");
        System.out.println("Entre al servlet get de comision");

        ComisionController comisionController = new ComisionController();
        comisionController.getComision(list, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Entre al servlet");
        String body = jsonUtil.getBody(request);

        ComisionController comisionController = new ComisionController();

        try {
            comisionController.CrearComision(body, response);
        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

}
