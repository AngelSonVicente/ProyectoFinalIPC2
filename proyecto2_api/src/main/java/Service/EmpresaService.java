/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Comision;
import Datos.Empresa;
import Datos.Oferta;
import DatosBD.ConexionBD;
import DatosBD.EmpresaBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import java.sql.Connection;

/**
 *
 * @author MSI
 */
public class EmpresaService {

    private Connection conexion;

    public EmpresaService(Connection conexion) {
        this.conexion = conexion;
    }

    public EmpresaService() {
        this.conexion = ConexionBD.getInstancia().getConexion();
    }

    public Empresa getEmpresa(String codigo) throws NotFoundException {
        EmpresaBD empresaBD = new EmpresaBD(conexion);

        Empresa empresa = empresaBD.getEmpresa(codigo);

        if (empresa != null) {

            return empresa;
        } else {

            throw new NotFoundException("No se encontró la empresa");
        }

    }

    public Empresa crearEmpresa(Empresa empresa) throws InvalidDataException {
        EmpresaBD empresaBD = new EmpresaBD(conexion);
        
        validar(empresa);
        return empresaBD.crearEmpresa(empresa);
    }
    
    
        public void validar(Empresa empresa) throws InvalidDataException{
          if (empresa.getCodigoEmpresa()==null || empresa.getMision()==null || empresa.getVision()==null ||
              empresa.getTitularTarjeta()==null || empresa.getCodigoSeguridad()==null ||
              empresa.getCodigoEmpresa().isEmpty() || empresa.getMision().isEmpty()|| empresa.getVision().isEmpty()||
              empresa.getTitularTarjeta().isEmpty()|| empresa.getCodigoSeguridad().isEmpty()  
                  ) {
            throw new InvalidDataException("Faltan Datos");
        }
          
        
    }

}
