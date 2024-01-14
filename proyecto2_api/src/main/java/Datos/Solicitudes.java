
package Datos;

/**
 *
 * @author MSI
 */
public class Solicitudes {
    
    private String codigo;
    private String codigoOferta;
    private String nombreOferta;
    private String codigoUsuario;
    private String nombreUsuario;
    private String mensaje;
    private String estado;

    public Solicitudes() {
    }

    public Solicitudes(String codigo, String codigoOferta, String nombreOferta, String codigoUsuario, String nombreUsuario, String mensaje, String estado) {
        this.codigo = codigo;
        this.codigoOferta = codigoOferta;
        this.nombreOferta = nombreOferta;
        this.codigoUsuario = codigoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoOferta() {
        return codigoOferta;
    }

    public void setCodigoOferta(String codigoOferta) {
        this.codigoOferta = codigoOferta;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Solicitudes{" + "codigo=" + codigo + ", codigoOferta=" + codigoOferta + ", nombreOferta=" + nombreOferta + ", codigoUsuario=" + codigoUsuario + ", nombreUsuario=" + nombreUsuario + ", mensaje=" + mensaje + ", estado=" + estado + '}';
    }

    
    
}
