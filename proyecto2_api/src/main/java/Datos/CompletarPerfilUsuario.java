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
public class CompletarPerfilUsuario {
      private String codigoUsuario;
    private List<String> telefonos;
    private List<String> categorias;

    public CompletarPerfilUsuario() {
    }

    public CompletarPerfilUsuario(String codigoUsuario, List<String> telefonos, List<String> categorias) {
        this.codigoUsuario = codigoUsuario;
        this.telefonos = telefonos;
        this.categorias = categorias;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "CompletarPerfilUsuario{" + "codigoUsuario=" + codigoUsuario + ", telefonos=" + telefonos + ", categorias=" + categorias + '}';
    }


    
    
    
}
