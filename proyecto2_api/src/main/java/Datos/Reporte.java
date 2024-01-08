/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class Reporte {

    private String tipo;
    private String reporte;
    private String codigo;
    private String fecha1;
    private String fecha2;

    public Reporte() {
    }

    public Reporte(String tipo, String reporte, String codigo, String fecha1, String fecha2) {
        this.tipo = tipo;
        this.reporte = reporte;
        this.codigo = codigo;
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    @Override
    public String toString() {
        return "Reporte{" + "tipo=" + tipo + ", reporte=" + reporte + ", codigo=" + codigo + ", fecha1=" + fecha1 + ", fecha2=" + fecha2 + '}';
    }

     
    
    
}
