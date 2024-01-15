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

   
    public List<Oferta> getOfertas() {
        return ofertasBD.getOfertas();
    }

    public List<Oferta> getOfertasPreferencias(String codigo) throws InvalidDataException {
        
        PasarOfertasFaseSeleccion();
        return ofertasBD.getOfertasPreferencias(codigo);
    }

    public boolean OfertaFinalizada(String codigoOferta) {
        
        return ofertasBD.OfertaFinalizada(codigoOferta);
    }

    public List<Oferta> getOfertasEmpresa(String codigo) throws InvalidDataException {
        PasarOfertasFaseSeleccion();
        return ofertasBD.getOfertasEmpresa(codigo);
    }

    public List<Oferta> getOfertasEmpresaEstados(String codigo, String estado) throws InvalidDataException {
          PasarOfertasFaseSeleccion();
      
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

    public void PasarOfertasFaseSeleccion() throws InvalidDataException {
        //pasamos las ofertas de fase Activa a fase de seleccion cuando llegue la fecha limite
        List<Oferta> ofertas = getOfertas();

        for (Oferta oferta : ofertas) {
            
            System.out.println(oferta.toString());

            if (!util.NoHaAlcanzadoFechaLimite(oferta.getFechaLimite())) {
                
                System.out.println("Aqui segun que ya alcanzó la fecha limite");

                oferta.setEstado("Seleccion");
                
                System.out.println("cambio de estado hecho: "+ oferta.toString());
                
                actualizarEstadoOferta(oferta);

                //enviar Notificacion
                NotificacionesService notificacionService = new NotificacionesService(conexion);

                SolicitudesService solicitudService = new SolicitudesService();

                //obtenemos todas las solicitudes que pertenezcan a la oferta para enviarles la notificaion
                List<Solicitudes> solicitudesOferta = solicitudService.getSolicitudesOferta(oferta.getCodigo());

                for (Solicitudes solicitud : solicitudesOferta) {
                    //enviamos todas notificacion a todas las personas que se hayan postulado
                    //creamos la notificaion 
                    Notificaciones notificacion = new Notificaciones(null, oferta.getCodigoEmpresa(), null, solicitud.getCodigoUsuario(), null, oferta.getCodigo(), null, "La Oferta de empleo ha pasado a fase de seleccion! Se le enviara una notificacion si se le aganda una entrevista o es rechazado. Mucha Suerte!", null, null);

                    //enviamos la notificaion
                    notificacionService.CrearNotificacion(notificacion);
                }

            }

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
