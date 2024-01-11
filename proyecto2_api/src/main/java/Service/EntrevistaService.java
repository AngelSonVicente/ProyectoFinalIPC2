package Service;

import Datos.Entrevista;
import Datos.Estado;
import Datos.JsonUtil;
import Datos.Solicitudes;
import DatosBD.EntrevistaBD;
import exceptions.InvalidDataException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class EntrevistaService {

    private EntrevistaBD entrevistaBD = new EntrevistaBD();
    private JsonUtil jsonUtil = new JsonUtil();
    private SolicitudesService solicitudService = new SolicitudesService();

    public Entrevista procesarSolicitud(String Body, HttpServletResponse response) throws IOException, InvalidDataException {
        //Crear entrevista y actualizar estado SOlicitud a "Entrevista"
        Entrevista entrevista = (Entrevista) jsonUtil.JsonStringAObjeto(Body, Entrevista.class);
        entrevista.setEstado(Estado.Pendiente.name());

        Entrevista entrevistaAgendada = agendarEntrevista(entrevista);

        jsonUtil.EnviarJson(response, entrevistaAgendada);

        return null;
    }

    public Entrevista agendarEntrevista(Entrevista entrevista) throws InvalidDataException {
        Solicitudes solicitud = new Solicitudes(entrevista.getCodigoSolicitud(), entrevista.getCodigoOferta(), null, null, null, null, Estado.Entrevista.name());
      
        //aplicar transaccionalidad
        Entrevista entrevistaCreada = crearEntrevista(entrevista);
        solicitudService.actualizarEstadoSolicitud(solicitud);

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
