package Servlets;

import Controller.OfertaController;
import Datos.Categoria;
import Datos.JsonUtil;
import Datos.Oferta;
import Datos.OfertaEliminada;
import DatosBD.GestionCategoriaBD;
import Service.CategoriaService;
import Service.OfertaService;
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
@WebServlet(name = "OfertasController", urlPatterns = {"/v1/Ofertas"})

public class OfertaServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();
    private OfertaService ofertaService = new OfertaService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigo = request.getParameter("codigo");
        String estado = request.getParameter("estado");
        String codigoEmpresa = request.getParameter("empresa");

        String codigoUsuario = request.getParameter("codigoUsuario");

        System.out.println("Entramos al servlet");
        System.out.println("COdigo: " + codigo);
        System.out.println("COdigo Empresa: " + codigoEmpresa);
        System.out.println("codigo Usuario = " + codigoUsuario);

        OfertaController ofertaController = new OfertaController();
        try {
            ofertaController.GetOfertas(codigo, estado, codigoEmpresa, codigoUsuario, response);
        } catch (InvalidDataException | NotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = jsonUtil.getBody(request);

        OfertaController ofertaController = new OfertaController();
        try {
            ofertaController.CrearOferta(body, response);
        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OfertaController ofertaController = new OfertaController();

        System.out.println("Entre al servlet metodo put");

        String body = jsonUtil.getBody(request);
        try {

            ofertaController.ActualizarOferta(body, response);
        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OfertaController ofertaController = new OfertaController();

        OfertaEliminada oferta = new OfertaEliminada(null, request.getParameter("codigoOferta"), null, request.getParameter("motivo"), null);

        try {

            ofertaController.EliminarOferta(oferta, response);
        } catch (NotFoundException | InvalidDataException ex) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

}
