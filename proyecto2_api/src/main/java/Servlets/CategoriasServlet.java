/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.Categoria;
import DatosBD.GestionCategoriaBD;
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

@WebServlet(name = "GestionCategoriasController", urlPatterns = {"/v1/GestionCategoria"})

public class CategoriasServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        
         GestionCategoriaBD categoria = new GestionCategoriaBD();
           
       // CategoriaService categoriaService = new CategoriaService( categoria);
        
        List<Categoria> categorias = categoria.getCategorias();  
        
          String json = new Gson().toJson(categorias);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Envía el JSON como respuesta
        response.getWriter().write(json);
        
        
    }

  
    
    
    
}
