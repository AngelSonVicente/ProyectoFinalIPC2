/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Categoria;
import static DatosBD.GestionCategoriaBD.conexion;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class CurriculumBD {

    private Connection conexion;

    public CurriculumBD(Connection conexion) {
        this.conexion = conexion;
    }

    private static String Update = "UPDATE usuarios set cv = ? WHERE codigo = ?";

    public boolean IngresarCV(String codigo, InputStream pdfCV) {
        System.out.println("Actualizando la CV");
        try {
            PreparedStatement update = conexion.prepareStatement(Update);
            update.setBlob(1, pdfCV);
            update.setString(2, codigo);
     
            int affectedRows = update.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("PDF actualizado");
                return true;
            } else {
                System.out.println("La actualización no tuvo éxito.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }

        return false;
    }

}
