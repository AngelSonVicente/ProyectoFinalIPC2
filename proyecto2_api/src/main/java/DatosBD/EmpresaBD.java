/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Categoria;
import Datos.Empresa;
import static DatosBD.GestionCategoriaBD.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class EmpresaBD {
    
     private static String SelectEmpresaID = "SELECT e.*, u.nombre AS nombre_usuario FROM empresa AS e JOIN usuarios AS u ON e.cod_usuario = u.codigo WHERE e.cod_usuario = ?";
   static Connection conexion = ConexionBD.getInstancia().getConexion();

    
        public Empresa getEmpresa(String codigo) {
        try {
            PreparedStatement select = conexion.prepareStatement(SelectEmpresaID);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return new Empresa(resultset.getString("codigo"), resultset.getString("cod_usuario"),
                        resultset.getString("nombre_usuario"), resultset.getString("mision"),
                        resultset.getString("vision"), resultset.getString("titular_tarjeta"),
                        resultset.getString("codigo_seguridad")
                
                );
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            
            System.out.println("Error:  "+ex);
        }

    return null;
    }
    
}
