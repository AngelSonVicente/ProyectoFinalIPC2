package Servlets;

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

        System.out.println("Entramos al servlet");
        System.out.println("COdigo: " + codigo);
        System.out.println("COdigo Empresa: " + codigoEmpresa);

        if (codigo == null) {
            if (codigoEmpresa != null) {
                if (estado != null) {
                    List<Oferta> ofertas = ofertaService.getOfertasEmpresaEstados(codigoEmpresa, estado);

                    String json = new Gson().toJson(ofertas);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    // Envía el JSON como respuesta
                    response.getWriter().write(json);
                } else {
                    List<Oferta> ofertas = ofertaService.getOfertasEmpresa(codigoEmpresa);

                    String json = new Gson().toJson(ofertas);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    // Envía el JSON como respuesta
                    response.getWriter().write(json);

                }

            } else {

                List<Oferta> ofertas = ofertaService.getOfertas();

                String json = new Gson().toJson(ofertas);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Envía el JSON como respuesta
                response.getWriter().write(json);
            }

        } else {

            try {
                Oferta ofert = ofertaService.getOferta(codigo);

                String json = new Gson().toJson(ofert);
                // Configura la respuesta
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Envía el JSON como respuesta
                response.getWriter().write(json);

                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NotFoundException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var ofertFE = jsonUtil.JsonAObjeto(request, Oferta.class);

        Oferta oferta = (Oferta) ofertFE;
        oferta.setEstado("Activo");

        try {

            Oferta ofertaaa = ofertaService.crearOferta(oferta);

            jsonUtil.EnviarJson(response, ofertaaa);

//             String json = new Gson().toJson(ofertaaa);
//            // Configura la respuesta
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//
//            // Envía el JSON como respuesta
//            response.getWriter().write(json);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (InvalidDataException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Entre al servlet metodo put");

        String body = jsonUtil.getBody(request);
        Oferta ofertaFE = gson.fromJson(body, Oferta.class);

        System.out.println("Objeto recibido: " + ofertaFE.toString());

        try {

            ofertaService.ActualizarOferta(body, response);
        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("body: "+ jsonUtil.getBody(request));
        
       OfertaEliminada oferta = new OfertaEliminada(null, request.getParameter("codigoOferta"), null, request.getParameter("motivo"), null);

        OfertaEliminada ofertaEliminada = (OfertaEliminada) jsonUtil.JsonAObjeto(request, OfertaEliminada.class);
     
        
        try {
            
           
            ofertaService.EliminarOferta(oferta, response);
        } catch (NotFoundException | InvalidDataException ex) {
        
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        
        }
        

    }

}
