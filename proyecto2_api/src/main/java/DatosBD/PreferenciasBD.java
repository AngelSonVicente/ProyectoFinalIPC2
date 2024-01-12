/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.CompletarPerfilUsuario;
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
public class PreferenciasBD {
        private Connection conexion;

    public PreferenciasBD(Connection conexion) {
        this.conexion = conexion;
    }

      private String Insert = "INSERT INTO preferencias (codigo_usuario, codigo_categoria) VALUES (?,?)";
      private String Select = "SELECT * FROM preferencias WHERE codigo_usuario = ?";
      private String Delete = "DELETE FROM preferencias WHERE codigo_usuario = ?";
      
    public CompletarPerfilUsuario ingresarPreferencias(CompletarPerfilUsuario perfilUsuario) {
        try {
            
            System.out.println("Ingresamos al Try de Ingresar preferencias");
            // Suponiendo que la conexión y la consulta están bien definidas
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);

            // Configuramos el código de usuario
            insert.setString(1, perfilUsuario.getCodigoUsuario());

            // Obtener la lista de teléfonos
            List<String> categorias = perfilUsuario.getCategorias();

                 for (int i = 0; i < categorias.size(); i++) {
                // Configuramos cada teléfono en la posición 'i+2' (ya que la posición 1 es para el código de usuario)
                insert.setString(2, categorias.get(i));
           
            int affectedRows = insert.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
       public List<String> getPreferencias(String codigo) {
        System.out.println("entramos al getofertas");
        List<String> categoriasPreferecia = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(Select);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                categoriasPreferecia.add(resultset.getString("codigo_categoria"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo

            System.out.println("-----------------------------------------------------------------");
            System.out.println(ex);
        }

        return categoriasPreferecia;
    }
       
       
       public boolean eliminarPreferencias(String codigoUsuario) {
        try {
            // Preparar la declaración SQL
            PreparedStatement deleteStatement = conexion.prepareStatement(Delete);

            deleteStatement.setString(1, codigoUsuario);

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
