/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class Notificaciones {
    
    private String codigo;
    private String codigoUsuario;
    private String nombreUsuario;
    private String codigoUsuarioDestino;
    private String nombreUsuarioDestino;
    private String codigoOferta;
    private String nombreOferta;
    private String contenido;
    private String fecha;
    private String estado;

    public Notificaciones() {
    }

    public Notificaciones(String codigo, String codigoUsuario, String nombreUsuario, String codigoUsuarioDestino, String nombreUsuarioDestino, String codigoOferta, String nombreOferta, String contenido, String fecha, String estado) {
        this.codigo = codigo;
        this.codigoUsuario = codigoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.codigoUsuarioDestino = codigoUsuarioDestino;
        this.nombreUsuarioDestino = nombreUsuarioDestino;
        this.codigoOferta = codigoOferta;
        this.nombreOferta = nombreOferta;
        this.contenido = contenido;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getCodigoUsuarioDestino() {
        return codigoUsuarioDestino;
    }

    public void setCodigoUsuarioDestino(String codigoUsuarioDestino) {
        this.codigoUsuarioDestino = codigoUsuarioDestino;
    }

    public String getNombreUsuarioDestino() {
        return nombreUsuarioDestino;
    }

    public void setNombreUsuarioDestino(String nombreUsuarioDestino) {
        this.nombreUsuarioDestino = nombreUsuarioDestino;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Notificaciones{" + "codigo=" + codigo + ", codigoUsuario=" + codigoUsuario + ", nombreUsuario=" + nombreUsuario + ", codigoUsuarioDestino=" + codigoUsuarioDestino + ", nombreUsuarioDestino=" + nombreUsuarioDestino + ", codigoOferta=" + codigoOferta + ", nombreOferta=" + nombreOferta + ", contenido=" + contenido + ", fecha=" + fecha + ", estado=" + estado + '}';
    }
    
    
    

    
    
}
