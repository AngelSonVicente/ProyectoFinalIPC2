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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class SolicitudesBD {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    private static String SelectExiste = "SELECT * FROM solicitudes WHERE codigo_usuario = ? AND codigo_oferta = ?";
    private static String SelectTodoUsuario = "SELECT s.*, o.codigo AS codigo_oferta, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM solicitudes AS s JOIN ofertas AS o ON s.codigo_oferta = o.codigo JOIN usuarios AS u ON s.codigo_usuario = u.codigo WHERE s.estado = 'Activo' AND s.codigo_usuario = ?";
    private static String SelectTodoOferta = "SELECT s.*, o.codigo AS codigo_oferta, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM solicitudes AS s JOIN ofertas AS o ON s.codigo_oferta = o.codigo JOIN usuarios AS u ON s.codigo_usuario = u.codigo WHERE s.codigo_oferta = ?";
    private static String SelectSoliID = "SELECT s.*, o.codigo AS codigo_oferta, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM solicitudes AS s JOIN ofertas AS o ON s.codigo_oferta = o.codigo JOIN usuarios AS u ON s.codigo_usuario = u.codigo WHERE s.codigo = ?";
    private static String ActualizarEstado="UPDATE solicitudes SET estado = ? WHERE codigo = ?";
    private static String Delete = "DELETE FROM solicitudes WHERE codigo = ?";
    private static String Insert = "INSERT INTO solicitudes (codigo ,codigo_oferta, codigo_usuario, mensaje, estado) VALUES (?, ?, ?, ?, ?)";

    private static String SelectUsuarioOferta ="SELECT * FROM solicitudes WHERE codigo_oferta = ? AND codigo_usuario = ?";
    //
    public Solicitudes crearSolicitud(Solicitudes solicitud) {
        System.out.println("esta creando la solicitud");
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, solicitud.getCodigo());
            insert.setString(2, solicitud.getCodigoOferta());
            insert.setString(3, solicitud.getCodigoUsuario());
            insert.setString(4, solicitud.getMensaje());
            insert.setString(5, solicitud.getEstado());

            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedID = generatedKeys.getString(1);
                    System.out.println("Soli Creada");
                    solicitud.setCodigo(generatedID);
                    return solicitud;
                } else {
                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error: " + ex);
        }

        return null;
    }
    public Solicitudes getSolicitud(String codigo) {
        try {
            PreparedStatement select = conexion.prepareStatement(SelectSoliID);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return new Solicitudes(resultset.getString("codigo"), resultset.getString("codigo_oferta"),
                        resultset.getString("nombre_oferta"), resultset.getString("codigo_usuario"), resultset.getString("nombre_usuario"),
                        resultset.getString("mensaje"), resultset.getString("estado")
                );
            }
        } catch (SQLException ex) {

            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return null;
    }
    public String getSolicitudOU(String codigo, String Oferta) {
        try {
            PreparedStatement select = conexion.prepareStatement(SelectUsuarioOferta);
            select.setString(1, Oferta);
            select.setString(2, codigo);
            System.out.println("Query hecha: " + select.toString());
            ResultSet resultset = select.executeQuery();
            

            if (resultset.next()) {
                return resultset.getString("codigo");
            }
        } catch (SQLException ex) {

            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return null;
    }


      public Solicitudes actualizarEstadoSolicitud(Solicitudes solicitud) {
    System.out.println("Actualizando la oferta");
    try {
        PreparedStatement update = conexion.prepareStatement(ActualizarEstado);
        update.setString(1, solicitud.getEstado());
        update.setString(2, solicitud.getCodigo());
       
        int affectedRows = update.executeUpdate();

        if (affectedRows == 1) {
            System.out.println("Categoría actualizada");
            
            return solicitud;
        } else {
            System.out.println("La actualización no tuvo éxito.");
            return null;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(ex);
    }

    return null;
}
    
    public List<Solicitudes> getSolicitudesUsuario(String codigo) {
        List<Solicitudes> solicitudes = new ArrayList<>();
        try {
            System.out.println(SelectTodoUsuario);
            PreparedStatement select = conexion.prepareStatement(SelectTodoUsuario);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                solicitudes.add(new Solicitudes(resultset.getString("codigo"), resultset.getString("codigo_oferta"),
                        resultset.getString("nombre_oferta"), resultset.getString("codigo_usuario"), resultset.getString("nombre_usuario"),
                        resultset.getString("mensaje"), resultset.getString("estado")
                ));

                System.out.println("codigo:   " + resultset.getString("codigo"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return solicitudes;
    }

    public List<Solicitudes> getSolicitudesOferta(String codigo) {
        List<Solicitudes> solicitudes = new ArrayList<>();
        try {
            System.out.println(SelectTodoUsuario);
            PreparedStatement select = conexion.prepareStatement(SelectTodoOferta);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                solicitudes.add(new Solicitudes(resultset.getString("codigo"), resultset.getString("codigo_oferta"),
                        resultset.getString("nombre_oferta"), resultset.getString("codigo_usuario"), resultset.getString("nombre_usuario"),
                        resultset.getString("mensaje"), resultset.getString("estado")
                ));

                System.out.println("codigo:   " + resultset.getString("codigo"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return solicitudes;
    }

    public boolean borrarSolicitud(String codigoSolicitud) {
        try {
            PreparedStatement delete = conexion.prepareStatement(Delete);
            delete.setString(1, codigoSolicitud);
            int filasAfectadas = delete.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            }

        } catch (SQLException ex) {
            // TODO: Manejar la excepción según tus necesidades
            ex.printStackTrace();
            System.out.println(ex);
        }
        return false;
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

            System.out.println("Error:  " + ex);
        }

        return false;
    }

}
