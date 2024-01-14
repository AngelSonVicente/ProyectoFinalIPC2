/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class OfertaEliminada {
    private String codigo;
    private String codigoOferta;
    private String nombreOferta;
    private String motivo;
    private String fecha;
    

    public OfertaEliminada() {
    }

    public OfertaEliminada(String codigo, String codigoOferta, String nombreOferta, String motivo, String fecha) {
        this.codigo = codigo;
        this.codigoOferta = codigoOferta;
        this.nombreOferta = nombreOferta;
        this.motivo = motivo;
        this.fecha = fecha;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "OfertaEliminada{" + "codigo=" + codigo + ", codigoOferta=" + codigoOferta + ", nombreOferta=" + nombreOferta + ", motivo=" + motivo + ", fecha=" + fecha + '}';
    }
    
    
    
}
