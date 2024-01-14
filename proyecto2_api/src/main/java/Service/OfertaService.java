package Service;

import Datos.Comision;
import Datos.Entrevista;
import Datos.JsonUtil;
import Datos.Oferta;
import Datos.OfertaEliminada;
import Datos.Util;
import DatosBD.ComisionBD;
import DatosBD.GestionCategoriaBD;
import DatosBD.OfertaBD;
import com.google.gson.Gson;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import Datos.Estado;
import Datos.Notificaciones;
import Datos.Solicitudes;
import DatosBD.ConexionBD;
import Servlets.EntrevistasServlet;
import java.sql.Connection;

/**
 *
 * @author MSI
 */
public class OfertaService {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    OfertaBD ofertasBD = new OfertaBD();

    GestionCategoriaBD categoria = new GestionCategoriaBD();

    ComisionBD comision = new ComisionBD();
    Util util = new Util();
    JsonUtil jsonUtil = new JsonUtil();

    public void ActualizarOferta(String body, HttpServletResponse response) throws IOException, InvalidDataException {
        Oferta ofertaFE = (Oferta) jsonUtil.JsonStringAObjeto(body, Oferta.class);

        System.out.println("Objeto recibido: " + ofertaFE.toString());

        //actualizar la oferta
        Oferta oferta = actualizarOfertaBD(ofertaFE);

        //enviar notificacion 
        //entrevista enviar a usuario con entrevistas 
        NotificacionesService notificacionService = new NotificacionesService(conexion);

        //Activo, Seleccion enviar notificacion  a usuarios con solicitudes
        if (ofertaFE.getEstado().equals(Estado.Activo.name()) || ofertaFE.getEstado().equals("Seleccion")) {

            System.out.println("en fase de seleccion ");
            SolicitudesService solciitudesService = new SolicitudesService();

            List<Solicitudes> solicitudesOferta = solciitudesService.getSolicitudesOferta(ofertaFE.getCodigo());

            //enviar notificacion a todas los usuarios con solicitud en la oferta
            for (Solicitudes solicitud : solicitudesOferta) {
                System.out.println(solicitud.toString());

                Notificaciones notificacion = new Notificaciones(null, ofertaFE.getCodigoEmpresa(), null, solicitud.getCodigoUsuario(), null, ofertaFE.getCodigo(), null, "Se ha modificado la oferta", null, null);

                notificacionService.CrearNotificacion(notificacion);

            }

        }

        if (ofertaFE.getEstado().equals(Estado.Entrevista.name())) {

            EntrevistaService entrevistaService = new EntrevistaService();

            List<Entrevista> entrevistasOferta = entrevistaService.getEntrevistasOferta(ofertaFE.getCodigo());

            //enviar notificacion a todas los usuarios con entrevista en la oferta
            for (Entrevista entrevista : entrevistasOferta) {
                Notificaciones notificacion = new Notificaciones(null, ofertaFE.getCodigoEmpresa(), null, entrevista.getCodigoUsuario(), null, ofertaFE.getCodigo(), null, "Se ha modificado la oferta", null, null);

                notificacionService.CrearNotificacion(notificacion);

            }

        }

        jsonUtil.EnviarJson(response, oferta);

    }

    public void EliminarOferta(OfertaEliminada ofertaEliminada, HttpServletResponse response) throws NotFoundException, InvalidDataException {

        System.out.println("oferta eliminada " + ofertaEliminada.toString());
        
        OfertaEliminada oferta = eliminarOfertaBD(ofertaEliminada);
        
        if (oferta != null) {

            SolicitudesService solicitudesService = new SolicitudesService();
            EntrevistaService entrevistaService = new EntrevistaService();

            List<Solicitudes> solicitudesOferta = solicitudesService.getSolicitudesOferta(ofertaEliminada.getCodigoOferta());
            List<Entrevista> entrevistasOferta = entrevistaService.getEntrevistasOferta(ofertaEliminada.getCodigoOferta());

            NotificacionesService notificacionesService = new NotificacionesService(conexion);

            Notificaciones notificacion = new Notificaciones(null, null, null, null, null, ofertaEliminada.getCodigoOferta(), null, "Se ha eliminado la oferta de empleo. MOtivo: " + ofertaEliminada.getMotivo(), null, null);

            for (Solicitudes solicitud : solicitudesOferta) {
                notificacion.setCodigoUsuarioDestino(solicitud.getCodigoUsuario());
                notificacionesService.CrearNotificacion(notificacion);

            }

            for (Entrevista entrevista : entrevistasOferta) {
                notificacion.setCodigoUsuarioDestino(entrevista.getCodigoUsuario());
                notificacionesService.CrearNotificacion(notificacion);

            }

            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    public List<Oferta> getOfertas() {
        return ofertasBD.getOfertas();
    }

    public boolean OfertaFinalizada(String codigoOferta) {
        return ofertasBD.OfertaFinalizada(codigoOferta);
    }

    public List<Oferta> getOfertasEmpresa(String codigo) {
        return ofertasBD.getOfertasEmpresa(codigo);
    }

    public List<Oferta> getOfertasEmpresaEstados(String codigo, String estado) {
        return ofertasBD.getOfertasEmpresaEstados(codigo, estado);
    }

    public Oferta crearOferta(Oferta oferta) throws InvalidDataException {
        validar(oferta);
        return ofertasBD.crearOferta(oferta);
    }

    public Oferta actualizarEstadoOferta(Oferta oferta) throws InvalidDataException {
        validar(oferta);
        return ofertasBD.actualizarEstadoOferta(oferta);
    }

    public Oferta actualizarOfertaBD(Oferta oferta) throws InvalidDataException {
        validar(oferta);
        return ofertasBD.actualizarOferta(oferta);
    }

    public OfertaEliminada eliminarOfertaBD(OfertaEliminada oferta) {

        return ofertasBD.EliminarOferta(oferta);
    }

    public Oferta getOferta(String codigo) throws NotFoundException {

        Oferta oferta = ofertasBD.getOferta(codigo);

        if (oferta != null) {

            return oferta;
        } else {

            throw new NotFoundException("No se encontró la categoria");
        }

    }

    public void validar(Oferta oferta) throws InvalidDataException {
        if (oferta.getNombre().isEmpty() || oferta.getDescripcion().isEmpty() || oferta.getFechaLimite().isEmpty() || oferta.getModadidad().isEmpty() || oferta.getUbicacion().isEmpty() || oferta.getDetalle().isEmpty()) {
            throw new InvalidDataException("Faltan Datos");
        }

        if (oferta.getSalario() < 0) {
            throw new InvalidDataException("No se puede ingresar un numero negativo");
        }

    }

}
