/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Datos.DashBoard;
import Datos.JsonUtil;
import Datos.Usuario;
import Service.UsuarioService;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author MSI
 */
public class UsuarioController {

    JsonUtil jsonUtil = new JsonUtil();

    public void CrearUsurio(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        Usuario user = (Usuario) jsonUtil.JsonStringAObjeto(body, Usuario.class);
        UsuarioService usuarioService = new UsuarioService();

        Usuario usuarioCreado;
        usuarioCreado = usuarioService.CrearUsuario(user);
        jsonUtil.EnviarJson(response, usuarioCreado);

    }

    public void GetUsuario(String codigo, String dash, HttpServletResponse response) throws IOException, NotFoundException {

        UsuarioService usuarioService = new UsuarioService();

        if (dash != null) {

            DashBoard dashito = usuarioService.getDasg();
            jsonUtil.EnviarJson(response, dashito);

        } else {

            Usuario usuario = usuarioService.getUsuarioID(codigo);
            jsonUtil.EnviarJson(response, usuario);

        }

    }
}
