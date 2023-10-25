package Datos;

import java.sql.Connection;
import java.sql.SQLException;

public class SubirArchivoEntrada {

    Connection conexion = ConexionBD.getInstancia().getConexion();
    private void  Subir(String sql){
        System.out.println(sql);

        try (var statement =conexion.prepareStatement(sql)){
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }


    public void Categoria(String codigo,String nombre, String descripcion) {
        Subir("INSERT INTO categoria (codigo, nombre, descripcion) VALUES ('"+codigo+"', '"+nombre+"', '"+descripcion+"')");
    }
    public void Libros(String isbn, String nombre, String costo, String categoria, String autor ) {
        Subir("INSERT INTO libros (isbn, nombre, costo, categoria, autor) VALUES ('"+isbn+"', '"+nombre+"', '"+costo+"', '"+categoria+"', '"+autor+"')");
    }
    public void Bibliotecas(String codigo,String nombre,String direccion) {
        Subir("INSERT INTO bibliotecas (codigo, nombre, direccion) VALUES ('"+codigo+"', '"+nombre+"', '"+direccion+"')");
    }
    public void CatalogoBibliotecas(String biblioteca, int isbn,int existencias) {
        Subir("INSERT INTO catalogo_bibliotecas (biblioteca, isbn, existencias, prestados) VALUES ('"+biblioteca+"', '"+isbn+"', '"+existencias+"', '0')");
    }
    public void Recepcionista(String codigo, String nombre, String usuario, String password, String correo, String biblioteca, String estado) {
        Subir("INSERT INTO usuario_recepcion (codigo, nombre, usuario, password, correo, biblioteca, estado) VALUES ('"+codigo+"','"+nombre+"', '"+usuario+"', '"+password+"', '"+correo+"', '"+biblioteca+"', '"+estado+"')");
    }
    public void Transportista(String codigo, String nombre, String usuario, String password, String correo) {
        Subir("INSERT INTO usuario_transporte (codigo, nombre, usuario, password, correo, estado) VALUES ('"+codigo+"','"+nombre+"', '"+usuario+"', '"+password+"', '"+correo+"', 'Activo')");
    }
    public void UsuarioFinal(String codigo, String nombre, String usuario, String password, String correo, String saldo) {
        Subir("INSERT INTO usuario_final (codigo, nombre, usuario, password, correo, saldo, estado, tipo, libros_prestados) VALUES ('"+codigo+"','"+nombre+"', '"+usuario+"', '"+password+"', '"+correo+"', '"+saldo+"', 'Activo', 'Normal', '0')");
    }
    public void SolicitudPrestamo(String codigo, String biblioteca,String Recepcionista, String usuario, String isbn, String Fecha, String Estado, String TipoEntrega) {
        Subir("INSERT INTO solicitud_prestamo (codigo, biblioteca, recepcionista,usuario, isbn, fecha, estado, tipo_entrega) " +
                "VALUES ('"+codigo+"','"+biblioteca+"','"+Recepcionista+"', '"+usuario+"', '"+isbn+"', '"+Fecha+"', '"+Estado+"', '"+TipoEntrega+"')");
    }
    public void Prestamos(String codigo, String Biblioteca, String Recepcionista, String usuario, String isbn, String Fecha, String FechaDevolucion, String estdo, String multa) {
        Subir("INSERT INTO prestamos (codigo, biblioteca, recepcionista, usuario, isbn, fecha, fecha_devolucion, estado, multa) " +
                "VALUES ('"+codigo+"','"+Biblioteca+"', '"+Recepcionista+"', '"+usuario+"', '"+isbn+"', '"+Fecha+"', '"+FechaDevolucion+"', '"+estdo+"', '"+multa+"')");
    }
    public void TransporteBibliotecas(String codigo, String BibliotecaOrigen, String BibliotecaDestino, String recepcionista, String transportista,String Fecha, String Estado) {
        Subir("INSERT INTO transporte_bibliotecas (codigo, biblioteca_origen, biblioteca_destino, recepcionista, transportista, fecha, estado) " +
                "VALUES ('"+codigo+"','"+BibliotecaOrigen+"', '"+BibliotecaDestino+"', '"+recepcionista+"', '"+transportista+"', '"+Fecha+"', '"+Estado+"')");
    }
    public void DetallesTeb(String codigo, String isbn, String unidades) {
        Subir("INSERT INTO detalles_teb (codigo, isbn, unidades) " +
                "VALUES ('"+codigo+"', '"+isbn+"', '"+unidades+"')");
    }
    public void TransporteUsuarios(String codigo,String biblioteca, String usuario, String transportista, String isbn, String Fecha,String Estado) {
         Subir("INSERT INTO transportes_usuarios (codigo, biblioteca, usuario, transportista, isbn, fecha, estado) " +
                "VALUES ('"+codigo+"','"+biblioteca+"', '"+usuario+"', '"+transportista+"', '"+isbn+"', '"+Fecha+"', '"+Estado+"')");
    }
    public void Incidencias( String codigo,String prestamo, String tipo, String costo, String estado, String detalles, String fecha) {
        Subir("INSERT INTO incidencia (codigo, prestamo, tipo, costo, estado, detalles, fecha) " +
                "VALUES ('"+codigo+"','"+prestamo+"', '"+tipo+"', '"+costo+"', '"+estado+"', '"+detalles+"', '"+fecha+"')");
    }
    public void SolicitudRevocacion(String codigo, String usuario, String detalle, String estado) {
        Subir("INSERT INTO solicitud_revocacion (codigo, usuario, detalle, estado) " +
                "VALUES ('"+codigo+"','"+usuario+"', '"+detalle+"', '"+estado+"')");

    }

}
