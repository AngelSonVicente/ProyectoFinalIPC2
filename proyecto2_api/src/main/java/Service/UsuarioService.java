/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.DashBoard;
import Datos.Usuario;
import DatosBD.UsuarioBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;

/**
 *
 * @author MSI
 */
public class UsuarioService {

    UsuarioBD usuarioBD = new UsuarioBD();

    public Usuario CrearUsuario(Usuario usuario) throws InvalidDataException {
        validarUsuarioYCorreo(usuario);

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
        if (Correo == null || Correo.isEmpty()) {
            return null;
        }

        return usuarioBD.getUsuarioCorreo(Correo);

    }

    private void validarUsuarioYCorreo(Usuario usuario) throws InvalidDataException {
      
        
        if (usuarioBD.getUsuarioByUser(usuario.getUsuario()) != null || usuarioBD.getUsuarioCorreo(usuario.getCorreo()) != null) {
            throw new InvalidDataException("Ya se encuentra un usuario registrado con el mismo usuario o correo");
        }

    }

}
