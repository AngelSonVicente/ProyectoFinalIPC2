/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.CompletarPerfilUsuario;
import Datos.Oferta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private String Select = "SELECT * FROM telefonos WHERE cod_usuario = ?";
    private String Delete = "DELETE FROM telefonos WHERE cod_usuario = ?";

    public CompletarPerfilUsuario ingresarTelefonos(String codigoUsuario, List<String> telefonos) {
        try {
            System.out.println("Entramos al try de ingresar telefonos");
            System.out.println("Conexion en el Telefonos BD: " + conexion);
            // Suponiendo que la conexión y la consulta están bien definidas
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);

            // Configuramos el código de usuario
            insert.setString(1, codigoUsuario);

            // Obtener la lista de teléfonos
            // Iterar sobre la lista de teléfonos
            for (int i = 0; i < telefonos.size(); i++) {
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

    public List<String> getTelefonos(String codigo) {
        System.out.println("entramos al getofertas");
        List<String> telefonos = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(Select);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                telefonos.add(resultset.getString("telefono"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo

            System.out.println("-----------------------------------------------------------------");
            System.out.println(ex);
        }

        return telefonos;
    }

    public boolean eliminarTelefonosPorUsuario(String codigoUsuario) {
        try {
            // Preparar la declaración SQL
            PreparedStatement deleteStatement = conexion.prepareStatement(Delete);

            deleteStatement.setString(1, codigoUsuario);

            System.out.println("query: "+ deleteStatement.toString());
            
            int filasAfectadas = deleteStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registros eliminados ");
                return true;
         
            } else {
                System.out.println("No se encontraron registros para eliminar.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
