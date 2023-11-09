/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.Empresa;
import Datos.Oferta;
import Service.EmpresaService;
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
@WebServlet(name = "EmpresaCOntroller", urlPatterns = {"/v1/Empresa"})

public class EmpresaServlet extends HttpServlet {
    private EmpresaService empresaService = new EmpresaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
      String codigo =   request.getParameter("codigo");
        if(codigo!=null){
              try {
         Empresa empresa = empresaService.getEmpresa(codigo);
              
           String json = new Gson().toJson(empresa);
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
