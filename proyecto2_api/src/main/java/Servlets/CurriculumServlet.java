/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.JsonUtil;
import Service.CurriculumService;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
@MultipartConfig(location = "C:/Users/MSI/Documents/apache-tomcat-10.1.7/temp")
@WebServlet(name = "CurriculumController", urlPatterns = {"/v1/Curriculum"})

public class CurriculumServlet extends HttpServlet {

    private static final String CV_PART_NAME = "CV";
    private JsonUtil jsonUtil = new JsonUtil();
      
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigoUsuario = request.getParameter("codigoUsuario");
         Part pdfPart = request.getPart(CV_PART_NAME);
         
         codigoUsuario = codigoUsuario.replace("\"", "");
         
         System.out.println("codigo usuario. "+ codigoUsuario);
         System.out.println("CVname. "+ pdfPart.getSubmittedFileName());
         
         CurriculumService curriculumCervice = new CurriculumService();
         
        try {
            curriculumCervice.actualizarCurriculum(codigoUsuario, pdfPart, response);
        } catch (InvalidDataException  | NotFoundException ex) {
        
                 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }
         
         
         
    }

}
