/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.Categoria;
import Datos.JsonUtil;
import DatosBD.GestionCategoriaBD;
import Service.CategoriaService;
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
@WebServlet(name = "GestionCategoriasController", urlPatterns = {"/v1/GestionCategoria"})

public class CategoriasServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();
    private final Gson gson = new Gson();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        String codigo = request.getParameter("codigo");
        if (codigo == null) {
            GestionCategoriaBD categoria = new GestionCategoriaBD();

            // CategoriaService categoriaService = new CategoriaService( categoria);
            List<Categoria> categorias = categoria.getCategorias();

            String json = new Gson().toJson(categorias);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Envía el JSON como respuesta
            response.getWriter().write(json);
        } else {

            try {
                CategoriaService categoriaService = new CategoriaService();
                Categoria cat = categoriaService.getCategoria(codigo);

                String json = new Gson().toJson(cat);
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

        
     
        CategoriaService categoriaService = new CategoriaService();
        String body = jsonUtil.getBody(request);
        categoriaService.ProcesarSolicitud(body, response);
            
       
     
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Entre al servlet metodo put");

        var buffer = new StringBuilder();
        var reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String payload = buffer.toString();
        Categoria categoriaFE = gson.fromJson(payload, Categoria.class);

        System.out.println(categoriaFE.getNombre());
        System.out.println(categoriaFE.getDescripcion());
        System.out.println(categoriaFE.getCodigo());

        // Usuario usuario = loginService.IsLogin(logincito.getPassword(), logincito.getUsuario());
        try {

            CategoriaService categoriaService = new CategoriaService();

            Categoria categoria = new Categoria();

            categoria = categoriaService.actualizarCategoria(categoriaFE);

            String json = new Gson().toJson(categoria);
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
