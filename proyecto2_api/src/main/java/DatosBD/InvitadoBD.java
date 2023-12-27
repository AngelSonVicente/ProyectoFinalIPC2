/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Oferta;
import static DatosBD.OfertaBD.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class InvitadoBD {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    private String Update = "UPDATE invitados SET cantidad_invitados = cantidad_invitados + 1 WHERE codigo = 1";

    public boolean AgregarInvitado() {
        System.out.println("Actualizando la invitados ");
        try {
            PreparedStatement update = conexion.prepareStatement(Update);
            int affectedRows = update.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("Invitados Actualizados");
                return true;
            } else {
                System.out.println("La actualización no tuvo éxito.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ex);
        }

        return false;
    }
}
