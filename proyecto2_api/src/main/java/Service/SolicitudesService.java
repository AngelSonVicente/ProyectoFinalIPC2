/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Comision;
import Datos.Oferta;
import Datos.SolicitudRetirada;
import Datos.Solicitudes;
import Datos.Util;
import DatosBD.SolicitudesBD;
import DatosBD.SolicitudesRetiradasBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author MSI
 */
public class SolicitudesService {

    SolicitudesBD solicitudBD = new SolicitudesBD();
    OfertaService ofertaService = new OfertaService();
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
     public Solicitudes actualizarEstadoSolicitud(Solicitudes solicitud) {
         //cambiar el estado de la oferta a Entrevista
       Solicitudes soli = solicitudBD.actualizarEstadoSolicitud(solicitud);
         
         List<Solicitudes> solicitudesOferta = getOfertasOferta(solicitud.getCodigoOferta());
                  System.out.println("codigo OFerta kjndkjnsadkjs_: " + solicitud.getCodigoOferta());
        
         if(SolicitudesEnEntrevista(solicitudesOferta)) {
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
        if(solicitudesOferta.isEmpty() || solicitudesOferta ==null){
            return true;
        }
    }
     System.out.println("ya no hay ningun activo cambiar");
    // Si todas las solicitudes están en estado "Entrevista", retorna true
    return true;
}
    
    
    public void validar(Solicitudes solicitud) throws InvalidDataException{
          if (!util.ValidarLenght(solicitud.getMensaje(), 200) ) {
            throw new InvalidDataException("el Mensaje es demasiado grande");
        }
          
 
    }
    
}
