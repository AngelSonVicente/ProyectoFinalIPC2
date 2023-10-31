package Service;


import Datos.ConexionBD;
import Datos.Usuario;
import Datos.Util;
import DatosBD.UsuarioBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MSI
 */
public class LoginService {
    
    
    static Connection conexion = ConexionBD.getInstancia().getConexion();
    Util util = new Util();

    private static final String SelectPassword = "SELECT password FROM usuarios WHERE usuario = ?";

    public Usuario IsLogin(String ContraIngresada, String UsuarioIngresado) {
        String ContraEncriptada= util.Encriptar(ContraIngresada);


        String Contra= obtnerContra(UsuarioIngresado);


        Usuario usuario = new Usuario();

        if(ContraEncriptada.equals(Contra)){

            usuario=UsuarioBD.getUsuarioByUser(UsuarioIngresado);
            return usuario;
            /*
            
             response.setContentType("application/json");
        String res = gson.toJson(login);
        var out = response.getWriter();
        out.print(res);
            */

            }

        return null;
    }
    
   private String obtnerContra(String Usuario){
   
        try {
            PreparedStatement select = conexion.prepareStatement(SelectPassword);
            select.setString(1, Usuario);
            ResultSet resultset = select.executeQuery();
            
            if (resultset.next()) {
                return resultset.getString("password");
            }
            
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
       return null;
   }

    
    
}
