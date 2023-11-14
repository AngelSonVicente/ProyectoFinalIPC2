/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class FinalizarOferta {
    private String codigoOferta;
    private String codigoUsuarioElegido;
    private String codigoSolicitud;
    private String codigoEmpresa;

    public FinalizarOferta() {
    }

    public FinalizarOferta(String codigoOferta, String codigoUsuarioElegido, String codigoSolicitud, String codigoEmpresa) {
        this.codigoOferta = codigoOferta;
        this.codigoUsuarioElegido = codigoUsuarioElegido;
        this.codigoSolicitud = codigoSolicitud;
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getCodigoOferta() {
        return codigoOferta;
    }

    public void setCodigoOferta(String codigoOferta) {
        this.codigoOferta = codigoOferta;
    }

    public String getCodigoUsuarioElegido() {
        return codigoUsuarioElegido;
    }

    public void setCodigoUsuarioElegido(String codigoUsuarioElegido) {
        this.codigoUsuarioElegido = codigoUsuarioElegido;
    }

    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }
    
    
    
}
