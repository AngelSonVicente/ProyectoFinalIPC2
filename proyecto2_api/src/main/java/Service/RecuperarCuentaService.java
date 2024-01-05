/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.JsonUtil;
import Datos.RecuperarCuenta;
import Datos.Usuario;
import DatosBD.ConexionBD;
import exceptions.InvalidDataException;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author MSI
 */
public class RecuperarCuentaService {
    
    JsonUtil jsonUtil = new JsonUtil();
    UsuarioService usuarioService = new UsuarioService();
    static Connection conexion = ConexionBD.getInstancia().getConexion();

    
    public void procesarSolicitud(String body) throws IOException, InvalidDataException{
        EnviarCorreo enviarCorreo = new EnviarCorreo();
        RecuperarCuenta cuenta = (RecuperarCuenta) jsonUtil.JsonStringAObjeto(body, RecuperarCuenta.class);
        ValidarCorreo(cuenta);
    
        System.out.println("Objeto obtenido: " + cuenta.toString()  );
        
       enviarCorreo.sendEmail(cuenta.getCorreo());
    }
    
        public void ValidarCorreo(RecuperarCuenta cuenta) throws InvalidDataException {
        Usuario ExisteCorreo = usuarioService.getUsuarioCorreo(cuenta.getCorreo());
            if(ExisteCorreo == null){
            throw new InvalidDataException("El Usuario NO Existe");
        }
    }
    
}
