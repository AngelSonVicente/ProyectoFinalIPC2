package Servlets;

import Controller.EntrevistaController;
import Datos.Entrevista;
import Datos.JsonUtil;
import Datos.Solicitudes;
import Service.EntrevistaService;
import Service.SolicitudesService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
@WebServlet(name = "EntrevistaController", urlPatterns = {"/v1/Entrevistas"})

public class EntrevistasServlet extends HttpServlet {

    EntrevistaService entrevistaService = new EntrevistaService();
    SolicitudesService solicitudService = new SolicitudesService();
    JsonUtil jsonUtil = new JsonUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigoUsuario = request.getParameter("codigoUsuario");
        String codigoOferta = request.getParameter("codigoOferta");

        EntrevistaController entrevistaController = new EntrevistaController();
        try {
            entrevistaController.getEntrevistas(codigoUsuario, codigoOferta, response);
        } catch (InvalidDataException ex) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = jsonUtil.getBody(request);

        EntrevistaController entrevistaController = new EntrevistaController();

        try {
            entrevistaController.CrearEntrevista(body, response);

        } catch (InvalidDataException | NotFoundException | SQLException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entrando al servlet metodo put de entrevista");

        String body = jsonUtil.getBody(request);
        EntrevistaController entrevistaController = new EntrevistaController();

        try {
            entrevistaController.ActualizarEntrevista(body, response);
        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

}
