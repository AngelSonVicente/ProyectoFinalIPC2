/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Categoria;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class GestionCategoriaBD {

    static Connection conexion = ConexionBD.getInstancia().getConexion();
    
    private static String SelectCategorias = "SELECT * FROM categorias";
    private static String Insert = "INSERT INTO categorias (nombre, decripcion) VALUES (?, ?)";

    public List<Categoria> getCategorias() {
            List<Categoria> categorias = new ArrayList<>();
        try {
            PreparedStatement select = conexion.prepareStatement(SelectCategorias);

            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                categorias.add(new Categoria(resultset.getInt("codigo"),
                        resultset.getString("nombre"),
                        resultset.getString("decripcion"))
                );
                
                
            }
            
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
        }
        
        return categorias;
    }
    public Categoria CrearCategoria(Categoria categoria) {
        System.out.println("esta creando la categoria");
    try {
   PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
     insert.setString(1, categoria.getNombre());
        insert.setString(2, categoria.getDescripcion());

        int affectedRows = insert.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
        }

        try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1); 
                System.out.println("categoria Creada");
                categoria.setCodigo(generatedID); 
                return categoria; 
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
