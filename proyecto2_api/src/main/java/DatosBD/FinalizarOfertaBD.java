/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.FinalizarOferta;
import Datos.Oferta;

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
public class FinalizarOfertaBD {

    LocalDate fechaActual = LocalDate.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato para obtener solo la fecha
    String fecha = fechaActual.format(formato);

    private Connection conexion;

    public FinalizarOfertaBD(Connection conexion) {
        this.conexion = conexion;
    }

    public FinalizarOfertaBD() {
        conexion = ConexionBD.getInstancia().getConexion();

    }

    private static String FinalizarOferta = "UPDATE ofertas set estado = 'Finalizado', usuario_elegido = ? WHERE codigo = ?";
    private static String FinalizarSolicitudes = "UPDATE solicitudes set estado = 'Elegido' WHERE codigo = ?";
    private static String Pago = "INSERT INTO historial_cobros (codigo_empresa, monto, fecha, codigo_oferta) VALUES (?,  (SELECT comision FROM parametros ORDER BY codigo DESC LIMIT 1), ?, ?);";

    public FinalizarOferta realizarPago(FinalizarOferta finalizarOferta) {

        try {
            PreparedStatement insert = conexion.prepareStatement(Pago, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, finalizarOferta.getCodigoEmpresa());
            insert.setString(2, fecha);
            insert.setString(3, finalizarOferta.getCodigoOferta());

            System.out.println(insert.toString());
            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedID = generatedKeys.getString(1);
                    System.out.println("categoria Creada");
                    return finalizarOferta;
                } else {
                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
                }
            }
        } catch (SQLException ex) {

        }

        return null;
    }

    public FinalizarOferta actualizarEstadoOferta(FinalizarOferta finalizarOferta) {
        System.out.println("Actualizando la oferta");
        try {
            PreparedStatement update = conexion.prepareStatement(FinalizarOferta);
            update.setString(1, finalizarOferta.getCodigoUsuarioElegido());

            update.setString(2, finalizarOferta.getCodigoOferta());

            int affectedRows = update.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("Categoría actualizada");
                return finalizarOferta;
            } else {
                System.out.println("La actualización no tuvo éxito.");
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ex);
        }

        return null;
    }

    public FinalizarOferta actualizarEstadoSolicitud(FinalizarOferta finalizarOferta) {
        System.out.println("Actualizando la oferta");
        try {
            PreparedStatement update = conexion.prepareStatement(FinalizarSolicitudes);
            update.setString(1, finalizarOferta.getCodigoSolicitud());

            int affectedRows = update.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("Categoría actualizada");
                return finalizarOferta;
            } else {
                System.out.println("La actualización no tuvo éxito.");
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ex);
        }

        return null;
    }
}
