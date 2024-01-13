/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Comision;
import Datos.Empresa;
import Datos.JsonUtil;
import Datos.Oferta;
import DatosBD.ConexionBD;
import DatosBD.EmpresaBD;
import static Service.CompletarPerfilService.conexion;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

    JsonUtil jsonUtil = new JsonUtil();

    public void ActualizarDatosEmpresa(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        Empresa empresa = (Empresa) jsonUtil.JsonStringAObjeto(body, Empresa.class);

        System.out.println(empresa.toString());
            TelefonosService telefonoService = new TelefonosService(conexion);

        //si viene algun campo del metodo de pago vacio, solo se actualiza la mision y la vision
        if (empresa.getTitularTarjeta() == null || empresa.getNumeroTarjeta() == null || empresa.getCodigoSeguridad() == null
                || empresa.getTitularTarjeta().isEmpty() || empresa.getNumeroTarjeta().isEmpty() || empresa.getCodigoSeguridad().isEmpty()) {
            //actualizar Mision Vision y telefonos


            try {
                // Iniciar transacción
                conexion.setAutoCommit(false);

                //el telefonos Service tiene su propia validacion
                telefonoService.EliminarTelefonos(empresa.getCodigoEmpresa());
                telefonoService.ingresarTelefonos(empresa.getCodigoEmpresa(), empresa.getTelefonos());
                ActualizarMisionVisionEmpresa(empresa);
                
                
// Confirmar la transacción si todas las operaciones fueron exitosas
                conexion.commit();


            } catch (SQLException e) {
                // En caso de error, deshacer la transacción

                try {
                    conexion.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
                e.printStackTrace();
                
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } finally {
                // Restaurar el modo de autocommit y cerrar la conexión
                try {
                    conexion.setAutoCommit(true);
                response.setStatus(HttpServletResponse.SC_OK);

                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }

        } else {
            
            //actualizar  metodo de pago, telefonos, mision y vision
            
            
          try {
                // Iniciar transacción
                conexion.setAutoCommit(false);

                //el telefonos Service tiene su propia validacion
                telefonoService.EliminarTelefonos(empresa.getCodigoEmpresa());
                telefonoService.ingresarTelefonos(empresa.getCodigoEmpresa(), empresa.getTelefonos());
                ActualizarEmpresa(empresa);
                
                
// Confirmar la transacción si todas las operaciones fueron exitosas
                conexion.commit();

         
            } catch (SQLException e) {
                // En caso de error, deshacer la transacción

                try {
                    conexion.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } finally {
                // Restaurar el modo de autocommit y cerrar la conexión
                try {
                    conexion.setAutoCommit(true);
                    
                response.setStatus(HttpServletResponse.SC_OK);

                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }

        }

    }

    public Empresa getEmpresa(String codigo) throws NotFoundException {
        EmpresaBD empresaBD = new EmpresaBD(conexion);

        Empresa empresa = empresaBD.getEmpresa(codigo);

        if (empresa.getCodigoEmpresa() != null || !empresa.getCodigoEmpresa().isEmpty()) {

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

    public boolean ActualizarEmpresa(Empresa empresa) throws InvalidDataException {
        validar(empresa);
        EmpresaBD empresaBD = new EmpresaBD(conexion);
        return empresaBD.actualizarEmpresa(empresa);
    }

    public boolean ActualizarMisionVisionEmpresa(Empresa empresa) throws InvalidDataException {

        if (empresa.getCodigoEmpresa() == null || empresa.getMision() == null || empresa.getVision() == null
                || empresa.getCodigoEmpresa().isEmpty() || empresa.getMision().isEmpty() || empresa.getVision().isEmpty()) {
            throw new InvalidDataException("Faltan Datos");
        }
        EmpresaBD empresaBD = new EmpresaBD(conexion);
        return empresaBD.actualizarMisionVisionEmpresa(empresa);
    }

    public void validar(Empresa empresa) throws InvalidDataException {
        if (empresa.getCodigoEmpresa() == null || empresa.getMision() == null || empresa.getVision() == null
                || empresa.getTitularTarjeta() == null || empresa.getNumeroTarjeta() == null || empresa.getCodigoSeguridad() == null
                || empresa.getCodigoEmpresa().isEmpty() || empresa.getMision().isEmpty() || empresa.getVision().isEmpty()
                || empresa.getTitularTarjeta().isEmpty() || empresa.getNumeroTarjeta().isEmpty() || empresa.getCodigoSeguridad().isEmpty()) {
            throw new InvalidDataException("Faltan Datos");
        }

    }

}
