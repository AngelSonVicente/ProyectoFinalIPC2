/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.RecuperarCuenta;
import Datos.Solicitudes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class TokenBD {

    private static String Insert = "INSERT INTO tokens (codigoUsuario, token, fecha, hora) VALUES (?, ?, ?, ?)";
    private static String Select = "SELECT * FROM tokens WHERE token = ?";
    static Connection conexion = ConexionBD.getInstancia().getConexion();

    public RecuperarCuenta CrearToken(RecuperarCuenta cuenta) {
        System.out.println("esta creando la solicitud");
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, cuenta.getCodigo());
            insert.setString(2, cuenta.getToken());
            insert.setString(3, cuenta.getFecha());
            insert.setString(4, cuenta.getHora());

            int affectedRows = insert.executeUpdate();

            if (affectedRows == 1) {

            } else {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error: " + ex);
        }

        return null;
    }

    public RecuperarCuenta getInformacionToken(RecuperarCuenta cuenta) {
        try {
            PreparedStatement select = conexion.prepareStatement(Select);
            select.setString(1, cuenta.getToken());
            ResultSet resultset = select.executeQuery();
            System.out.println("Query: " + select.toString());

            if (resultset.next()) {
                return new RecuperarCuenta(resultset.getString("codigoUsuario"), null,
                        resultset.getString("token"), resultset.getString("fecha"), resultset.getString("hora"),
                        null);
            }
        } catch (SQLException ex) {

            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return null;
    }

}
