/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.DashBoard;
import Datos.JsonUtil;
import Datos.Usuario;
import DatosBD.UsuarioBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author MSI
 */
public class UsuarioService {
    
    JsonUtil jsonUtil= new JsonUtil();

    UsuarioBD usuarioBD = new UsuarioBD();
    
    public Usuario ActualizarUsuario(String body, HttpServletResponse response) throws InvalidDataException, NotFoundException, IOException{
        Usuario usuario = (Usuario) jsonUtil.JsonStringAObjeto(body, Usuario.class);
        
        
        //si se actuliazó el usuario devolvemos el usuario ya con la informacion actualizada
        if(ActualizarUsuarioBD(usuario)){
            
            Usuario usuarioActualizado = getUsuarioID(String.valueOf(usuario.getCodigo()));
            jsonUtil.EnviarJson(response, usuarioActualizado);
            
        }
    
        
        return usuario;
    }

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

    private boolean ActualizarUsuarioBD(Usuario usuario) throws InvalidDataException {

        if (usuario.getNombre() == null || usuario.getDireccion() == null
                || usuario.getCorreo() == null || usuario.getCui() == null || usuario.getBirth() == null
                || usuario.getNombre().isEmpty() || usuario.getDireccion().isEmpty()
                || usuario.getCorreo().isEmpty() || usuario.getCui().isEmpty() || usuario.getBirth().isEmpty()) {
            throw new InvalidDataException("Faltan Datos");
        }

        Usuario usuarioCorreo = getUsuarioCorreo(usuario.getCorreo());

        //validamos que no haya otro usuario con el mismo correo
        if (usuarioCorreo != null) {
            //lanzamos excepcion solo si el correo no pertenece al usuario que está actualizando su informacion
            //ya que podria dejar su mismo correo
            if (usuarioCorreo.getCodigo() != usuario.getCodigo()) {

                throw new InvalidDataException("EL Correo Ya esta en uso");
            }
        }

        return usuarioBD.ActualizarUsuario(usuario);

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
