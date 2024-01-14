/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Comision;
import Datos.JsonUtil;
import Datos.Oferta;
import Datos.SolicitudRetirada;
import Datos.Solicitudes;
import Datos.Util;
import DatosBD.SolicitudesBD;
import DatosBD.SolicitudesRetiradasBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import Datos.Estado;
import Datos.Notificaciones;
import DatosBD.ConexionBD;
import java.sql.Connection;

/**
 *
 * @author MSI
 */
public class SolicitudesService {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    SolicitudesBD solicitudBD = new SolicitudesBD();
    OfertaService ofertaService = new OfertaService();
    SolicitudesRetiradasBD soliretirada = new SolicitudesRetiradasBD();

    Util util = new Util();
    JsonUtil jsonUtil = new JsonUtil();
    private String MensajeNotificacion = "ha aplicado a la oferta de empleo";

    public void CrearSolicitud(String body, HttpServletResponse response) throws InvalidDataException, IOException, NotFoundException {

        Solicitudes solicitudFE = (Solicitudes) jsonUtil.JsonStringAObjeto(body, Solicitudes.class);

        solicitudFE.setEstado(Estado.Activo.name());

        System.out.println("codigo empleo: " + solicitudFE.getCodigoOferta());
        System.out.println("codigo usuario: " + solicitudFE.getCodigoUsuario());
        System.out.println("Mensaje: " + solicitudFE.getMensaje());

        //APLICAR TRANSACCIONALIDAD
        //crearSolicitud
        Solicitudes solicitud = crearSOlicitudBD(solicitudFE);

        //notificar al emppleador
        Oferta oferta = ofertaService.getOferta(solicitudFE.getCodigoOferta());

        Notificaciones notificacion = new Notificaciones(null, solicitudFE.getCodigoUsuario(), null, oferta.getCodigoEmpresa(), null, oferta.getCodigo(), null, MensajeNotificacion, null, null);

        NotificacionesService notificacionesService = new NotificacionesService(conexion);
        notificacionesService.CrearNotificacion(notificacion);

        jsonUtil.EnviarJson(response, solicitud);

    }

    public void ActualizarSolicitud(Solicitudes solicitud, HttpServletResponse response) throws IOException, InvalidDataException {

        System.out.println(solicitud.toString());
        
        //aplicar transaccionalidad :v
       Solicitudes solicitudActualizada = actualizarEstadoSolicitudBD(solicitud);

        
        NotificacionesService notificacionesService = new NotificacionesService(conexion);
        if(solicitud.getEstado().equals(Estado.Rechazado.name())){
            
            
            //enviar notificacion de rechazo
            
            Notificaciones notificaion = new Notificaciones(null, null, null, solicitud.getCodigoUsuario(), null, solicitud.getCodigoOferta(), null, "Se ha rechazado su Solicitud a la oferta", null, null);
            System.out.println(notificaion.toString());
            
            notificacionesService.CrearNotificacion(notificaion);
            
            
        }
        
        
        
        
        jsonUtil.EnviarJson(response, solicitudActualizada);
        

    }

    public Solicitudes crearSOlicitudBD(Solicitudes solicitud) throws InvalidDataException {

        validar(solicitud);

        return solicitudBD.crearSolicitud(solicitud);
    }

    public boolean ExisteSolicitud(String codigoUsuario, String codigoOferta) {
        return solicitudBD.ExisteSolicitud(codigoUsuario, codigoOferta);
    }

    public boolean borrarSolicitud(String codigo) throws InvalidDataException, NotFoundException {
        Solicitudes soli = solicitudBD.getSolicitud(codigo);

        Oferta oferta = ofertaService.getOferta(soli.getCodigoOferta());
        
        
        //APLICAR TRANSACCION
        soliretirada.crearSolicitudRetirada(new SolicitudRetirada(null, soli.getCodigoUsuario(), null, soli.getCodigoOferta(), null, null));
     
        NotificacionesService notificacionesService = new NotificacionesService(conexion);
        
        Notificaciones notificacion = new Notificaciones(null, soli.getCodigoUsuario(), null, oferta.getCodigoEmpresa(), null, soli.getCodigoOferta(), null, "El Usuario ha retirado su postulacion de la oferta", null, null);
        
        //mandar notificacion 
        notificacionesService.CrearNotificacion(notificacion);
        
        
        
        return solicitudBD.borrarSolicitud(codigo);
    }

    public List<Solicitudes> getSolicitudesOferta(String codigo) {
        return solicitudBD.getSolicitudesOferta(codigo);
    }

    public List<Solicitudes> getSolicitudesEmpresa(String codigo) {
        return solicitudBD.getSolicitudesUsuario(codigo);
    }

    public Solicitudes actualizarEstadoSolicitudBD(Solicitudes solicitud) {
        //cambiar el estado de la oferta a Entrevista
        Solicitudes soli = solicitudBD.actualizarEstadoSolicitud(solicitud);

        List<Solicitudes> solicitudesOferta = getSolicitudesOferta(solicitud.getCodigoOferta());
        System.out.println("codigo OFerta kjndkjnsadkjs_: " + solicitud.getCodigoOferta());

        if (SolicitudesEnEntrevista(solicitudesOferta)) {
            // cambiar el estado de oferta a Entrevista
            try {
                System.out.println("actualizando la onda esta");

                Oferta oferta = ofertaService.getOferta(solicitud.getCodigoOferta());
                oferta.setEstado("Entrevista");
                ofertaService.actualizarEstadoOferta(oferta);
            } catch (Exception e) {
                System.out.println("-----------------------");
                System.out.println(e);
            }
        }

        return soli;

    }

    public boolean SolicitudesEnEntrevista(List<Solicitudes> solicitudesOferta) {
        for (Solicitudes solicitud : solicitudesOferta) {
            System.out.println("codigo: " + solicitud.getCodigo());
            System.out.println("Estado: " + solicitud.getEstado());
            if ("Activo".equals(solicitud.getEstado())) {
                // Si alguna solicitud no está en estado "Entrevista", retorna false

                System.out.println("Todavia hay un Activo, no cambiar");
                return false;
            }
            if (solicitudesOferta.isEmpty() || solicitudesOferta == null) {
                return true;
            }
        }
        System.out.println("ya no hay ningun activo cambiar");
        // Si todas las solicitudes están en estado "Entrevista", retorna true
        return true;
    }

    public void validar(Solicitudes solicitud) throws InvalidDataException {
        if (!util.ValidarLenght(solicitud.getMensaje(), 200)) {
            throw new InvalidDataException("el Mensaje es demasiado grande");
        }

    }

}
