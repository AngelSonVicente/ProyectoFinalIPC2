package DatosBD;

import Datos.Oferta;
import Datos.OfertaEliminada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private static String SelectTodoEmpresa = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.codigo_empresa = ?";
    private static String SelectTodoEmpresaEstado = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.estado = ? AND o.codigo_empresa = ?";
    private static String SelectOfertaID = "SELECT o.*, (SELECT nombre FROM usuarios WHERE codigo = o.codigo_empresa) AS nombre_empresa, (SELECT nombre FROM usuarios WHERE codigo = o.usuario_elegido) AS nombre_usuario_elegido, (SELECT nombre FROM categorias WHERE codigo = o.categoria) AS nombre_categoria FROM ofertas AS o WHERE o.codigo = ?";

    private static String Insert = "INSERT INTO ofertas (codigo_empresa, nombre, descripcion, categoria, estado, fecha_publicacion, fecha_limite, salario, modalidad, ubicacion, detalles) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String Update = "UPDATE ofertas set nombre = ?, descripcion = ? , categoria = ?, estado = ?, fecha_limite = ?, salario = ?, modalidad = ?, ubicacion = ?, detalles = ? WHERE codigo = ?";
private static String UpdateEstado = "UPDATE ofertas set estado = ? WHERE codigo = ?";
    
    
    public OfertaEliminada EliminarOferta(OfertaEliminada oferta) {
    System.out.println("Actualizando la oferta");
    try {
        PreparedStatement update = conexion.prepareStatement(UpdateEstado);
        update.setString(1, "Eliminado");
        update.setString(2, oferta.getCodigoOferta());
      
        int affectedRows = update.executeUpdate();

        if (affectedRows == 1) {
            System.out.println("Categoría actualizada");
            OfertaEliminadaBD ofertaeliminada = new OfertaEliminadaBD();
           oferta = ofertaeliminada.crearOfertaEliminada(oferta);
            
            return oferta;
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
    
    public Oferta actualizarOferta(Oferta oferta) {
    System.out.println("Actualizando la oferta");
    try {
        PreparedStatement update = conexion.prepareStatement(Update);
  
                update.setString(1, oferta.getNombre());
        update.setString(2, oferta.getDescripcion());
        update.setString(3, oferta.getCategoria());
        update.setString(4, oferta.getEstado());
        update.setString(5, oferta.getFechaLimite());
        update.setFloat(6, oferta.getSalario());
        update.setString(7, oferta.getModadidad());
        update.setString(8, oferta.getUbicacion());
        update.setString(9, oferta.getDetalle());
        
        update.setString(10, oferta.getCodigo());
        
        int affectedRows = update.executeUpdate();

        if (affectedRows == 1) {
            System.out.println("Oferta actualizada");
            return oferta;
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
    public Oferta actualizarEstadoOferta(Oferta oferta) {
    System.out.println("Actualizando la oferta");
    try {
        PreparedStatement update = conexion.prepareStatement(UpdateEstado);
        update.setString(1, oferta.getEstado());
      
        update.setString(2, oferta.getCodigo());


        int affectedRows = update.executeUpdate();

        if (affectedRows == 1) {
            System.out.println("Categoría actualizada");
            return oferta;
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
    public List<Oferta> getOfertasEmpresaEstados(String codigo, String Estado) {
        System.out.println("entramos al getofertas");
        List<Oferta> ofertas = new ArrayList<>();
        try {
            System.out.println(SelectTodoEmpresaEstado);
            PreparedStatement select = conexion.prepareStatement(SelectTodoEmpresaEstado);
            select.setString(1, Estado);
            select.setString(2, codigo);
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
            
            System.out.println("-----------------------------------------------------------------");
            System.out.println(ex);
        }

        return ofertas;
    }

    public Oferta crearOferta(Oferta oferta) {
        System.out.println("esta creando la categoria");
              LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato para obtener solo la fecha
        String fecha= fechaActual.format(formato);
        try {
            PreparedStatement insert = conexion.prepareStatement(Insert, PreparedStatement.RETURN_GENERATED_KEYS);
            insert.setString(1, oferta.getCodigoEmpresa());
            insert.setString(2, oferta.getNombre());
            insert.setString(3, oferta.getDescripcion());
            insert.setString(4, oferta.getCategoria());
            insert.setString(5, "Activo");
            insert.setString(6, fecha);
            insert.setString(7, oferta.getFechaLimite());
            insert.setFloat(8, oferta.getSalario());
            insert.setString(9, oferta.getModadidad());
            insert.setString(10, oferta.getUbicacion());
            insert.setString(11, oferta.getDetalle());

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
