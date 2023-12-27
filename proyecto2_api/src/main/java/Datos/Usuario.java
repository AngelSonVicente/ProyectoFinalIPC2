package Datos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MSI
 */
public class Usuario {

    private int codigo;
    private String nombre;
    private String usuario;
    private String direccion;
    private String correo;
    private String password;
    private String cui;
    private String birth;
    private String tipo;
    private byte[] cv;

    public Usuario() {
    }

    public Usuario(int codigo, String nombre, String usuario, String direccion, String correo, String password, String cui, String birth, String tipo, byte[] cv) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.direccion = direccion;
        this.correo = correo;
        this.password = password;
        this.cui = cui;
        this.birth = birth;
        this.tipo = tipo;
        this.cv = cv;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + ", nombre=" + nombre + ", usuario=" + usuario + ", direccion=" + direccion + ", correo=" + correo + ", password=" + password + ", cui=" + cui + ", birth=" + birth + ", tipo=" + tipo + ", cv=" + cv + '}';
    }
    
    

    

}
