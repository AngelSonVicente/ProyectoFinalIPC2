/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.JsonUtil;
import Datos.Notificaciones;
import DatosBD.ConexionBD;
import DatosBD.NotificacionesBD;
import exceptions.InvalidDataException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author MSI
 */
public class NotificacionesService {

    private Connection conexion;

    public NotificacionesService(Connection conexion) {
        this.conexion = conexion;
    }

    public NotificacionesService() {
            conexion = ConexionBD.getInstancia().getConexion();
    }

    

    JsonUtil jsonUtil = new JsonUtil();

    public void getNotificaciones(String codigoUsuarioDestino, HttpServletResponse response) throws InvalidDataException, IOException {

        List<Notificaciones> notificaciones = getNotificacionesBD(codigoUsuarioDestino);

        jsonUtil.EnviarListaJson(response, notificaciones);

    }
    public void MarcarComoNotificacionesComoLeido(String codigoUsuario, HttpServletResponse response) throws InvalidDataException{
        
        if(marcarComoLeidoNotificacionesUsuarioBD(codigoUsuario)){
                response.setStatus(HttpServletResponse.SC_OK);
        
        }else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        
        }
    
    }
    
    
    
    
    
    
    

    public List<Notificaciones> getNotificacionesBD(String codigoUsuarioDestino) throws InvalidDataException {
        if (codigoUsuarioDestino == null || codigoUsuarioDestino.isEmpty()) {
            throw new InvalidDataException("El Usuario NO Existe");
        }
        NotificacionesBD notificacionesBD = new NotificacionesBD(conexion);
        return notificacionesBD.getNotificaciones(codigoUsuarioDestino);
    }
    
    public Notificaciones CrearNotificacion(Notificaciones notificaion) throws InvalidDataException{
    
        
        if( notificaion.getCodigoUsuarioDestino()==null || notificaion.getCodigoOferta() ==null ||
                notificaion.getContenido()==null ||notificaion.getCodigoUsuarioDestino().isEmpty() || notificaion.getCodigoOferta().isEmpty()  ||
                notificaion.getContenido().isEmpty() ){
                throw new InvalidDataException("El Usuario NO Existe");
    
        }
        
        
            NotificacionesBD notificacionesBD = new NotificacionesBD(conexion);
    
        return notificacionesBD.crearNotificacion(notificaion);
    }
    public boolean marcarComoLeidoNotificacionesUsuarioBD(String CodigoUsuario) throws InvalidDataException{
    
        
        if(CodigoUsuario==null || CodigoUsuario.isEmpty() ){
                throw new InvalidDataException("El Usuario NO Existe");
    
        }
        
        
            NotificacionesBD notificacionesBD = new NotificacionesBD(conexion);
    
        return notificacionesBD.marcarComoLeidoNotificacionesUsuario(CodigoUsuario);
    }

}
