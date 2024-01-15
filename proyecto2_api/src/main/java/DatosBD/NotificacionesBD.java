/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Categoria;
import Datos.Notificaciones;
import Datos.Oferta;
import  DatosBD.GestionCategoriaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class NotificacionesBD {

    private Connection conexion;

    public NotificacionesBD(Connection conexion) {
        this.conexion = conexion;
    }

    private String Select = "SELECT n.*, u1.nombre AS nombreUsuario, u2.nombre AS nombreUsuarioDestino, o.nombre AS nombreOferta FROM notificaciones AS n JOIN usuarios AS u1 ON n.codigoUsuario = u1.codigo JOIN usuarios AS u2 ON n.codigoUsuarioDestino = u2.codigo JOIN ofertas AS o ON n.codigoOferta = o.codigo WHERE n.codigoUsuarioDestino = ?";
    private String Insert = "INSERT INTO notificaciones (codigoUsuario, codigoUsuarioDestino, codigoOferta, contenido, fecha, estado) VALUES (?, ?, ?, ?, ?, ?)";
    private String Update = "UPDATE notificaciones SET estado = 'Leido' WHERE codigoUsuarioDestino = ?";

    public List<Notificaciones> getNotificaciones(String codigoUsuarioDestino) {
        List<Notificaciones> notificaciones = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(Select);
            select.setString(1, codigoUsuarioDestino);

            System.out.println("queryy_ " + select.toString());
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                notificaciones.add(new Notificaciones(resultset.getString("codigo"), resultset.getString("codigoUsuario"), resultset.getString("nombreUsuario"), resultset.getString("codigoUsuarioDestino"),
                        resultset.getString("nombreUsuarioDestino"), resultset.getString("codigoOferta"), resultset.getString("nombreOferta"), resultset.getString("contenido"), resultset.getString("fecha"), resultset.getString("estado"))
                );

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error:  " + ex);
        }

        return notificaciones;
    }

    public Notificaciones crearNotificacion(Notificaciones notificacion) {
        System.out.println("esta creando la notificacion");
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato para obtener solo la fecha
        String fecha = fechaActual.format(formato);
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, notificacion.getCodigoUsuario());
            insert.setString(2, notificacion.getCodigoUsuarioDestino());
            insert.setString(3, notificacion.getCodigoOferta());
            insert.setString(4, notificacion.getContenido());
            insert.setString(5, fecha);
            insert.setString(6, "No leido");
           
            System.out.println("------------Creando Oferta notificaion------------");
            System.out.println(insert.toString());
            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedID = generatedKeys.getString(1);
                    System.out.println("Oferta Creada");
                    notificacion.setCodigo(generatedID);
                    return notificacion;
                } else {
                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    
     public boolean marcarComoLeidoNotificacionesUsuario(String codigoUsuario) {
    System.out.println("Actualizando la categoría");
    try {
        PreparedStatement update = conexion.prepareStatement(Update);
        update.setString(1, codigoUsuario);
        System.out.println("QueryFinalizacion : " + update.toString());
        int affectedRows = update.executeUpdate();

        if (affectedRows >= 1) {
            System.out.println("Marcados como leido");
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
