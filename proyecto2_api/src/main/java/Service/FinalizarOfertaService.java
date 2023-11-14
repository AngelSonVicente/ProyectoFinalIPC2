/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.FinalizarOferta;
import DatosBD.FinalizarOfertaBD;
import DatosBD.OfertaBD;
import DatosBD.SolicitudesBD;

/**
 *
 * @author MSI
 */
public class FinalizarOfertaService {
    FinalizarOfertaBD finalizar = new FinalizarOfertaBD();
    SolicitudesBD soli = new SolicitudesBD();
    
    public FinalizarOferta finalizarOferta(FinalizarOferta finalizarOferta){
      
        
        finalizarOferta.setCodigoSolicitud(soli.getSolicitudOU(finalizarOferta.getCodigoUsuarioElegido(), finalizarOferta.getCodigoOferta()));
        
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Si jalo");
        System.out.println("oferta: "+finalizarOferta.getCodigoOferta() );
        System.out.println("soli: "+finalizarOferta.getCodigoSolicitud());
        System.out.println("usuario: "+finalizarOferta.getCodigoUsuarioElegido());
        System.out.println("empresa: "+finalizarOferta.getCodigoEmpresa());
       
       
        finalizar.actualizarEstadoOferta(finalizarOferta);
        finalizar.actualizarEstadoSolicitud(finalizarOferta);
        finalizar.realizarPago(finalizarOferta);
    
    return finalizarOferta;
    }
    
    
}
