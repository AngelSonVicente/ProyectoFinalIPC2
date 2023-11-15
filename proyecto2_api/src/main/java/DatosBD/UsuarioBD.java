/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import DatosBD.ConexionBD;
import Datos.Usuario;
import Datos.Util;
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
     private static final String SelectUsuarioID = "SELECT * FROM usuarios WHERE codigo = ?";
     private static final String Insert = "INSERT INTO usuarios(nombre, usuario, password, direccion, correo, cui, birth, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

     private Util util = new Util();
    
       static Connection conexion = ConexionBD.getInstancia().getConexion();
   public Usuario crearUsuario(Usuario usuario) {
        System.out.println("esta creando el usuario");
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, usuario.getNombre());
            insert.setString(2, usuario.getUsuario());
            insert.setString(3, util.Encriptar(usuario.getPassword()));
            insert.setString(4, usuario.getDireccion());
            insert.setString(5, usuario.getCorreo());
            insert.setString(6, usuario.getCui());
            insert.setString(7, usuario.getBirth());
            insert.setString(8, usuario.getTipo());
           
            
            System.out.println(insert.toString());
            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    System.out.println("categoria Creada");
                    usuario.setCodigo(generatedID);
                    return usuario;
                } else {
                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("----------------------------------");
            ex.printStackTrace();
        }

        return null;
    }
       
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
     
     public static Usuario getUsuarioCodigo(String codigo) {
        // validateCarnet not null
        try {
            PreparedStatement select = conexion.prepareStatement(SelectUsuarioID);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();
            System.out.println("----------------------------------------------------");
            System.out.println(select.toString());
            
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
