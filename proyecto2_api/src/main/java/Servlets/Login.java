/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

//import com.fasterxml.jackson.databind.ObjectMapper;
import Datos.JsonUtil;
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
    JsonUtil  jsonUtil= new  JsonUtil();

    login login = new login();
    private LoginService loginService = new LoginService();
    private final Gson gson = new Gson();



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Entre al servlet Login");
        
        String body = jsonUtil.getBody(request);
        loginService.procesarSolicitud(body, response);


    }


}
