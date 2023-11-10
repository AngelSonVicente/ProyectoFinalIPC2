/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Solicitudes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class SolicitudesBD {
    static Connection conexion = ConexionBD.getInstancia().getConexion();

    private static String SelectTodo = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.estado = 'Activo'";
    private static String SelectExiste = "SELECT * FROM solicitudes WHERE codigo_usuario = ? AND codigo_oferta = ?";
   
    private static String SelectTodoEmpresa = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.estado = 'Activo' AND o.codigo_empresa = ?";
    private static String SelectOfertaID = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.codigo = ?";

    private static String Update = "UPDATE categorias set nombre = ?, decripcion = ? WHERE codigo = ?";

    
    private static String Insert = "INSERT INTO solicitudes (codigo_oferta, codigo_usuario, mensaje) VALUES (?, ?, ?)";
    //
    public Solicitudes crearSolicitud(Solicitudes solicitud) {
        System.out.println("esta creando la solicitud");
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, solicitud.getCodigoOferta());
            insert.setString(2, solicitud.getCodigoUsuario());
            insert.setString(3, solicitud.getMensaje());

            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedID = generatedKeys.getString(1);
                    System.out.println("categoria Creada");
                    solicitud.setCodigo(generatedID);
                    return solicitud;
                } else {
                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    
      public boolean ExisteSolicitud(String codigoUsuario, String codigoOferta) {
        try {
            PreparedStatement select = conexion.prepareStatement(SelectExiste);
            select.setString(1, codigoUsuario);
            select.setString(2, codigoOferta);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return true;
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            
            System.out.println("Error:  "+ex);
        }

    return false;
    }
  

    
    
    
}
