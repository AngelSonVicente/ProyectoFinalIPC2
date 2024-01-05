/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class RecuperarCuenta {
    
    private String codigo;
    private String correo;
    private String token;
    private String fecha;
    private String hora;
    private String password;

    public RecuperarCuenta(String codigo, String correo, String token, String fecha, String hora, String password) {
        this.codigo = codigo;
        this.correo = correo;
        this.token = token;
        this.fecha = fecha;
        this.hora = hora;
        this.password = password;
    }

    public RecuperarCuenta() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RecuperarCuenta{" + "codigo=" + codigo + ", correo=" + correo + ", token=" + token + ", fecha=" + fecha + ", hora=" + hora + ", password=" + password + '}';
    }
    
     
       
}
