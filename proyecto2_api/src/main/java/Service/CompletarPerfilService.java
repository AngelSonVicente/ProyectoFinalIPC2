/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.CompletarPerfilUsuario;
import Datos.Empresa;
import Datos.JsonUtil;
import DatosBD.ConexionBD;
import exceptions.InvalidDataException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class CompletarPerfilService {

    private JsonUtil jsonUtil = new JsonUtil();
    static Connection conexion = ConexionBD.getInstancia().getConexion();

    public void CompletarPerfil(String body, Part filePart, HttpServletResponse response) throws IOException, InvalidDataException {

        
        CompletarPerfilUsuario perfilUsuario = (CompletarPerfilUsuario) jsonUtil.JsonStringAObjeto(body, CompletarPerfilUsuario.class);
        Empresa perfilEmpresa = (Empresa) jsonUtil.JsonStringAObjeto(body, Empresa.class);
        System.out.println("------------------------------------------");

        //saber si es una empresa 
        if (perfilEmpresa.getCodigoEmpresa() != null && perfilEmpresa.getMision() != null && perfilEmpresa.getVision() != null
                && perfilEmpresa.getNumeroTarjeta() != null && perfilEmpresa.getTitularTarjeta() != null && perfilEmpresa.getCodigoSeguridad() != null
                && !perfilEmpresa.getTelefonos().isEmpty()) {
            System.out.println("Empresa: " + perfilEmpresa.toString());

            // meter los datos a telefonos y Empresa
            CompletarPerfilEmpresa(perfilEmpresa, response);

        } else {
            // si es Usuario y si el archivo es un pdf
            if (perfilUsuario.getCodigoUsuario() != null && !perfilUsuario.getTelefonos().isEmpty() && !perfilUsuario.getCategorias().isEmpty()
                    && filePart != null && filePart.getContentType().equals("application/pdf")) {
                System.out.println("USuario:  " + perfilUsuario.toString());

                CompletarPerfilUsuario(perfilUsuario, filePart, response);

            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            }

        }

    }

    public void CompletarPerfilEmpresa(Empresa perfilEmpresa, HttpServletResponse response) throws InvalidDataException {

        TelefonosService telefonoService = new TelefonosService(conexion);
        EmpresaService empresaService = new EmpresaService(conexion);

        try {
            // Iniciar transacción
            conexion.setAutoCommit(false);

            // Realizar operaciones de cada servicio
            telefonoService.ingresarTelefonos(perfilEmpresa.getCodigoEmpresa(), perfilEmpresa.getTelefonos());
            empresaService.crearEmpresa(perfilEmpresa);
            // Confirmar la transacción si todas las operaciones fueron exitosas
            conexion.commit();

            response.setStatus(HttpServletResponse.SC_OK);

        } catch (SQLException e) {
            // En caso de error, deshacer la transacción
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            try {
                conexion.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // Restaurar el modo de autocommit y cerrar la conexión
            try {
                conexion.setAutoCommit(true);

            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

    }

    public void CompletarPerfilUsuario(CompletarPerfilUsuario perfilUsuario, Part filePart, HttpServletResponse response) throws InvalidDataException, IOException {

        //ingresar las preferencias y telefonos a la BD al igual que actualizar el pdf 
        TelefonosService telefonoService = new TelefonosService(conexion);
        PreferenciasService preferenciasService = new PreferenciasService(conexion);
        CurriculumService curriculumService = new CurriculumService(conexion);

        try {
            // Iniciar transacción
            conexion.setAutoCommit(false);

            // Realizar operaciones de cada servicio
            telefonoService.ingresarTelefonos(perfilUsuario.getCodigoUsuario(), perfilUsuario.getTelefonos());
            preferenciasService.ingresarPreferencias(perfilUsuario);
            curriculumService.IngresarCV(perfilUsuario.getCodigoUsuario(), filePart);

            // Confirmar la transacción si todas las operaciones fueron exitosas
            conexion.commit();

            response.setStatus(HttpServletResponse.SC_OK);

        } catch (SQLException e) {
            // En caso de error, deshacer la transacción
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            try {
                conexion.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // Restaurar el modo de autocommit y cerrar la conexión
            try {
                conexion.setAutoCommit(true);

            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

    }

}
