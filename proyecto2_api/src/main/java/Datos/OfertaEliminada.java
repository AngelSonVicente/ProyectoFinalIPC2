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
    private String motivo;

    public OfertaEliminada() {
    }

    public OfertaEliminada(String codigo, String codigoOferta, String motivo) {
        this.codigo = codigo;
        this.codigoOferta = codigoOferta;
        this.motivo = motivo;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    
}
