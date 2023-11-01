/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import DatosBD.ConexionBD;
import Datos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author MSI
 */
public class UsuarioBD {
    
     private static final String SelectUsuario = "SELECT * FROM usuarios WHERE usuario = ?";

    
       static Connection conexion = ConexionBD.getInstancia().getConexion();
 
     public static Usuario getUsuarioByUser(String usuario) {
        // validateCarnet not null
        try {
            PreparedStatement select = conexion.prepareStatement(SelectUsuario);
            select.setString(1, usuario);
            ResultSet resultset = select.executeQuery();
            
            if (resultset.next()) {
                return new Usuario(resultset.getInt("codigo"),
                resultset.getString("nombre"),resultset.getString("usuario"),
                resultset.getString("direccion"),resultset.getString("correo"),"",
                resultset.getString("cui"),resultset.getString("birth"),resultset.getString("tipo"),
                resultset.getBytes("cv"));
            }
            
            return null;
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
        }
        
        return null;
    }
    
}
