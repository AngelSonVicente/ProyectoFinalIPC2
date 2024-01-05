/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import com.google.gson.Gson;

import Datos.CompletarPerfilUsuario;
import Datos.JsonUtil;
import Service.CompletarPerfilService;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;

/**
 *
 * @author MSI
 */
@MultipartConfig(location = "C:/Users/MSI/Documents/apache-tomcat-10.1.7/temp")
@WebServlet(name = "CompletarInformacion", urlPatterns = {"/v1/CompletarInformacion"})
public class CompletarPerfilServlet extends HttpServlet {

    private static final String PATH = "C:/Users/MSI/Documents";
    private static final String CV_PART_NAME = "CV";

    private JsonUtil jsonUtil = new JsonUtil();
    private CompletarPerfilService completarPerfilService = new CompletarPerfilService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part pdfPart = request.getPart(CV_PART_NAME);
        //System.out.println("Nombre del archivo PDF: " + pdfPart.getSubmittedFileName());

        String body = (request.getParameter("CompletarPerfi"));

        try {
            completarPerfilService.CompletarPerfil(body, pdfPart, response);

        } catch (Exception e) {

        }
    
    }

}
