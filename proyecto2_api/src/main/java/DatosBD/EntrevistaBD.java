/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Entrevista;
import Datos.Oferta;
import Datos.Solicitudes;
import static DatosBD.OfertaBD.conexion;
import static DatosBD.SolicitudesBD.conexion;
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
public class EntrevistaBD {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    private static String SelectExiste = "SELECT * FROM solicitudes WHERE codigo_usuario = ? AND codigo_oferta = ?";
    private static String SelectTodoUsuario = "SELECT e.*, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM entrevistas AS e JOIN ofertas AS o ON e.codigo_oferta = o.codigo JOIN usuarios AS u ON e.usuario = u.codigo WHERE e.estado = 'Pendiente' AND e.usuario = ?";
        private static String SelectEntevistasFecha = "SELECT e.*, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM entrevistas AS e JOIN ofertas AS o ON e.codigo_oferta = o.codigo JOIN usuarios AS u ON e.usuario = u.codigo WHERE e.estado = 'Pendiente' AND e.usuario = ?";
    private static String Insert = "INSERT INTO entrevistas (codigo, codigo_oferta, usuario, fecha, hora, ubicacion, estado, notas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static String FinalizarEntrevista = "UPDATE entrevistas SET estado = 'Finalizado', notas = ? WHERE codigo = ?";
    private static String SelectEntrevistasOferta="SELECT e.*, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM entrevistas AS e JOIN ofertas AS o ON e.codigo_oferta = o.codigo JOIN usuarios AS u ON e.usuario = u.codigo WHERE e.codigo_oferta = ?";
    
      
    public Entrevista finalizarEntrevista(Entrevista entrevista) {
    System.out.println("Actualizando la categoría");
    try {
        PreparedStatement update = conexion.prepareStatement(FinalizarEntrevista);
        update.setString(1, entrevista.getNota());
        update.setString(2, entrevista.getCodigo());
        System.out.println("QueryFinalizacion : " + update.toString());
        int affectedRows = update.executeUpdate();

        if (affectedRows == 1) {
            System.out.println("Categoría actualizada");
            return entrevista;
        } else {
            System.out.println("La actualización no tuvo éxito.");
            return null;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println(ex);
    }

    return null;
}
    
    public Entrevista crearEntrevista(Entrevista entrevista) {

        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, entrevista.getCodigo());
            insert.setString(2, entrevista.getCodigoOferta());
            insert.setString(3, entrevista.getCodigoUsuario());
            insert.setString(4, entrevista.getFecha());
            insert.setString(5, entrevista.getHora());
            insert.setString(6, entrevista.getUbicacion());
            insert.setString(7, entrevista.getEstado());
            insert.setString(8, entrevista.getNota());

            System.out.println(insert.toString());
            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedID = generatedKeys.getString(1);
                    System.out.println("categoria Creada");
                    entrevista.setCodigo(generatedID);
                    return entrevista;
                } else {
                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public List<Entrevista> getEntrevistasUsuario(String codigo) {
        List<Entrevista> entrevistas = new ArrayList<>();
        try {
      //      System.out.println(SelectTodoUsuario);
            PreparedStatement select = conexion.prepareStatement(SelectTodoUsuario);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                entrevistas.add(new Entrevista(resultset.getString("codigo"), resultset.getString("codigo_oferta"),
                        resultset.getString("nombre_oferta"), resultset.getString("usuario"), resultset.getString("nombre_usuario"),
                        resultset.getString("fecha"), resultset.getString("hora"), resultset.getString("ubicacion"),
                        resultset.getString("estado"), resultset.getString("notas"),null
                ));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return entrevistas;
    }

    public List<Entrevista> getEntrevistasOferta(String codigo) {
        List<Entrevista> entrevistas = new ArrayList<>();
        try {
        //    System.out.println(SelectTodoUsuario);
            PreparedStatement select = conexion.prepareStatement(SelectEntrevistasOferta);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();
           // System.out.println("Query: " + select.toString());
            
            while (resultset.next()) {
                entrevistas.add(new Entrevista(resultset.getString("codigo"), resultset.getString("codigo_oferta"),
                        resultset.getString("nombre_oferta"), resultset.getString("usuario"), resultset.getString("nombre_usuario"),
                        resultset.getString("fecha"), resultset.getString("hora"), resultset.getString("ubicacion"),
                        resultset.getString("estado"), resultset.getString("notas"),null
                ));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return entrevistas;
    }

}
