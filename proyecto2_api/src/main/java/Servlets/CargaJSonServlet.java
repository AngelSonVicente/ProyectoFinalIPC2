package Servlets;

import Service.CargaJsonService;
import exceptions.InvalidDataException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

@WebServlet(name = "JsonController", urlPatterns = {"/v1/CargaJson"})
@MultipartConfig(location = "C:/Users/MSI/Documents/apache-tomcat-10.1.7/temp")
public class CargaJSonServlet extends HttpServlet {

    private static final String PATH = "C:/Users/MSI/Documents";
    private static final String PART_NAME = "datafile";
    private CargaJsonService cargaJsonService = new CargaJsonService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart(PART_NAME);
        String carnet = request.getParameter("carnet");

        try {
            cargaJsonService.CargarJson(filePart);
          
        } catch (InvalidDataException ex) {
          
        }

    }
}
