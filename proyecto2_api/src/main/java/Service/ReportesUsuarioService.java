/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Oferta;
import Datos.SolicitudRetirada;
import Datos.Solicitudes;
import DatosBD.ReportesUsuarioBD;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ReportesUsuarioService {
    ReportesUsuarioBD reportes = new ReportesUsuarioBD();
    String sinFecha="sinfecha";
     public List<SolicitudRetirada> Reporte1(String codigoUsuario, String fecha1, String fecha2) {
        if (!fecha1.equals(sinFecha) && !fecha2.equals(sinFecha)) {
            return reportes.Reporte1Fecha(codigoUsuario, fecha1, fecha2);
        }
        
        return reportes.Reporte1(codigoUsuario);
    }
     
     public List<Solicitudes> Reporte2(String codigoUsuario) {
     return reportes.Reporte2(codigoUsuario);
     }
     public List<Solicitudes> Reporte3(String codigoUsuario) {
     return reportes.Reporte3(codigoUsuario);
     }
     public List<Solicitudes> Reporte4(String codigoUsuario) {
     return reportes.Reporte4(codigoUsuario);
     }
     
    
}
