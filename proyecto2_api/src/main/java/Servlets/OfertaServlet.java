package Servlets;

import Datos.Categoria;
import Datos.Oferta;
import DatosBD.GestionCategoriaBD;
import Service.CategoriaService;
import Service.OfertaService;
import com.google.gson.Gson;
import exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MSI
 */

@WebServlet(name = "OfertasController", urlPatterns = {"/v1/Ofertas"})

public class OfertaServlet extends HttpServlet {


    private OfertaService ofertaService = new OfertaService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigo = request.getParameter("codigo");
        String codigoEmpresa = request.getParameter("empresa");
        
        System.out.println("Entramos al servlet");
        System.out.println("COdigo: " +codigo);
        System.out.println("COdigo Empresa: " +codigoEmpresa);
        
        if (codigo == null) { 
            if(codigoEmpresa!=null){
                
           List<Oferta> ofertas = ofertaService.getOfertasEmpresa(codigoEmpresa);

            String json = new Gson().toJson(ofertas);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Envía el JSON como respuesta
            response.getWriter().write(json);
                
            }else{
            
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

}
