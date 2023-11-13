/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Comision;
import Datos.SolicitudRetirada;
import Datos.Solicitudes;
import Datos.Util;
import DatosBD.SolicitudesBD;
import DatosBD.SolicitudesRetiradasBD;
import exceptions.InvalidDataException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author MSI
 */
public class SolicitudesService {

    SolicitudesBD solicitudBD = new SolicitudesBD();
    SolicitudesRetiradasBD soliretirada = new SolicitudesRetiradasBD();
    Util util = new Util();
    
      
    public Solicitudes crearSOlicitud(Solicitudes solicitud) throws InvalidDataException{
        
        validar(solicitud);
      
    return solicitudBD.crearSolicitud(solicitud);
    }
    
    public boolean ExisteSolicitud(String codigoUsuario, String codigoOferta){
    return solicitudBD.ExisteSolicitud(codigoUsuario, codigoOferta);
    }
    public boolean borrarSolicitud(String codigo){
        Solicitudes soli = solicitudBD.getSolicitud(codigo);
        
        soliretirada.crearSolicitudRetirada(new SolicitudRetirada(null, soli.getCodigoUsuario(), null, soli.getCodigoOferta(),null, null));
    return solicitudBD.borrarSolicitud(codigo);
    }
    
     public List<Solicitudes> getOfertasOferta(String codigo) {
        return solicitudBD.getSolicitudesOferta(codigo);
    }
    
     public List<Solicitudes> getOfertasEmpresa(String codigo) {
        return solicitudBD.getSolicitudesUsuario(codigo);
    }
    
    
    public void validar(Solicitudes solicitud) throws InvalidDataException{
          if (!util.ValidarLenght(solicitud.getMensaje(), 200) ) {
            throw new InvalidDataException("el Mensaje es demasiado grande");
        }
          
 
    }
    
}
