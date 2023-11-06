/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Categoria;
import Datos.Comision;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ComisionBD {
    
        static Connection conexion = ConexionBD.getInstancia().getConexion();

    private static String SelectTodo = "SELECT * FROM parametros";
    private static String SelectUltimo = "SELECT * FROM parametros ORDER BY codigo DESC LIMIT 1";
    private static String Insert = "INSERT INTO parametros (comision, fecha) VALUES (?, ?)";
   
    public List<Comision> getHistorialComisiones() {
        List<Comision> comisiones = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(SelectTodo);

            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                comisiones.add(new Comision(resultset.getString("codigo"),
                        resultset.getFloat("comision"),
                        resultset.getString("fecha"))
                );

            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
        }

        return comisiones;
    }

    public Comision CrearComision(Comision comision) {
        System.out.println("esta creando la categoria");
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setFloat(1, comision.getComision());
            insert.setString(2, comision.getFecha());

            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedID = generatedKeys.getString(1);
                    System.out.println("categoria Creada");
                    comision.setCodigo(generatedID);
                    return comision;
                } else {
                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Comision getComision() {
        try {
            PreparedStatement select = conexion.prepareStatement(SelectUltimo);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return new Comision(resultset.getString("codigo"),
                        resultset.getFloat("comision"),
                        resultset.getString("fecha"));
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
        }

    return null;
    }
    

    
}
