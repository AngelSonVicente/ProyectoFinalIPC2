/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosBD;

import Datos.Categoria;
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
public class GestionCategoriaBD {

    static Connection conexion = ConexionBD.getInstancia().getConexion();
    
    private static String SelectCategorias = "SELECT * FROM categorias";

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

}
