/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.Categoria;
import Datos.Comision;
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

/**
 *
 * @author MSI
 */
@WebServlet(name = "GestionComisionController", urlPatterns = {"/v1/GestionComision"})

public class ComisionServlet extends HttpServlet {

     private final Gson gson = new Gson();

    private ComisionService comisionService = new ComisionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String list = request.getParameter("list");

            System.out.println("list");
            System.out.println("Entre al servlet get de comision");
        if (list == null) {

            // CategoriaService categoriaService = new CategoriaService( categoria);
            List<Comision> comisiones = comisionService.getHistorialComision();

            String json = new Gson().toJson(comisiones);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Envía el JSON como respuesta
            response.getWriter().write(json);
        } else {

            Comision comi = comisionService.getComision();

            String json = new Gson().toJson(comi);
            // Configura la respuesta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Envía el JSON como respuesta
            response.getWriter().write(json);

          
        }

    
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                System.out.println("Entre al servlet");

        var buffer = new StringBuilder();
        var reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String payload = buffer.toString();
        Comision comisionFE = gson.fromJson(payload, Comision.class);

        System.out.println(comisionFE.getComision());
        // Usuario usuario = loginService.IsLogin(logincito.getPassword(), logincito.getUsuario());

         
        try {
            
          Comision  comi =comisionService.crearComision(comisionFE);
            
             String json = new Gson().toJson(comi);
            // Configura la respuesta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Envía el JSON como respuesta
            response.getWriter().write(json);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (InvalidDataException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        
        

    }
    
    
}
