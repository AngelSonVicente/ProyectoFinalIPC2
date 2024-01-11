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
import java.util.List;
import org.apache.commons.io.FilenameUtils;

public class CargaJsonService {

    private static final String PATH = "C:/Users/MSI/Documents";
    private static final String NAME = "Entrada";
    private static final String PDF_PART_PREFIX = "PdfEntrada";

    private SubirArchivoEntradaBD subirArchivo = new SubirArchivoEntradaBD();

    public void CargarJson(Part jsonPart, List<Part> pdfParts) throws IOException, InvalidDataException {
        validar(jsonPart.getContentType());

        System.out.println("COntent Type: " + jsonPart.getContentType());
        System.out.println("SubmittedFileName: " + jsonPart.getSubmittedFileName());
        System.out.println("Header: " + jsonPart.getHeader("Content-disposition"));

        //Enviar esto a una funcion para que se suba a la base
        InputStream jsonStream = jsonPart.getInputStream();

      //  String finalPath = PATH + File.separatorChar + NAME + "." + FilenameUtils.getExtension(filePart.getSubmittedFileName());

        //subir al BD
        subirArchivo.SubirJSon(jsonStream, pdfParts);
        
        
        
//        for (Part part : pdfParts) {
//            
//            String PDFname = part.getSubmittedFileName().substring(0,part.getSubmittedFileName().length()-8);
//            
//            System.out.println("Nombre del archivo PDF BAck: " + part.getSubmittedFileName());
//            String finalPathPDF = PATH + File.separatorChar + PDFname + "." + FilenameUtils.getExtension(part.getSubmittedFileName());
//         
//            part.write(finalPathPDF);
//      
//        }

        // filePart.write(finalPath);
    }

    public void validar(String contentType) throws InvalidDataException {
        if (contentType == null || !contentType.equals("application/json")) {
            throw new InvalidDataException("Sin archivo o No es un Archivo Json");
        }

    }

}
