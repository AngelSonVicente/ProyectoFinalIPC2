/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Entrevista;
import Datos.Solicitudes;
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
public class EntrevistaBD {
    
    static Connection conexion = ConexionBD.getInstancia().getConexion();

    private static String SelectExiste = "SELECT * FROM solicitudes WHERE codigo_usuario = ? AND codigo_oferta = ?";
   private static String SelectTodoUsuario = "SELECT e.*, o.nombre AS nombre_oferta, u.nombre AS nombre_usuario FROM entrevistas AS e JOIN ofertas AS o ON e.codigo_oferta = o.codigo JOIN usuarios AS u ON e.usuario = u.codigo WHERE e.estado = 'Pendiente' AND e.usuario = ?";
    
     public List<Entrevista> getEntrevistasUsuario(String codigo) {
        List<Entrevista> entrevistas = new ArrayList<>();
        try {
            System.out.println(SelectTodoUsuario);
            PreparedStatement select = conexion.prepareStatement(SelectTodoUsuario);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                entrevistas.add(new Entrevista(resultset.getString("codigo"), resultset.getString("codigo_oferta"),
                        resultset.getString("nombre_oferta"), resultset.getString("usuario"), resultset.getString("nombre_usuario"),
                        resultset.getString("fecha"), resultset.getString("hora"),resultset.getString("ubicacion"),
                        resultset.getString("estado"), resultset.getString("notas")
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
