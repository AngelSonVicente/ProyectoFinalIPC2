/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.util.List;

/**
 *
 * @author MSI
 */
public class Empresa {

    private String codigo;
    private String codigoEmpresa;
    private String nombreEmpresa;
    private String mision;
    private String vision;
    private String titularTarjeta;
    private String numeroTarjeta;
    private String codigoSeguridad;
    private List<String> telefonos;

    public Empresa() {
    }

    public Empresa(String codigo, String codigoEmpresa, String nombreEmpresa, String mision, String vision, String titularTarjeta, String numeroTarjeta, String codigoSeguridad, List<String> telefonos) {
        this.codigo = codigo;
        this.codigoEmpresa = codigoEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.mision = mision;
        this.vision = vision;
        this.titularTarjeta = titularTarjeta;
        this.numeroTarjeta = numeroTarjeta;
        this.codigoSeguridad = codigoSeguridad;
        this.telefonos = telefonos;
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

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getTitularTarjeta() {
        return titularTarjeta;
    }

    public void setTitularTarjeta(String titularTarjeta) {
        this.titularTarjeta = titularTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    @Override
    public String toString() {
        return "Empresa{" + "codigo=" + codigo + ", codigoEmpresa=" + codigoEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", mision=" + mision + ", vision=" + vision + ", titularTarjeta=" + titularTarjeta + ", numeroTarjeta=" + numeroTarjeta + ", codigoSeguridad=" + codigoSeguridad + ", telefonos=" + telefonos + '}';
    }

   
    
    
    
    
}
