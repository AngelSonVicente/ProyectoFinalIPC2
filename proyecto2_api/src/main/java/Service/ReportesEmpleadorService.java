/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Entrevista;
import Datos.HistorialCobros;
import Datos.Oferta;
import DatosBD.ReportesEmpleador;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ReportesEmpleadorService {

    ReportesEmpleador reportes = new ReportesEmpleador();

    public List<Oferta> Reporte1(String codigoEmpresa, String fecha1, String fecha2) {
        if (fecha1 == "sinfecha" && fecha2 == "sinfecha") {
            return reportes.Reporte1Fecha(codigoEmpresa, fecha1, fecha2);
        }
        return reportes.Reporte1(codigoEmpresa);
    }

    public List<Entrevista> Reporte2Fecha(String codigoUsuario, String fecha) {
        System.out.println("Reporte 2 Empleador:  codigoUsuario: " + codigoUsuario+"  fecha: "+fecha );

        if (!fecha.equalsIgnoreCase("sinfecha")) {
            return reportes.Reporte2Fecha(codigoUsuario, fecha);
        }else{
        
        return reportes.Reporte2(codigoUsuario);
        }
    }

    public List<HistorialCobros> Reporte3(String codigoEmpresa) {
        return reportes.Reporte3(codigoEmpresa);
    }
}
