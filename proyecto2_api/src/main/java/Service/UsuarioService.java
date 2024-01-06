/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.DashBoard;
import Datos.Usuario;
import DatosBD.UsuarioBD;
import exceptions.NotFoundException;

/**
 *
 * @author MSI
 */
public class UsuarioService {

    UsuarioBD usuarioBD = new UsuarioBD();

    public Usuario CrearUsuario(Usuario usuario) {
        return usuarioBD.crearUsuario(usuario);
    }
    
    public boolean cambiarPassword(String codigoUsuario, String password) {
        
        return usuarioBD.cambiarPassword(codigoUsuario, password);
    
    }

    public DashBoard getDasg() {
        return usuarioBD.getDash();
    }

    public Usuario getUsuarioID(String codigo) throws NotFoundException {

        Usuario usuario = usuarioBD.getUsuarioCodigo(codigo);
        if (usuario != null) {
            return usuario;
        } else {

            throw new NotFoundException("No se encontró el Usuario");
        }
    }

    public Usuario getUsuarioCorreo(String Correo) {
        if (Correo==null || Correo.isEmpty()) {
        return null;
        }

     return usuarioBD.getUsuarioCorreo(Correo);
     
    }

}
