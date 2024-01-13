/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class CambiarPassword {
     
    private String codigoUsuario;
    private String passwordAnterior;
    private String passwordNuevo;

    public CambiarPassword() {
    }

    public CambiarPassword(String codigoUsuario, String passwordAnterior, String passwordNuevo) {
        this.codigoUsuario = codigoUsuario;
        this.passwordAnterior = passwordAnterior;
        this.passwordNuevo = passwordNuevo;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getPasswordAnterior() {
        return passwordAnterior;
    }

    public void setPasswordAnterior(String passwordAnterior) {
        this.passwordAnterior = passwordAnterior;
    }

    public String getPasswordNuevo() {
        return passwordNuevo;
    }

    public void setPasswordNuevo(String passwordNuevo) {
        this.passwordNuevo = passwordNuevo;
    }

    @Override
    public String toString() {
        return "CambiarPassword{" + "codigoUsuario=" + codigoUsuario + ", passwordAnterior=" + passwordAnterior + ", passwordNuevo=" + passwordNuevo + '}';
    }
     
        
    
}
