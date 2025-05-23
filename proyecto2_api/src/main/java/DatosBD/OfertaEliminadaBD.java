/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Oferta;
import Datos.OfertaEliminada;

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
public class OfertaEliminadaBD {
     private Connection conexion ;

    public OfertaEliminadaBD(Connection conexion) {
        this.conexion = conexion;
    }

    public OfertaEliminadaBD() {
        conexion = ConexionBD.getInstancia().getConexion();

    }
     
      LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato para obtener solo la fecha
        String fecha = fechaActual.format(formato);
      
    
     private static String Insert = "INSERT INTO ofertas_eliminadas (codigo_oferta, motivo, fecha) VALUES (?, ?, ?)";
   
    
        public OfertaEliminada crearOfertaEliminada(OfertaEliminada oferta) {
            
            System.out.println("crear oferta eliminada: "+ oferta.toString());
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, oferta.getCodigoOferta());
            insert.setString(2, oferta.getMotivo());
            insert.setString(3, fecha);
          

            System.out.println(insert.toString());
            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedID = generatedKeys.getString(1);
                    System.out.println("categoria Creada");
                    oferta.setCodigo(generatedID);
                    return oferta;
                } else {
                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    
}
