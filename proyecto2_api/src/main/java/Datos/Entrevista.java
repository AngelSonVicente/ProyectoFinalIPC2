/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class Entrevista {
    private String codigo;
    private String codigoOferta;
    private String nombreOferta;
    private String codigoUsuario;
    private String nombreUsuario;
    private String fecha;
    private String hora;
    private String ubicacion;
    private String estado;
    private String nota;
    private String codigoSolicitud;

    public Entrevista() {
    }

    public Entrevista(String codigo, String codigoOferta, String nombreOferta, String codigoUsuario, String nombreUsuario, String fecha, String hora, String ubicacion, String estado, String nota, String codigoSolicitud) {
        this.codigo = codigo;
        this.codigoOferta = codigoOferta;
        this.nombreOferta = nombreOferta;
        this.codigoUsuario = codigoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.nota = nota;
        this.codigoSolicitud = codigoSolicitud;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    @Override
    public String toString() {
        return "Entrevista{" + "codigo=" + codigo + ", codigoOferta=" + codigoOferta + ", nombreOferta=" + nombreOferta + ", codigoUsuario=" + codigoUsuario + ", nombreUsuario=" + nombreUsuario + ", fecha=" + fecha + ", hora=" + hora + ", ubicacion=" + ubicacion + ", estado=" + estado + ", nota=" + nota + ", codigoSolicitud=" + codigoSolicitud + '}';
    }
    
    

    
}
