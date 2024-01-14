package Service;

import Datos.Entrevista;
import Datos.Estado;
import Datos.JsonUtil;
import Datos.Notificaciones;
import Datos.Oferta;
import Datos.Solicitudes;
import DatosBD.ConexionBD;
import DatosBD.EntrevistaBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author MSI
 */
public class EntrevistaService {
    static Connection conexion = ConexionBD.getInstancia().getConexion();

    
    private EntrevistaBD entrevistaBD = new EntrevistaBD();
    private OfertaService ofertaService = new OfertaService();
    private JsonUtil jsonUtil = new JsonUtil();
    private SolicitudesService solicitudService = new SolicitudesService();

    public Entrevista procesarSolicitud(String Body, HttpServletResponse response) throws IOException, InvalidDataException, NotFoundException {
        //Crear entrevista y actualizar estado SOlicitud a "Entrevista"
        Entrevista entrevista = (Entrevista) jsonUtil.JsonStringAObjeto(Body, Entrevista.class);
        entrevista.setEstado(Estado.Pendiente.name());

        System.out.println("Entrevista recibida: "+ entrevista.toString());
        
        //APLICAR TRANSACCION
        Entrevista entrevistaAgendada = agendarEntrevista(entrevista);
        
        Oferta oferta = ofertaService.getOferta(entrevista.getCodigoOferta());
        
        //enviar notificacion a usuario de que ha conseguido la entrevista
        NotificacionesService notificacionesService = new NotificacionesService(conexion);
        
        Notificaciones notificacion = new Notificaciones(null, oferta.getCodigoEmpresa(), null, entrevista.getCodigoUsuario(), null, entrevista.getCodigoOferta(), null, "Se ha agendado una Entrevista En la oferta de empleo", null, null);
    //enviar notificacion
        notificacionesService.CrearNotificacion(notificacion);
        
        
        

       jsonUtil.EnviarJson(response, entrevistaAgendada);

        return null;
    }

    public Entrevista agendarEntrevista(Entrevista entrevista) throws InvalidDataException {
        Solicitudes solicitud = new Solicitudes(entrevista.getCodigoSolicitud(), entrevista.getCodigoOferta(), null, null, null, null, Estado.Entrevista.name());
      
        //aplicar transaccionalidad
        Entrevista entrevistaCreada = crearEntrevista(entrevista);
        solicitudService.actualizarEstadoSolicitudBD(solicitud);

        return entrevistaCreada;
    }

    public List<Entrevista> getEntrevistasOferta(String codigo) {
        return entrevistaBD.getEntrevistasOferta(codigo);
    }

    public Entrevista finalizarEntrevista(Entrevista entrevista) throws InvalidDataException {
        validarFinalizacion(entrevista);
        return entrevistaBD.finalizarEntrevista(entrevista);
    }

    public List<Entrevista> getEntrevistasUsuario(String codigo) {
        return entrevistaBD.getEntrevistasUsuario(codigo);
    }

    public Entrevista crearEntrevista(Entrevista entrevista) throws InvalidDataException {
        validar(entrevista);

        return entrevistaBD.crearEntrevista(entrevista);
    }

    public void validar(Entrevista entrevista) throws InvalidDataException {
        if (entrevista.getCodigoOferta().isEmpty() || entrevista.getCodigoUsuario().isEmpty() || entrevista.getFecha().isEmpty() || entrevista.getHora().isEmpty()
                || entrevista.getUbicacion().isEmpty() || entrevista.getCodigoOferta() == null || entrevista.getCodigoUsuario() == null || entrevista.getFecha() == null || entrevista.getHora() == null
                || entrevista.getUbicacion() == null) {
            throw new InvalidDataException("Faltan Datos");
        }

    }

    public void validarFinalizacion(Entrevista entrevista) throws InvalidDataException {
        if (entrevista.getCodigo().isEmpty() || entrevista.getNota().isEmpty() || entrevista.getCodigo() == null
                || entrevista.getNota() == null) {
            throw new InvalidDataException("Faltan Datos");
        }
    }
}
