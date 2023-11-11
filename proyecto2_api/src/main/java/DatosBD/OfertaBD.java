package DatosBD;

import Datos.Oferta;
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
public class OfertaBD {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    //agregar fltro de las preferencias del usuario de momento todo
    private static String SelectTodo = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.estado = 'Activo'";
    private static String SelectTodoEmpresa = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.estado = 'Activo' AND o.codigo_empresa = ?";
    private static String SelectOfertaID = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.codigo = ?";

    private static String Insert = "INSERT INTO ofertas (codigo_empresa, nombre, descripcion, categoria, estado, fecha_publicacion, fecha_limite, salario, modalidad, ubicacion, detalles) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String Update = "UPDATE categorias set nombre = ?, decripcion = ? WHERE codigo = ?";

    public List<Oferta> getOfertas() {
        System.out.println("entramos al getofertas");
        List<Oferta> ofertas = new ArrayList<>();
        try {
            System.out.println("Entramos al try del getofertas");
            PreparedStatement select = conexion.prepareStatement(SelectTodo);

            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                ofertas.add(new Oferta(resultset.getString("codigo"), resultset.getString("codigo_empresa"),
                        resultset.getString("nombre_empresa"), resultset.getString("nombre"), resultset.getString("descripcion"),
                        resultset.getString("categoria"), resultset.getString("nombre_categoria"), resultset.getString("estado"),
                        resultset.getString("fecha_publicacion"), resultset.getString("fecha_limite"), resultset.getFloat("salario"),
                        resultset.getString("modalidad"), resultset.getString("ubicacion"), resultset.getString("detalles"),
                        resultset.getString("usuario_elegido"), resultset.getString("nombre_usuario_elegido")
                ));

                System.out.println("codigo:   " + resultset.getString("codigo"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return ofertas;
    }

    public List<Oferta> getOfertasEmpresa(String codigo) {
        System.out.println("entramos al getofertas");
        List<Oferta> ofertas = new ArrayList<>();
        try {
            System.out.println(SelectTodoEmpresa);
            PreparedStatement select = conexion.prepareStatement(SelectTodoEmpresa);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();
            while (resultset.next()) {
                ofertas.add(new Oferta(resultset.getString("codigo"), resultset.getString("codigo_empresa"),
                        resultset.getString("nombre_empresa"), resultset.getString("nombre"), resultset.getString("descripcion"),
                        resultset.getString("categoria"), resultset.getString("nombre_categoria"), resultset.getString("estado"),
                        resultset.getString("fecha_publicacion"), resultset.getString("fecha_limite"), resultset.getFloat("salario"),
                        resultset.getString("modalidad"), resultset.getString("ubicacion"), resultset.getString("detalles"),
                        resultset.getString("usuario_elegido"), resultset.getString("nombre_usuario_elegido")
                ));

                System.out.println("codigo:   " + resultset.getString("codigo"));
            }

        } catch (SQLException ex) {
            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return ofertas;
    }
//
//    public Categoria CrearCategoria(Categoria categoria) {
//        System.out.println("esta creando la categoria");
//        try {
//            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
//            insert.setString(1, categoria.getNombre());
//            insert.setString(2, categoria.getDescripcion());
//
//            int affectedRows = insert.executeUpdate();
//
//            if (affectedRows == 0) {
//                throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
//            }
//
//            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    int generatedID = generatedKeys.getInt(1);
//                    System.out.println("categoria Creada");
//                    categoria.setCodigo(generatedID);
//                    return categoria;
//                } else {
//                    throw new SQLException("La inserción no tuvo éxito, ningún ID generado.");
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return null;
//    }

    public Oferta getOferta(String codigo) {
        try {
            PreparedStatement select = conexion.prepareStatement(SelectOfertaID);
            select.setString(1, codigo);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return new Oferta(resultset.getString("codigo"), resultset.getString("codigo_empresa"),
                        resultset.getString("nombre_empresa"), resultset.getString("nombre"), resultset.getString("descripcion"),
                        resultset.getString("categoria"), resultset.getString("nombre_categoria"), resultset.getString("estado"),
                        resultset.getString("fecha_publicacion"), resultset.getString("fecha_limite"), resultset.getFloat("salario"),
                        resultset.getString("modalidad"), resultset.getString("ubicacion"), resultset.getString("detalles"),
                        resultset.getString("usuario_elegido"), resultset.getString("nombre_usuario_elegido")
                );
            }
        } catch (SQLException ex) {

            // TODO pendiente manejo
            ex.printStackTrace();

            System.out.println(ex);
        }

        return null;
    }

}
