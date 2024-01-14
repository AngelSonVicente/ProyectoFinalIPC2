/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Entrevista;
import Datos.FinalizarOferta;
import Datos.JsonUtil;
import Datos.Notificaciones;
import Datos.Solicitudes;
import DatosBD.ConexionBD;
import DatosBD.FinalizarOfertaBD;
import DatosBD.OfertaBD;
import DatosBD.SolicitudesBD;
import exceptions.InvalidDataException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author MSI
 */
public class FinalizarOfertaService {
            static Connection conexion = ConexionBD.getInstancia().getConexion();

    FinalizarOfertaBD finalizar = new FinalizarOfertaBD();
    SolicitudesBD soli = new SolicitudesBD();
    SolicitudesService solicitudesService = new SolicitudesService();
    EntrevistaService entrevistaService = new EntrevistaService();

    JsonUtil jsonUtil = new JsonUtil();

    public void FinalizarOFerta(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        FinalizarOferta datos = (FinalizarOferta) jsonUtil.JsonStringAObjeto(body, FinalizarOferta.class);

        FinalizarOferta finalizacionHecha = finalizarOfertaBD(datos);

        jsonUtil.EnviarJson(response, finalizacionHecha);

    }

    public FinalizarOferta finalizarOfertaBD(FinalizarOferta finalizarOferta) throws InvalidDataException {

        finalizarOferta.setCodigoSolicitud(soli.getSolicitudOU(finalizarOferta.getCodigoUsuarioElegido(), finalizarOferta.getCodigoOferta()));

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Si jalo");
        System.out.println("oferta: " + finalizarOferta.getCodigoOferta());
        System.out.println("soli: " + finalizarOferta.getCodigoSolicitud());
        System.out.println("usuario: " + finalizarOferta.getCodigoUsuarioElegido());
        System.out.println("empresa: " + finalizarOferta.getCodigoEmpresa());

       List<Entrevista> entrevistas= entrevistaService.getEntrevistasOferta(finalizarOferta.getCodigoOferta());
        
        //APLICAR 
        finalizar.actualizarEstadoOferta(finalizarOferta);
        finalizar.actualizarEstadoSolicitud(finalizarOferta);
        finalizar.realizarPago(finalizarOferta);
        

        NotificacionesService notificacionesService = new NotificacionesService(conexion);
        
        Notificaciones notificacionUsuarioElegido = new Notificaciones(null, finalizarOferta.getCodigoEmpresa(), null, finalizarOferta.getCodigoUsuarioElegido(), null, finalizarOferta.getCodigoOferta(), null, "Felicidades! ha conseguido el puesto en la oferta de empleo! Pongase en contacto con la empresa.", null, null);
        
        //enviar notificacion al usuario elegido
        notificacionesService.CrearNotificacion(notificacionUsuarioElegido);
        
        //enviar notificaion a los demás usuarios 
        
        for(Entrevista entrevista : entrevistas){
            
            if(entrevista.getCodigoUsuario()!=finalizarOferta.getCodigoUsuarioElegido()){
              
                Notificaciones notificacion = new Notificaciones(null, finalizarOferta.getCodigoEmpresa(), null, entrevista.getCodigoUsuario(), null, finalizarOferta.getCodigoOferta(), null, "La oferta de empleo ha finalizado, Lamentablemente usted no ha sido elegido para tomar el puesto. siga buscando mas ofertas!", null, null);
       
                notificacionesService.CrearNotificacion(notificacion);
            
            }
       
        
        }
        
        
        

        return finalizarOferta;
    }

}
