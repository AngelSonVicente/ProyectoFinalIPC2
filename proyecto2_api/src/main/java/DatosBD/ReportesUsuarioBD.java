/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Oferta;
import Datos.SolicitudRetirada;
import Datos.Solicitudes;
import static DatosBD.ReportesEmpleador.conexion;
import static DatosBD.SolicitudesBD.conexion;
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
public class ReportesUsuarioBD {
        static Connection conexion = ConexionBD.getInstancia().getConexion();

    String Reporte1Fecha ="SELECT solicitudes_retiradas.*, o.nombre AS nombre_oferta FROM solicitudes_retiradas JOIN ofertas AS o ON solicitudes_retiradas.codigo_oferta = o.codigo WHERE o.fecha_publicacion BETWEEN ? AND ? AND solicitudes_retiradas.codigo_usuario = ?;";
    String Reporte1 ="SELECT solicitudes_retiradas.*, o.nombre AS nombre_oferta FROM solicitudes_retiradas JOIN ofertas AS o ON solicitudes_retiradas.codigo_oferta = o.codigo WHERE solicitudes_retiradas.codigo_usuario = ?;";
    String Reporte2 ="SELECT s.*, o.nombre AS nombre_oferta FROM solicitudes AS s JOIN ofertas AS o ON s.codigo_oferta = o.codigo WHERE s.codigo_usuario = ? AND s.estado = 'Entrevista';";
    String Reporte3 ="SELECT s.*, o.nombre AS nombre_oferta FROM solicitudes AS s JOIN ofertas AS o ON s.codigo_oferta = o.codigo WHERE s.estado = 'Rechazado' AND s.codigo_usuario = ?;";
    String Reporte4 ="SELECT s.*, o.nombre AS nombre_oferta FROM solicitudes AS s JOIN ofertas AS o ON s.codigo_oferta = o.codigo WHERE s.estado != 'Elegido' AND s.codigo_usuario = ?;";
    public List<SolicitudRetirada> Reporte1(String codigoEmpresa) {
        System.out.println("entramos al getofertas");
        List<SolicitudRetirada> solicitudes = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas" + Reporte1);
            PreparedStatement select = conexion.prepareStatement(Reporte1);
            select.setString(1, codigoEmpresa);

            ResultSet resultset = select.executeQuery();

            while (resultset.next()) {
                solicitudes.add(new SolicitudRetirada(resultset.getString("codigo"), resultset.getString("codigo_usuario"),
                        "", resultset.getString("codigo_oferta"), resultset.getString("nombre_oferta"),
                        resultset.getString("fecha")
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
    public List<SolicitudRetirada> Reporte1Fecha(String codigoEmpresa, String fecha1, String fecha2) {
        System.out.println("entramos al getofertas");
        List<SolicitudRetirada> solicitudes = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas"+ Reporte1Fecha);
            PreparedStatement select = conexion.prepareStatement(Reporte1Fecha);
            select.setString(1, fecha1);
            select.setString(2, fecha2);
            select.setString(3, codigoEmpresa);
            System.out.println("query: " +select.toString() );
            ResultSet resultset = select.executeQuery();

            
            while (resultset.next()) {
                solicitudes.add(new SolicitudRetirada(resultset.getString("codigo"), resultset.getString("codigo_usuario"),
                        "", resultset.getString("codigo_oferta"), resultset.getString("nombre_oferta"),
                        resultset.getString("fecha")
                ));

             //   System.out.println("codigo:   " + resultset.getString("codigo"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return solicitudes;
    }

    
     public List<Solicitudes> Reporte2(String codigoUsuario) {
        List<Solicitudes> solicitudes = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(Reporte2);
            select.setString(1, codigoUsuario);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                solicitudes.add(new Solicitudes(resultset.getString("codigo"), resultset.getString("codigo_oferta"),
                        resultset.getString("nombre_oferta"), resultset.getString("codigo_usuario"), "",
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

    
    

     public List<Solicitudes> Reporte3(String codigoUsuario) {
        List<Solicitudes> solicitudes = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(Reporte3);
            select.setString(1, codigoUsuario);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                solicitudes.add(new Solicitudes(resultset.getString("codigo"), resultset.getString("codigo_oferta"),
                        resultset.getString("nombre_oferta"), resultset.getString("codigo_usuario"), "",
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
     public List<Solicitudes> Reporte4(String codigoUsuario) {
        List<Solicitudes> solicitudes = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(Reporte4);
            select.setString(1, codigoUsuario);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                solicitudes.add(new Solicitudes(resultset.getString("codigo"), resultset.getString("codigo_oferta"),
                        resultset.getString("nombre_oferta"), resultset.getString("codigo_usuario"), "",
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
    
    
}
