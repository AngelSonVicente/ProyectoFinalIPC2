/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.DashBoard;
import Datos.RecuperarCuenta;
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
    private static final String SelectUsuarioCorreo = "SELECT * FROM usuarios WHERE correo = ?";
    private static final String Insert = "INSERT INTO usuarios (codigo, nombre, usuario, password, direccion, correo, cui, birth, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String cambiarPassword = "UPDATE usuarios SET password = ? WHERE codigo = ?";
    private static final String ExisteCorreYUsuario = "SELECT * FROM usuarios WHERE usuario = ? OR correo = ? ";

    private static final String Dashboard = "SELECT COUNT(CASE WHEN tipo = 'Empleador' THEN 1 END) AS total_empleadores, COUNT(CASE WHEN tipo = 'Usuario' THEN 1 END) AS total_usuarios FROM usuarios;";
    Util util = new Util();

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    public Usuario crearUsuario(Usuario usuario) {
        System.out.println("esta creando el usuario");
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setInt(1, usuario.getCodigo());
            insert.setString(2, usuario.getNombre());
            insert.setString(3, usuario.getUsuario());
            insert.setString(4, util.Encriptar(usuario.getPassword()));
            insert.setString(5, usuario.getDireccion());
            insert.setString(6, usuario.getCorreo());
            insert.setString(7, usuario.getCui());
            insert.setString(8, usuario.getBirth());
            insert.setString(9, usuario.getTipo());

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

    public DashBoard getDash() {
        // validateCarnet not null
        try {
            PreparedStatement select = conexion.prepareStatement(Dashboard);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return new DashBoard(resultset.getString("total_empleadores"),
                        resultset.getString("total_usuarios"));
            }

            return null;
        } catch (SQLException ex) {
            // TODO pendiente manejo
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
                        resultset.getString("nombre"), resultset.getString("usuario"),
                        resultset.getString("direccion"), resultset.getString("correo"), "",
                        resultset.getString("cui"), resultset.getString("birth"), resultset.getString("tipo"),
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
                        resultset.getString("nombre"), resultset.getString("usuario"),
                        resultset.getString("direccion"), resultset.getString("correo"), "",
                        resultset.getString("cui"), resultset.getString("birth"), resultset.getString("tipo"),
                        resultset.getBytes("cv"));
            }

            return null;
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
        }

        return null;
    }

    public  Usuario getUsuarioCorreo(String correo) {
        // validateCarnet not null
        try {
            PreparedStatement select = conexion.prepareStatement(SelectUsuarioCorreo);
            select.setString(1, correo);
            ResultSet resultset = select.executeQuery();
            System.out.println("----------------------------------------------------");
            System.out.println(select.toString());

            if (resultset.next()) {
                return new Usuario(resultset.getInt("codigo"),
                        resultset.getString("nombre"), resultset.getString("usuario"),
                        resultset.getString("direccion"), resultset.getString("correo"), "",
                        resultset.getString("cui"), resultset.getString("birth"), resultset.getString("tipo"),
                        resultset.getBytes("cv"));
            }

            return null;
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
        }

        return null;
    }

    

    public boolean cambiarPassword(String codigoUsuario, String password){

    
        System.out.println("Actualizando la password");
    try {
        PreparedStatement update = conexion.prepareStatement(cambiarPassword);
        update.setString(1, util.Encriptar(password));
        update.setString(2, codigoUsuario);
        System.out.println("QueryFinalizacion : " + update.toString());
        int affectedRows = update.executeUpdate();

        if (affectedRows == 1) {
            return true;
        } else {
            System.out.println("La actualización no tuvo éxito.");
            return false;
        }
    }
    catch (SQLException ex

    
        ) {
        ex.printStackTrace();
        System.out.println(ex);
    }


return false;
}
    
     
     
     
}
