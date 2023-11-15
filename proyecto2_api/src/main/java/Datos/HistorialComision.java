/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class HistorialComision {
    private String codigo;
    private float comision;
    private String fechaInial;
    private String fechaFinal;

    public HistorialComision() {
    }

    public HistorialComision(String codigo, float comision, String fechaInial, String fechaFinal) {
        this.codigo = codigo;
        this.comision = comision;
        this.fechaInial = fechaInial;
        this.fechaFinal = fechaFinal;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public String getFechaInial() {
        return fechaInial;
    }

    public void setFechaInial(String fechaInial) {
        this.fechaInial = fechaInial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
    
    
}
