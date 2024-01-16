/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class Filtro {
    
    private String categoria;
    private String salario;
    private String modalidad;
    private String ubicacion;
    private String nombre;

    public Filtro() {
    }

    public Filtro(String categoria, String salario, String modalidad, String ubicacion, String nombre) {
        this.categoria = categoria;
        this.salario = salario;
        this.modalidad = modalidad;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Filtro{" + "categoria=" + categoria + ", salario=" + salario + ", modalidad=" + modalidad + ", ubicacion=" + ubicacion + ", nombre=" + nombre + '}';
    }
    
    
    
    
}
