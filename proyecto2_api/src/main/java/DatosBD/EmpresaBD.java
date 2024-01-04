/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Categoria;
import Datos.Empresa;
import static DatosBD.GestionCategoriaBD.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class EmpresaBD {
     private Connection conexion;

    public EmpresaBD(Connection conexion) {
        this.conexion = conexion;
    }

     private static String SelectEmpresaID = "SELECT e.*, u.nombre AS nombre_usuario FROM empresa AS e JOIN usuarios AS u ON e.cod_usuario = u.codigo WHERE e.cod_usuario = ?";
     private static String Insert = "INSERT INTO empresa (cod_usuario, mision, vision, titular_tarjeta, no_tarjeta, codigo_seguridad) VALUES (?, ?, ?, ?, ?, ?)";
   //  static Connection conexion = ConexionBD.getInstancia().getConexion();

    
        public Empresa getEmpresa(String codigo) {
        try {
            PreparedStatement select = conexion.prepareStatement(SelectEmpresaID);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return new Empresa(resultset.getString("codigo"), resultset.getString("cod_usuario"),
                        resultset.getString("nombre_usuario"), resultset.getString("mision"),
                        resultset.getString("vision"), resultset.getString("titular_tarjeta"),resultset.getString("no_tarjeta"),
                        null,null
                
                );
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            
            System.out.println("Error:  "+ex);
        }

    return null;
    }
 
          public Empresa crearEmpresa(Empresa empresa) {
        System.out.println("esta creando la Empresa");
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, empresa.getCodigoEmpresa());
            insert.setString(2, empresa.getMision());
            insert.setString(3, empresa.getVision());
            insert.setString(4, empresa.getTitularTarjeta());
            insert.setString(5, empresa.getNumeroTarjeta());
            insert.setString(6, empresa.getCodigoSeguridad());

            int affectedRows = insert.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    System.out.println("categoria Creada");
                    return empresa;
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
