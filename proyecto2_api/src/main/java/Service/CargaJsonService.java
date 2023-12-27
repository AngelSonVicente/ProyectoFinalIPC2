/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DatosBD.SubirArchivoEntradaBD;
import exceptions.InvalidDataException;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FilenameUtils;

public class CargaJsonService {
        private static final String PATH = "C:/Users/MSI/Documents";
        private static final String NAME = "Entrada";

        private SubirArchivoEntradaBD subirArchivo = new SubirArchivoEntradaBD();
        
    public void CargarJson(Part filePart) throws IOException, InvalidDataException{
        validar(filePart.getContentType());
     
        
        System.out.println("COntent Type: "+filePart.getContentType());
        System.out.println("SubmittedFileName: "+filePart.getSubmittedFileName());
        System.out.println("Header: "+filePart.getHeader("Content-disposition"));
        
      //Enviar esto a una funcion para que se suba a la base
        InputStream fileStream = filePart.getInputStream();   
        //String finalPath = PATH + File.separatorChar + NAME + "." + FilenameUtils.getExtension(filePart.getSubmittedFileName());
     
        System.out.println("Enviando Input al back");
        subirArchivo.SubirJSon(fileStream);
       // filePart.write(finalPath);
    
    }
    
        public void validar(String contentType) throws InvalidDataException{
          if (contentType == null || !contentType.equals("application/json")) {
            throw new InvalidDataException("Sin archivo o No es un Archivo Json");
        }
          
 
    }
    
}
