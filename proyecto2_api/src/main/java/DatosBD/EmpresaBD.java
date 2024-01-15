/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Categoria;
import Datos.Empresa;
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
 private static String UpdateEmpresa = "UPDATE empresa set mision = ?, vision = ? , titular_tarjeta = ?, no_tarjeta = ?, codigo_seguridad = ? WHERE cod_usuario = ?";
 private static String UpdateMisionVision = "UPDATE empresa set mision = ?, vision = ? WHERE cod_usuario = ?";
      
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
        
        
          public boolean actualizarMisionVisionEmpresa(Empresa empresa) {
        System.out.println("Actualizando la oferta");
        try {
            PreparedStatement update = conexion.prepareStatement(UpdateMisionVision);
            update.setString(1, empresa.getMision());

            update.setString(2, empresa.getVision());
            update.setString(3, empresa.getCodigoEmpresa());

            int affectedRows = update.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("mision vision actualizada");
                return true;
            } else {
                System.out.println("La actualización no tuvo éxito.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ex);
        }

        return true;
    }
        
          public boolean actualizarEmpresa(Empresa empresa) {
        System.out.println("Actualizando la oferta");
        try {
            PreparedStatement update = conexion.prepareStatement(UpdateEmpresa);
            update.setString(1, empresa.getMision());
            update.setString(2, empresa.getVision());
            update.setString(3, empresa.getTitularTarjeta());
            update.setString(4, empresa.getNumeroTarjeta());
            update.setString(5, empresa.getCodigoSeguridad());
            update.setString(6, empresa.getCodigoEmpresa());

            int affectedRows = update.executeUpdate();

            if (affectedRows == 1) {
                System.out.println("mision vision actualizada");
                return true;
            } else {
                System.out.println("La actualización no tuvo éxito.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ex);
        }

        return true;
    }
    
}
