package Controller;

import Datos.Empresa;
import Datos.JsonUtil;
import DatosBD.ConexionBD;
import Service.EmpresaService;
import Service.TelefonosService;
import com.google.gson.Gson;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class EmpresaController {

    JsonUtil jsonUtil = new JsonUtil();
    static Connection conexion = ConexionBD.getInstancia().getConexion();

    public void getEmpresa(String codigo, HttpServletResponse response) throws NotFoundException, IOException {

        EmpresaService empresaService = new EmpresaService();

        if (codigo != null) {

            Empresa empresa = empresaService.getEmpresa(codigo);

            jsonUtil.EnviarJson(response, empresa);

        }

    }

    public void ActualizarDatosEmpresa(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        EmpresaService empresaService = new EmpresaService(conexion);
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
                empresaService.ActualizarMisionVisionEmpresa(empresa);

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
                empresaService.ActualizarEmpresa(empresa);

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

}
