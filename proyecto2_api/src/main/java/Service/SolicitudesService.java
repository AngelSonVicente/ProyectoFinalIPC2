/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Comision;
import Datos.Solicitudes;
import Datos.Util;
import DatosBD.SolicitudesBD;
import exceptions.InvalidDataException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author MSI
 */
public class SolicitudesService {

    SolicitudesBD solicitudBD = new SolicitudesBD();
    Util util = new Util();
    
      
    public Solicitudes crearSOlicitud(Solicitudes solicitud) throws InvalidDataException{
        
        validar(solicitud);
      
    return solicitudBD.crearSolicitud(solicitud);
    }
    
    public boolean ExisteSolicitud(String codigoUsuario, String codigoOferta){
    return solicitudBD.ExisteSolicitud(codigoUsuario, codigoOferta);
    }
    
    
    public void validar(Solicitudes solicitud) throws InvalidDataException{
          if (!util.ValidarLenght(solicitud.getMensaje(), 200) ) {
            throw new InvalidDataException("el Mensaje es demasiado grande");
        }
          
 
    }
    
}
