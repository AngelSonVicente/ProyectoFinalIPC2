
package Servlets;

import Datos.Entrevista;
import Datos.Solicitudes;
import Service.EntrevistaService;
import com.google.gson.Gson;
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
  @WebServlet(name = "EntrevistaController", urlPatterns = {"/v1/Entrevistas"})

public class EntrevistasServlet extends HttpServlet{
    
    EntrevistaService entrevistaService = new EntrevistaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        String codigoUsuario = request.getParameter("codigoUsuario");
        
        
        if(codigoUsuario!=null){
            
               List<Entrevista> entrevistas = entrevistaService.getEntrevistasUsuario(codigoUsuario);

            String json = new Gson().toJson(entrevistas);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Envía el JSON como respuesta
            response.getWriter().write(json);
            
            
        }
        
        
        
    
    }
    
    
    
}
