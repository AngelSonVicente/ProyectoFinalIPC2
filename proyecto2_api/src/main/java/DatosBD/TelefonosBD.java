/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.CompletarPerfilUsuario;
import static DatosBD.GestionCategoriaBD.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class TelefonosBD {
   private Connection conexion;

    public TelefonosBD(Connection conexion) {
        this.conexion = conexion;
    }
    private String Insert = "INSERT INTO telefonos (cod_usuario, telefono) VALUES (?,?)";

    public CompletarPerfilUsuario ingresarTelefonos(String codigoUsuario, List<String> telefonos) {
        try {
            System.out.println("Entramos al try de ingresar telefonos");
            System.out.println("Conexion en el Telefonos BD: " +conexion );
            // Suponiendo que la conexión y la consulta están bien definidas
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);

            // Configuramos el código de usuario
            insert.setString(1, codigoUsuario);

            // Obtener la lista de teléfonos
           
            // Iterar sobre la lista de teléfonos
            for (int i = 0; i < telefonos.size(); i++) {
                // Configuramos cada teléfono en la posición 'i+2' (ya que la posición 1 es para el código de usuario)
                insert.setString(2, telefonos.get(i));
           
            int affectedRows = insert.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }
            }

            // Ejecutar la inserción

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
