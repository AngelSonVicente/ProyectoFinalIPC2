/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Entrevista;
import Datos.HistorialCobros;
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
public class ReportesEmpleador {

    private Connection conexion ;

    public ReportesEmpleador(Connection conexion) {
        this.conexion = conexion;
    }

    public ReportesEmpleador() {
        conexion = ConexionBD.getInstancia().getConexion();

    }
    
    
    
    
    private String Reporte1Fechas = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.codigo_empresa = ? AND o.fecha_publicacion BETWEEN ? AND ?;";
    private String Reporte1 = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.codigo_empresa = ?";

    private String Reporte2Fecha = "SELECT e.*, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM entrevistas AS e JOIN ofertas AS o ON e.codigo_oferta = o.codigo JOIN usuarios AS u ON e.usuario = u.codigo WHERE o.codigo_empresa = ? AND e.fecha=?;";
    private String Reporte2 = "SELECT e.*, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM entrevistas AS e JOIN ofertas AS o ON e.codigo_oferta = o.codigo JOIN usuarios AS u ON e.usuario = u.codigo WHERE o.codigo_empresa = ?;";
    private String Reporte3 = "SELECT hc.*, SUM(hc.monto) OVER (ORDER BY hc.codigo) AS total_acumulado FROM historial_cobros AS hc WHERE hc.codigo_empresa = ?;";
    public List<HistorialCobros> Reporte3(String codigoEmpresa) {
        List<HistorialCobros> historial = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(Reporte3);
            select.setString(1, codigoEmpresa);

            System.out.println("");
            ResultSet resultset = select.executeQuery();

            System.out.println("Query: "+ select.toString());
            while (resultset.next()) {
                historial.add(new HistorialCobros(resultset.getString("codigo"), resultset.getString("codigo_empresa"),
                        resultset.getString("codigo_oferta"), "", resultset.getFloat("monto"),
                        resultset.getFloat("total_acumulado")));

            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return historial;
    }

    public List<Oferta> Reporte1Fecha(String codigoEmpresa, String fecha1, String fecha2) {
        System.out.println("entramos al getofertas");
        List<Oferta> ofertas = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas");
            PreparedStatement select = conexion.prepareStatement(Reporte1Fechas);
            select.setString(1, codigoEmpresa);
            select.setString(2, fecha1);
            select.setString(3, fecha2);

            ResultSet resultset = select.executeQuery();

            while (resultset.next()) {
                ofertas.add(new Oferta(resultset.getString("codigo"), resultset.getString("codigo_empresa"),
                        resultset.getString("nombre_empresa"), resultset.getString("nombre"), resultset.getString("descripcion"),
                        resultset.getString("categoria"), resultset.getString("nombre_categoria"), resultset.getString("estado"),
                        resultset.getString("fecha_publicacion"), resultset.getString("fecha_limite"), resultset.getFloat("salario"),
                        resultset.getString("modalidad"), resultset.getString("ubicacion"), resultset.getString("detalles"),
                        resultset.getString("usuario_elegido"), resultset.getString("nombre_usuario_elegido")
                ));

                System.out.println("codigo:   " + resultset.getString("codigo"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return ofertas;
    }

    public List<Oferta> Reporte1(String codigoEmpresa) {
        System.out.println("entramos al getofertas");
        List<Oferta> ofertas = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas");
            PreparedStatement select = conexion.prepareStatement(Reporte1);
            select.setString(1, codigoEmpresa);

            ResultSet resultset = select.executeQuery();

            while (resultset.next()) {
                ofertas.add(new Oferta(resultset.getString("codigo"), resultset.getString("codigo_empresa"),
                        resultset.getString("nombre_empresa"), resultset.getString("nombre"), resultset.getString("descripcion"),
                        resultset.getString("categoria"), resultset.getString("nombre_categoria"), resultset.getString("estado"),
                        resultset.getString("fecha_publicacion"), resultset.getString("fecha_limite"), resultset.getFloat("salario"),
                        resultset.getString("modalidad"), resultset.getString("ubicacion"), resultset.getString("detalles"),
                        resultset.getString("usuario_elegido"), resultset.getString("nombre_usuario_elegido")
                ));

                System.out.println("codigo:   " + resultset.getString("codigo"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return ofertas;
    }

    public List<Entrevista> Reporte2Fecha(String codigoUsuario, String fecha) {
        List<Entrevista> entrevistas = new ArrayList<>();
        try {
                System.out.println("Entre aca");
            PreparedStatement select = conexion.prepareStatement(Reporte2Fecha);
            select.setString(1, codigoUsuario);
            select.setString(2, fecha);
            ResultSet resultset = select.executeQuery();
            System.out.println("query: "+ select.toString());
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

    public List<Entrevista> Reporte2(String codigoUsuario) {
        List<Entrevista> entrevistas = new ArrayList<>();
        try {
            System.out.println("tambien entre");
            PreparedStatement select = conexion.prepareStatement(Reporte2);
            select.setString(1, codigoUsuario);
            ResultSet resultset = select.executeQuery();
            
            System.out.println("Query: " + select.toString());
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
