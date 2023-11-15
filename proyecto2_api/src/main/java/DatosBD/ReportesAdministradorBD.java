/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.CategoriaReporte;
import Datos.EmpleadorReporte;
import Datos.HistorialComision;
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
public class ReportesAdministradorBD {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    private String Reporte1 = "WITH HistorialComisiones AS ( SELECT codigo, comision, fecha, LEAD(fecha) OVER (ORDER BY fecha) AS fecha_final FROM parametros ) SELECT * FROM HistorialComisiones;";
    private String Reporte2 = "SELECT u.codigo AS codigo_usuario, u.nombre AS nombre_usuario, COUNT(o.codigo) AS total_ofertas_publicadas FROM usuarios AS u JOIN ofertas AS o ON u.codigo = o.codigo_empresa GROUP BY u.codigo, u.nombre ORDER BY total_ofertas_publicadas DESC LIMIT 5;";
    private String Reporte3Fecha = "SELECT u.codigo AS codigo_empresa, u.nombre AS nombre_empresa, SUM(hc.monto) AS total_generado FROM usuarios AS u JOIN historial_cobros AS hc ON u.codigo = hc.codigo_empresa WHERE hc.fecha BETWEEN ? AND ? GROUP BY u.codigo, u.nombre ORDER BY total_generado DESC LIMIT 5;";
    private String Reporte3 = "SELECT u.codigo AS codigo_empresa, u.nombre AS nombre_empresa, SUM(hc.monto) AS total_generado FROM usuarios AS u JOIN historial_cobros AS hc ON u.codigo = hc.codigo_empresa GROUP BY u.codigo, u.nombre ORDER BY total_generado DESC LIMIT 5;";
    private String Reporte4 = "SELECT c.nombre AS nombre_categoria, COUNT(o.codigo) AS total_ofertas_publicadas, SUM(hc.monto) AS total_ingresos_generados FROM categorias AS c LEFT JOIN ofertas AS o ON c.codigo = o.categoria LEFT JOIN historial_cobros AS hc ON o.codigo = hc.codigo_oferta GROUP BY c.nombre;";

    public List<HistorialComision> Reporte1() {
        System.out.println("entramos al getofertas");
        List<HistorialComision> historial = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas");
            PreparedStatement select = conexion.prepareStatement(Reporte1);

            System.out.println("Query"+select.toString());
            ResultSet resultset = select.executeQuery();

            while (resultset.next()) {
                historial.add(new HistorialComision(resultset.getString("codigo"), resultset.getFloat("comision"),
                        resultset.getString("fecha"), resultset.getString("fecha_final")
                ));

                System.out.println("codigo:   " + resultset.getString("codigo"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return historial;
    }

    public List<EmpleadorReporte> Reporte2() {
        System.out.println("entramos al getofertas");
        List<EmpleadorReporte> top5 = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas");
            PreparedStatement select = conexion.prepareStatement(Reporte2);

            ResultSet resultset = select.executeQuery();

            while (resultset.next()) {
                top5.add(new EmpleadorReporte(resultset.getString("codigo_usuario"), resultset.getString("nombre_usuario"),
                        resultset.getFloat("total_ofertas_publicadas")
                ));

            
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            System.out.println(ex);
        }
        return top5;
    }

    public List<EmpleadorReporte> Reporte3() {
        System.out.println("entramos al reporte 3");
        List<EmpleadorReporte> top5 = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas");
            PreparedStatement select = conexion.prepareStatement(Reporte3);

            ResultSet resultset = select.executeQuery();

            System.out.println("Query " + select.toString());
            while (resultset.next()) {
                top5.add(new EmpleadorReporte(resultset.getString("codigo_empresa"), resultset.getString("nombre_empresa"),
                        resultset.getFloat("total_generado")
                ));

               System.out.println("codigo:   " + resultset.getString("codigo_empresa"));
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            System.out.println(ex);
        }
        return top5;
    }

    public List<EmpleadorReporte> Reporte3Fecha(String fecha1, String fecha2) {
        System.out.println("entramos al getofertas");
        List<EmpleadorReporte> top5 = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas");
            PreparedStatement select = conexion.prepareStatement(Reporte3Fecha);
            select.setString(1, fecha1);
            select.setString(2, fecha2);

            System.out.println("query "+ select.toString());
            ResultSet resultset = select.executeQuery();

            while (resultset.next()) {
                top5.add(new EmpleadorReporte(resultset.getString("codigo_empresa"), resultset.getString("nombre_empresa"),
                        resultset.getFloat("total_generado")
                ));

            //    System.out.println("codigo:   " + resultset.getString("codigo"));
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            System.out.println("---------------------");
            System.out.println(ex);
        }
        return top5;
    }

    public List<CategoriaReporte> Reporte4() {
        System.out.println("entramos al getofertas");
        List<CategoriaReporte> top5 = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas");
            PreparedStatement select = conexion.prepareStatement(Reporte4);

            ResultSet resultset = select.executeQuery();

            while (resultset.next()) {
                top5.add(new CategoriaReporte(resultset.getString("nombre_categoria"), resultset.getInt("total_ofertas_publicadas"),
                        resultset.getFloat("total_ingresos_generados")
                ));

               // System.out.println("codigo:   " + resultset.getString("codigo"));
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            System.out.println(ex);
        }
        return top5;
    }

}
