/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class HistorialCobros {

    private String codigo;
    private String codigoEmpresa;
    private String codigoOferta;
    private String nombreOferta;
    private float monto;
    private float totalAcumulado;

    public HistorialCobros() {
    }

    public HistorialCobros(String codigo, String codigoEmpresa, String codigoOferta, String nombreOferta, float monto, float totalAcumulado) {
        this.codigo = codigo;
        this.codigoEmpresa = codigoEmpresa;
        this.codigoOferta = codigoOferta;
        this.nombreOferta = nombreOferta;
        this.monto = monto;
        this.totalAcumulado = totalAcumulado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
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

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public float getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(float totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
    }
    
    
    

}
