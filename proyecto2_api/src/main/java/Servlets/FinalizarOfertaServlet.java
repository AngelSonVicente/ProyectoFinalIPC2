/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Datos.Entrevista;
import Datos.FinalizarOferta;
import Datos.JsonUtil;
import Service.FinalizarOfertaService;
import Service.OfertaService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author MSI
 */

@WebServlet(name = "FinalizacionOfertaController", urlPatterns = {"/v1/TerminarOferta"})
public class FinalizarOfertaServlet extends HttpServlet{
    FinalizarOfertaService finalizarService = new FinalizarOfertaService();
    OfertaService ofertaService = new OfertaService();
    JsonUtil jsonUtil = new JsonUtil(); 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
           
        String BODY=jsonUtil.getBody(request);
BufferedReader reader = request.getReader();
StringBuilder stringBuilder = new StringBuilder();
String line;

while ((line = reader.readLine()) != null) {
    stringBuilder.append(line);
}

String body = stringBuilder.toString();

// Utiliza Gson para analizar la cadena JSON en objetos Java
Gson gson = new Gson();
FinalizarOferta datos = gson.fromJson(body, FinalizarOferta.class);

// Ahora puedes acceder a los datos como propiedades del objeto DatosEnvio
String codigoOferta = datos.getCodigoOferta();
String codigoUsuario = datos.getCodigoUsuarioElegido();
String codigoEmpresa = datos.getCodigoEmpresa();

            
            
        System.out.println("entrando al post del servlet finalizar");
        System.out.println("Oferta ----  "+ codigoOferta);
        System.out.println("USuario elegido ----  "+ codigoUsuario);
        System.out.println("EMpresa ----  "+ codigoEmpresa);
        
        
        
        FinalizarOferta finalizacion = new FinalizarOferta(codigoOferta, codigoUsuario, null, codigoEmpresa);
        
        FinalizarOferta finalizarHecha= finalizarService.finalizarOferta(datos);
        
        jsonUtil.EnviarJson(response, finalizarHecha);
       
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
         
        
        
        
         String codigo = request.getParameter("codigoOferta");
        if(ofertaService.OfertaFinalizada(codigo)){
                 response.setStatus(HttpServletResponse.SC_OK);

        
        }else{
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

        
    }
    
    
    
    
}
