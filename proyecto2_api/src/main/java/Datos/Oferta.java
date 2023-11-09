/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author MSI
 */
public class Oferta {
    private String codigo;
    private String codigoEmpresa;
    private String nombreEmpresa; //jalar de usuarios
    private String nombre;
    private String descripcion;
    private String categoria;
    private String nombreCategoria; //jalar de categorias
    private String estado;
    private String fechaPublicacion;
    private String fechaLimite;
    private float  salario;
    private String modadidad;
    private String ubicacion;
    private String detalle;
    private String usuarioElegido;
    private String nombreUsuario; //jalar de usuarios

    public Oferta() {
    }

    public Oferta(String codigo, String codigoEmpresa, String nombreEmpresa, String nombre, String descripcion, String categoria, String nombreCategoria, String estado, String fechaPublicacion, String fechaLimite, float salario, String modadidad, String ubicacion, String detalle, String usuarioElegido, String nombreUsuario) {
        this.codigo = codigo;
        this.codigoEmpresa = codigoEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.nombreCategoria = nombreCategoria;
        this.estado = estado;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaLimite = fechaLimite;
        this.salario = salario;
        this.modadidad = modadidad;
        this.ubicacion = ubicacion;
        this.detalle = detalle;
        this.usuarioElegido = usuarioElegido;
        this.nombreUsuario = nombreUsuario;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getModadidad() {
        return modadidad;
    }

    public void setModadidad(String modadidad) {
        this.modadidad = modadidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getUsuarioElegido() {
        return usuarioElegido;
    }

    public void setUsuarioElegido(String usuarioElegido) {
        this.usuarioElegido = usuarioElegido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    

    
}
