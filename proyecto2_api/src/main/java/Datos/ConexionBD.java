package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {


    private static ConexionBD instancia;
    private static Connection conexion = null;
    private final String url = "jdbc:mysql://localhost:3306/biblioteca2";
    private final String usuario = "root";
    private final String contrasena = "ASV30885";

    private ConexionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conectado a la base de datos");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar a la base de datos");
        }
    }

    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexion cerrada");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la Conexion");
            }
        }
    }







}
