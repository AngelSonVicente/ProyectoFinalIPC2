/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class DashBoard {
    private String empleadores;
    private String usuario;

    public DashBoard() {
    }

    public DashBoard(String empleadores, String usuario) {
        this.empleadores = empleadores;
        this.usuario = usuario;
    }

    public String getEmpleadores() {
        return empleadores;
    }

    public void setEmpleadores(String empleadores) {
        this.empleadores = empleadores;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
}
