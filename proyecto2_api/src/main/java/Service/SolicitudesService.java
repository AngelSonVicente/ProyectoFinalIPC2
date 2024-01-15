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
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class SolicitudesService {

    private Connection conexion;

    public SolicitudesService() {
    }

    public SolicitudesService(Connection conexion) {
        conexion = ConexionBD.getInstancia().getConexion();
    }

    // SolicitudesBD solicitudBD = new SolicitudesBD();
    // OfertaService ofertaService = new OfertaService();
    // SolicitudesRetiradasBD soliretirada = new SolicitudesRetiradasBD();
    Util util = new Util();
    JsonUtil jsonUtil = new JsonUtil();
    private String MensajeNotificacion = "ha aplicado a la oferta de empleo";

    public void CrearSolicitud(String body, HttpServletResponse response) throws InvalidDataException, IOException, NotFoundException, SQLException {

        Solicitudes solicitudFE = (Solicitudes) jsonUtil.JsonStringAObjeto(body, Solicitudes.class);

        solicitudFE.setEstado(Estado.Activo.name());
        NotificacionesService notificacionesService = new NotificacionesService(conexion);
        OfertaService ofertaService = new OfertaService(conexion);

        System.out.println("codigo empleo: " + solicitudFE.getCodigoOferta());
        System.out.println("codigo usuario: " + solicitudFE.getCodigoUsuario());
        System.out.println("Mensaje: " + solicitudFE.getMensaje());

        Solicitudes solicitud = new Solicitudes();
        //APLICAR TRANSACCIONALIDAD
        //crearSolicitud
        try {
            // Iniciar transacción
            conexion.setAutoCommit(false);

            //funciones
            solicitud = crearSOlicitudBD(solicitudFE);

            //notificar al emppleador
            Oferta oferta = ofertaService.getOferta(solicitudFE.getCodigoOferta());

            Notificaciones notificacion = new Notificaciones(null, solicitudFE.getCodigoUsuario(), null, oferta.getCodigoEmpresa(), null, oferta.getCodigo(), null, MensajeNotificacion, null, null);

            notificacionesService.CrearNotificacion(notificacion);

// Confirmar la transacción si todas las operaciones fueron exitosas
            conexion.commit();

        } catch (SQLException e) {
            // En caso de error, deshacer la transacción

            try {
                conexion.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } finally {
            // Restaurar el modo de autocommit y cerrar la conexión
            try {
                conexion.setAutoCommit(true);
                //enviar json
                jsonUtil.EnviarJson(response, solicitud);

            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

    }

    public void ActualizarSolicitud(Solicitudes solicitud, HttpServletResponse response) throws IOException, InvalidDataException, NotFoundException {

        System.out.println(solicitud.toString());

        NotificacionesService notificacionesService = new NotificacionesService(conexion);
        Solicitudes solicitudActualizada = new Solicitudes();
        try {
            // Iniciar transacción
            conexion.setAutoCommit(false);

            //funciones
            solicitudActualizada = actualizarEstadoSolicitudBD(solicitud);

            if (solicitud.getEstado().equals(Estado.Rechazado.name())) {

                //enviar notificacion de rechazo
                Notificaciones notificaion = new Notificaciones(null, null, null, solicitud.getCodigoUsuario(), null, solicitud.getCodigoOferta(), null, "Se ha rechazado su Solicitud a la oferta", null, null);
                System.out.println(notificaion.toString());

                notificacionesService.CrearNotificacion(notificaion);

            }

// Confirmar la transacción si todas las operaciones fueron exitosas
            conexion.commit();

        } catch (SQLException e) {
            // En caso de error, deshacer la transacción

            try {
                conexion.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } finally {
            // Restaurar el modo de autocommit y cerrar la conexión
            try {
                conexion.setAutoCommit(true);
                //enviar json

            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

        jsonUtil.EnviarJson(response, solicitudActualizada);

    }

    public Solicitudes crearSOlicitudBD(Solicitudes solicitud) throws InvalidDataException {

        validar(solicitud);
        SolicitudesBD solicitudBD = new SolicitudesBD(conexion);
        return solicitudBD.crearSolicitud(solicitud);
    }

    public boolean ExisteSolicitud(String codigoUsuario, String codigoOferta) {
        SolicitudesBD solicitudBD = new SolicitudesBD(conexion);
        return solicitudBD.ExisteSolicitud(codigoUsuario, codigoOferta);
    }

    public boolean borrarSolicitud(String codigo) throws InvalidDataException, NotFoundException {
        SolicitudesBD solicitudBD = new SolicitudesBD(conexion);

        Solicitudes soli = solicitudBD.getSolicitud(codigo);
        OfertaService ofertaService = new OfertaService(conexion);
        Oferta oferta = ofertaService.getOferta(soli.getCodigoOferta());
        NotificacionesService notificacionesService = new NotificacionesService(conexion);
        SolicitudesRetiradasBD soliretirada = new SolicitudesRetiradasBD(conexion);

        boolean solicitudBoraada = false;

        //APLICAR TRANSACCION
        try {
            // Iniciar transacción
            conexion.setAutoCommit(false);

            //funciones
            soliretirada.crearSolicitudRetirada(new SolicitudRetirada(null, soli.getCodigoUsuario(), null, soli.getCodigoOferta(), null, null));

            Notificaciones notificacion = new Notificaciones(null, soli.getCodigoUsuario(), null, oferta.getCodigoEmpresa(), null, soli.getCodigoOferta(), null, "El Usuario ha retirado su postulacion de la oferta", null, null);

            //mandar notificacion 
            notificacionesService.CrearNotificacion(notificacion);

            solicitudBoraada = solicitudBD.borrarSolicitud(codigo);

// Confirmar la transacción si todas las operaciones fueron exitosas
            conexion.commit();

        } catch (SQLException e) {
            // En caso de error, deshacer la transacción

            try {
                conexion.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            // Restaurar el modo de autocommit y cerrar la conexión
            try {
                conexion.setAutoCommit(true);

            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

        return solicitudBoraada;

    }

    public List<Solicitudes> getSolicitudesOferta(String codigo) throws InvalidDataException {

        if (codigo == null || codigo.isEmpty()) {
            throw new InvalidDataException("el codigo no es valido");

        }

        SolicitudesBD solicitudBD = new SolicitudesBD(conexion);
        return solicitudBD.getSolicitudesOferta(codigo);
    }

    public List<Solicitudes> getSolicitudesEmpresa(String codigo) throws InvalidDataException {
        if (codigo == null || codigo.isEmpty()) {
            throw new InvalidDataException("el codigo no es valido");

        }

        SolicitudesBD solicitudBD = new SolicitudesBD(conexion);
        return solicitudBD.getSolicitudesUsuario(codigo);
    }

    public Solicitudes actualizarEstadoSolicitudBD(Solicitudes solicitud) throws InvalidDataException, NotFoundException {
        SolicitudesBD solicitudBD = new SolicitudesBD(conexion);
        OfertaService ofertaService = new OfertaService(conexion);
        
         Solicitudes solicitudactualizada = new Solicitudes();

        try {
            // Iniciar transacción
            conexion.setAutoCommit(false);

            //funciones
            //cambiar el estado de la oferta a Entrevista
             solicitudactualizada = solicitudBD.actualizarEstadoSolicitud(solicitud);

            List<Solicitudes> solicitudesOferta = getSolicitudesOferta(solicitud.getCodigoOferta());
            System.out.println("codigo OFerta kjndkjnsadkjs_: " + solicitud.getCodigoOferta());

            if (SolicitudesEnEntrevista(solicitudesOferta)) {
                // cambiar el estado de oferta a Entrevista

                Oferta oferta = ofertaService.getOferta(solicitud.getCodigoOferta());
                oferta.setEstado("Entrevista");
                ofertaService.actualizarEstadoOferta(oferta);

            }

// Confirmar la transacción si todas las operaciones fueron exitosas
            conexion.commit();

        } catch (SQLException e) {
            // En caso de error, deshacer la transacción

            try {
                conexion.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            // Restaurar el modo de autocommit y cerrar la conexión
            try {
                conexion.setAutoCommit(true);
          
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

        return solicitudactualizada;

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
