package Servlets;

import Datos.JsonUtil;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

@WebServlet(name = "JsonController", urlPatterns = {"/v1/CargaJson"})
@MultipartConfig(location = "C:/Users/MSI/Documents/apache-tomcat-10.1.7/temp")
public class CargaJSonServlet extends HttpServlet {

    private static final String PATH = "C:/Users/MSI/Documents";
    private static final String JSON_PART_NAME = "JsonEntrada";
     private static final String PDF_PART_PREFIX = "PdfEntrada";

    private CargaJsonService cargaJsonService = new CargaJsonService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part jsonPart = request.getPart(JSON_PART_NAME);
        System.out.println("Nombre del archivo JSON: " + jsonPart.getSubmittedFileName());

        // Obtener todos los archivos PDF
        List<Part> pdfParts = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().startsWith(PDF_PART_PREFIX)) {
                pdfParts.add(part);
                System.out.println("Nombre del archivo PDF: " + part.getSubmittedFileName());
          
            }
        }
        
        
        

//        try {
//            // Aquí puedes manejar tanto el archivo JSON como la lista de archivos PDF
//            cargaJsonService.CargarJson(jsonPart);
//            for (Part pdfPart : pdfParts) {
//                // Manejar cada archivo PDF según sea necesario
//                System.out.println("");
//            }
//
//        } catch (InvalidDataException ex) {
//            // Manejar la excepción según sea necesario
//        }

    }
}
