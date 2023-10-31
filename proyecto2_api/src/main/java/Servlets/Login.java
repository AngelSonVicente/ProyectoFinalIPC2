/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

//import com.fasterxml.jackson.databind.ObjectMapper;
import Datos.Usuario;
import Datos.login;
import Service.LoginService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author MSI
 */
@WebServlet(name = "LoginController", urlPatterns = {"/v1/login"})

public class Login extends HttpServlet {

    login login = new login();
    private LoginService loginService = new LoginService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    
//         if (request.getParameter("carnet") == null) {
//            this.loadList(request, response);
//        } else {
//            this.loadStudent(request, response, request.getParameter("carnet"));
//        }
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
        Usuario logincito = gson.fromJson(payload, Usuario.class);
        System.out.println(logincito.getUsuario());
        System.out.println(logincito.getPassword());
        
        Usuario usuario = loginService.IsLogin(logincito.getPassword(), logincito.getUsuario());

        System.out.println("usuario_ "+ usuario);
        if ( usuario!= null) {

            response.setStatus(HttpServletResponse.SC_OK);
        String json = new Gson().toJson(usuario);

        // Configura la respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Envía el JSON como respuesta
        response.getWriter().write(json);

        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        }

//            response.setStatus(HttpServletResponse.SC_OK);
        
//        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
//        try {
//            DBEstudiante dbEstudiante = new DBEstudiante();
//            StudentService estudianteServicio = new StudentService(dbEstudiante);
//            estudiante = estudianteServicio.createStudent(estudiante);
//            objectMapper.writeValue(response.getWriter(), estudiante);
//            response.setStatus(HttpServletResponse.SC_CREATED);
//        } catch (InvalidDataException e) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        }
    }
//    
//        private void loadStudent(HttpServletRequest request, HttpServletResponse response, String carnet) throws ServletException, IOException {
//        UsuarioBD dbEstudiante = new UsuarioBD();
//       
//        //StudentService estudianteServicio = new StudentService(dbEstudiante);
//        
//        try {
//            Usuario usuario = dbEstudiante.IsLogin();
//            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//            objectMapper.writeValue(response.getWriter(), estudiante);
//        } catch (NotFoundException ex) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }

}
