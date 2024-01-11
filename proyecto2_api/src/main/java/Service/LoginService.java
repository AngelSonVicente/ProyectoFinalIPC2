package Service;

import Datos.JsonUtil;
import Datos.TipoUsuario;
import DatosBD.ConexionBD;
import Datos.Usuario;
import Datos.Util;
import DatosBD.UsuarioBD;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LoginService {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    private InvitadoService invitadoService = new InvitadoService();
    private JsonUtil jsonUtil = new JsonUtil();

    public void procesarSolicitud(String body, HttpServletResponse response) throws IOException {

        Usuario UsuarioFE = (Usuario) jsonUtil.JsonStringAObjeto(body, Usuario.class);
        System.out.println("usuario_ " + UsuarioFE);
        System.out.println("Tipo de usuario: " + UsuarioFE.getTipo());
        System.out.println("usuario de usuario: " + UsuarioFE.getUsuario());
        System.out.println("contra de usuario: " + UsuarioFE.getPassword());
    
        Usuario usuario = new Usuario();
        if (UsuarioFE.getTipo() == null) {

            usuario = IsLogin(UsuarioFE.getPassword(), UsuarioFE.getUsuario());
            if (usuario != null) {
                jsonUtil.EnviarJson(response, usuario);

                response.setStatus(HttpServletResponse.SC_OK);

            } else {

                response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            }

        } else {
            if (UsuarioFE.getTipo().equals(TipoUsuario.Invitado.name())) {
                System.out.println("Enviando Usuario Invitado");
                UsuarioFE.setNombre(TipoUsuario.Invitado.name());
                
                //subir al servidor un nuevo invitado
                invitadoService.AgregarInvitado();
                jsonUtil.EnviarJson(response, UsuarioFE);
                response.setStatus(HttpServletResponse.SC_OK);

            }
        }

    }

    Util util = new Util();

    private static final String SelectPassword = "SELECT password FROM usuarios WHERE usuario = ?";

    public Usuario IsLogin(String ContraIngresada, String UsuarioIngresado) {

        String ContraEncriptada = util.Encriptar(ContraIngresada);

        String Contra = obtnerContra(UsuarioIngresado);
        Usuario usuario = new Usuario();

        System.out.println("contra ingresada: " + ContraEncriptada);
        System.out.println("usuario ingresado: " + UsuarioIngresado);
        System.out.println("contra : " + Contra);
        

        if (ContraEncriptada.equals(Contra)) {
            System.out.println("si ingresó");

            usuario = UsuarioBD.getUsuarioByUser(UsuarioIngresado);
          
            return usuario;

        }

        return null;
    }

    private String obtnerContra(String Usuario) {
        try {
            PreparedStatement select = conexion.prepareStatement(SelectPassword);
            select.setString(1, Usuario);
            ResultSet resultset = select.executeQuery();

            if (resultset.next()) {
                return resultset.getString("password");
            }

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
