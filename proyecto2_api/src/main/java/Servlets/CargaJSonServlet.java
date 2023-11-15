/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "JsonController", urlPatterns = {"/v1/CargaJson"})
public class CargaJSonServlet extends HttpServlet {
    private static final String PATH = "C:/Users/MSI/Desktop/Json"; // Ruta sin el separador al final

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lee el JSON del cuerpo de la solicitud
        StringBuilder jsonStringBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        }

        // Obtiene el carnet de los parámetros de la solicitud
        String carnet = request.getParameter("carnet");
        System.out.println("Carnet: " + carnet);

        // Guarda el JSON en un archivo
        String finalPath = PATH + File.separatorChar + carnet + ".json";
        try (PrintWriter writer = new PrintWriter(finalPath)) {
            writer.println(jsonStringBuilder.toString());
        }

        // Puedes responder con algún mensaje si es necesario
        response.getWriter().write("Archivo JSON guardado exitosamente.");
    }
}
