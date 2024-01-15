/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.SolicitudRetirada;
import Datos.Solicitudes;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author MSI
 */
public class SolicitudesRetiradasBD {

    private Connection conexion;

    public SolicitudesRetiradasBD(Connection conexion) {
        this.conexion = conexion;
    }

    public SolicitudesRetiradasBD() {
        conexion = ConexionBD.getInstancia().getConexion();

    }

    private static String Insert = "INSERT INTO solicitudes_retiradas (codigo_usuario, codigo_oferta, fecha) VALUES (?, ?, ?)";

    public SolicitudRetirada crearSolicitudRetirada(SolicitudRetirada solicitud) {
        System.out.println("esta creando la solicitud");
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato para obtener solo la fecha
        String fecha = fechaActual.format(formato);
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, solicitud.getCodigoUsuario());
            insert.setString(2, solicitud.getCodigoOferta());
            insert.setString(3, fecha);

            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedID = generatedKeys.getString(1);
                    System.out.println("Soli Creada");
                    solicitud.setCodigo(generatedID);
                    return solicitud;
                } else {
                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error: " + ex);
        }

        return null;
    }

}
