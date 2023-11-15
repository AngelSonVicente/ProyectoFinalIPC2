/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.CategoriaReporte;
import Datos.EmpleadorReporte;
import Datos.HistorialComision;
import DatosBD.ReportesAdministradorBD;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ReportesAdministradorService {

    private ReportesAdministradorBD reportes = new ReportesAdministradorBD();

    public List<HistorialComision> Reporte1() {
        return reportes.Reporte1();
    }

    public List<EmpleadorReporte> Reporte2() {
        return reportes.Reporte2();
    }

    public List<EmpleadorReporte> Reporte3Fecha(String fecha1, String fecha2) {
        return reportes.Reporte3();
    }

    public List<CategoriaReporte> Reporte4() {
        return reportes.Reporte4();
    }
}
