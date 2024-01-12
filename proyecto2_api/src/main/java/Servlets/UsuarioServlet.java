/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Datos.DashBoard;
import Datos.JsonUtil;
import Datos.Usuario;
import Service.UsuarioService;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/v1/Usuario"})
public class UsuarioServlet extends HttpServlet {

    JsonUtil jsonUtil = new JsonUtil<Usuario>();
    JsonUtil<DashBoard> jsonUtil2 = new JsonUtil<DashBoard>();
    UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigo = request.getParameter("codigoUsuario");
        String dash = request.getParameter("dash");

        System.out.println("codigo usuario: " + codigo);
        
        if(dash!=null){
            
            DashBoard dashito = usuarioService.getDasg();
             jsonUtil2.EnviarJson(response, dashito);

        
        }
        else{
               try {
            Usuario usuario = usuarioService.getUsuarioID(codigo);
            jsonUtil.EnviarJson(response, usuario);

        } catch (NotFoundException ex) {
            System.out.println(ex);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
        }
 

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("----------------Entramos al servlet Crear Usuario-----------------------");
        var UsuarioFE = jsonUtil.JsonAObjeto(request, Usuario.class);
        Usuario user = (Usuario) UsuarioFE;
        System.out.println("usuario"+user.getUsuario());
        System.out.println("nombre"+user.getNombre());
        System.out.println("contraseña"+user.getPassword());
        
        Usuario usuarioCreado;
        try {
            usuarioCreado = usuarioService.CrearUsuario(user);
      jsonUtil.EnviarJson(response, usuarioCreado);
        
        } catch (InvalidDataException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
            
         
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body=jsonUtil.getBody(request);
        
        try {
            usuarioService.ActualizarUsuario(body, response);
        } catch (InvalidDataException |NotFoundException ex) {
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        } 
        
    }
    
}
