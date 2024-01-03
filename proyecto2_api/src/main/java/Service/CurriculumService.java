/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DatosBD.ConexionBD;
import DatosBD.CurriculumBD;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author MSI
 */
public class CurriculumService {

    private Connection conexion;

    public CurriculumService(Connection conexion) {
        this.conexion = conexion;
    }

    public CurriculumService() {
        conexion = ConexionBD.getInstancia().getConexion();

    }
    
    
    public boolean IngresarCV (String codigo, Part cvPart) throws IOException{
    CurriculumBD curriculumBD = new CurriculumBD(conexion);
        //validar que sea un pdf auqnue ya se validó en el Completar perfilService, pero este igual se va a usar para actualizar el cv
        return curriculumBD.IngresarCV(codigo,cvPart.getInputStream());
        
    }

}
