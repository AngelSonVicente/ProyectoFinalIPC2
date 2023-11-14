package Servlets;

import Datos.Entrevista;
import Datos.JsonUtil;
import Datos.Solicitudes;
import Service.EntrevistaService;
import Service.SolicitudesService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exceptions.InvalidDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
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

        if (codigoUsuario != null) {

            List<Entrevista> entrevistas = entrevistaService.getEntrevistasUsuario(codigoUsuario);

            String json = new Gson().toJson(entrevistas);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Envía el JSON como respuesta
            response.getWriter().write(json);

        }
        if (codigoOferta != null) {

            List<Entrevista> entrevistas = entrevistaService.getEntrevistasOferta(codigoOferta);
            jsonUtil.EnviarListaJson(response, entrevistas);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Entrando al post de Entrevistas");
        var entrevistaFE = jsonUtil.JsonAObjeto(request, Entrevista.class);
        Entrevista entrevista = (Entrevista) entrevistaFE;

        try {

            Entrevista entrevistaCrada = entrevistaService.crearEntrevista(entrevista);
            jsonUtil.EnviarJson(response, entrevistaCrada);

            response.setStatus(HttpServletResponse.SC_OK);

        } catch (InvalidDataException ex) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entrando al servlet metodo put de entrevista");
        var entrevistaFE = jsonUtil.JsonAObjeto(request, Entrevista.class);
        Entrevista entrevista = (Entrevista) entrevistaFE;
        
        System.out.println("codigo: " + entrevista.getCodigo());
        System.out.println("nota: " + entrevista.getNota());
        try {
            Entrevista entrevistaFinalizada = entrevistaService.finalizarEntrevista(entrevista);
               jsonUtil.EnviarJson(response, entrevistaFinalizada);

            response.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            System.out.println("--------------------------------");
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            
        }
        
        
        
        

    }

}
