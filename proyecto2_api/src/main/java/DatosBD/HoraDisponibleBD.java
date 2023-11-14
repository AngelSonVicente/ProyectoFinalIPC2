/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Categoria;
import Datos.HoraDisponible;
import static DatosBD.GestionCategoriaBD.conexion;
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
public class HoraDisponibleBD {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    private static String SelectCategorias = "SELECT e.*, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM entrevistas AS e JOIN ofertas AS o ON e.codigo_oferta = o.codigo JOIN usuarios AS u ON e.usuario = u.codigo WHERE e.estado = 'Pendiente' AND e.fecha = ? AND e.codigo_oferta = ? AND e.ubicacion= ?;";

    public List<HoraDisponible> getHorasOcupadas(String fecha, String ubicacion, String codigoOferta) {
        List<HoraDisponible> horas = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(SelectCategorias);
            select.setString(1, fecha);
            select.setString(2, codigoOferta);
            select.setString(3, ubicacion);

            System.out.println("Horas ocupadas");
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                horas.add(new HoraDisponible(resultset.getString("hora"))
                );

                System.out.println(resultset.getString("hora"));
              
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            System.out.println("Error:  " + ex);
        }

        return horas;
    }

}
