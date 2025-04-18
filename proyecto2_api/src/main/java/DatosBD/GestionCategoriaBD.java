
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

    private Connection conexion ;

    public GestionCategoriaBD(Connection conexion) {
        this.conexion = conexion;
    }

    public GestionCategoriaBD() {
        conexion = ConexionBD.getInstancia().getConexion();

    }
    
    private static String SelectCategorias = "SELECT * FROM categorias";
    private static String SelectCategoriasID = "SELECT * FROM categorias WHERE codigo = ?";
    private static String ExisteNombre = "SELECT * FROM categorias WHERE nombre = ?";
    private static String Insert = "INSERT INTO categorias (codigo, nombre, decripcion) VALUES (?, ?, ?)";
    private static String Update = "UPDATE categorias set nombre = ?, decripcion = ? WHERE codigo = ?";

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
            System.out.println("Error:  "+ex);
        }

        return categorias;
    }

    public Categoria CrearCategoria(Categoria categoria) {
        System.out.println("esta creando la categoria");
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setInt(1, categoria.getCodigo());
            insert.setString(2, categoria.getNombre());
            insert.setString(3, categoria.getDescripcion());

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

    
    public boolean ExiteCategoria(String nombre) {
        try {
            PreparedStatement select = conexion.prepareStatement(ExisteNombre);
            select.setString(1, nombre);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return true;
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            
            System.out.println("Error:  "+ex);
        }

    return false;
    }
    public Categoria getCategoria(String codigo) {
        try {
            PreparedStatement select = conexion.prepareStatement(SelectCategoriasID);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return new Categoria(resultset.getInt("codigo"),
                        resultset.getString("nombre"),
                        resultset.getString("decripcion"));
            }
        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();
            
            System.out.println("Error:  "+ex);
        }

    return null;
    }
    
    
    
    public Categoria actualizarCategoria(Categoria categoria) {
    System.out.println("Actualizando la categoría");
    try {
        PreparedStatement update = conexion.prepareStatement(Update);
        update.setString(1, categoria.getNombre());
        update.setString(2, categoria.getDescripcion());
        update.setInt(3, categoria.getCodigo());

        int affectedRows = update.executeUpdate();

        if (affectedRows == 1) {
            System.out.println("Categoría actualizada");
            return categoria;
        } else {
            System.out.println("La actualización no tuvo éxito.");
            return null;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println(ex);
    }

    return null;
}

    
    

}
