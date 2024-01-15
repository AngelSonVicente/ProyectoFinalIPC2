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
    //static Connection conexion = ConexionBD.getInstancia().getConexion();

    private Connection conexion;

    public EntrevistaService(Connection conexion) {
        this.conexion = conexion;
    }

    public EntrevistaService() {
        conexion = ConexionBD.getInstancia().getConexion();
    }
    
    private EntrevistaBD entrevistaBD = new EntrevistaBD();
    private OfertaService ofertaService = new OfertaService();
    private JsonUtil jsonUtil = new JsonUtil();
    private SolicitudesService solicitudService = new SolicitudesService();

   

    public Entrevista agendarEntrevista(Entrevista entrevista) throws InvalidDataException {
        Solicitudes solicitud = new Solicitudes(entrevista.getCodigoSolicitud(), entrevista.getCodigoOferta(), null, null, null, null, Estado.Entrevista.name());
      
        //aplicar transaccionalidad
        Entrevista entrevistaCreada = crearEntrevista(entrevista);
        solicitudService.actualizarEstadoSolicitudBD(solicitud);

        return entrevistaCreada;
    }

    public List<Entrevista> getEntrevistasOferta(String codigo) throws InvalidDataException {
          if(codigo==null || codigo.isEmpty()){
               throw new InvalidDataException("El codigo no es valido");
     
        }
        
        return entrevistaBD.getEntrevistasOferta(codigo);
    }

    public Entrevista finalizarEntrevista(Entrevista entrevista) throws InvalidDataException {
        validarFinalizacion(entrevista);
        return entrevistaBD.finalizarEntrevista(entrevista);
    }

    public List<Entrevista> getEntrevistasUsuario(String codigo) throws InvalidDataException {
        if(codigo==null || codigo.isEmpty()){
               throw new InvalidDataException("El codigo no es valido");
     
        }
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
