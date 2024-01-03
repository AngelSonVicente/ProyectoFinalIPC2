/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.CompletarPerfilUsuario;
import Datos.Empresa;
import Datos.JsonUtil;
import DatosBD.ConexionBD;
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

    public void CompletarPerfil(String body, Part filePart, HttpServletResponse response) throws IOException {

        System.out.println("COnexion en el completar service: " + conexion);
        CompletarPerfilUsuario perfilUsuario = (CompletarPerfilUsuario) jsonUtil.JsonStringAObjeto(body, CompletarPerfilUsuario.class);
        Empresa perfilEmpresa = (Empresa) jsonUtil.JsonStringAObjeto(body, Empresa.class);

        //saber si es una empresa 
        if (perfilEmpresa.getCodigoEmpresa() != null && perfilEmpresa.getMision() != null && perfilEmpresa.getVision() != null
                && perfilEmpresa.getNumeroTarjeta() != null && perfilEmpresa.getTitularTarjeta() != null && perfilEmpresa.getCodigoSeguridad() != null
                && !perfilEmpresa.getTelefonos().isEmpty()) {

            // meter los datos a telefonos y Empresa
        } else {
            // si es Usuario y si el archivo es un pdf
            if (perfilUsuario.getCodigoUsuario() != null && !perfilUsuario.getTelefonos().isEmpty() && !perfilUsuario.getCategorias().isEmpty()
                    && filePart != null && filePart.getContentType().equals("application/pdf")) {

                //ingresar las preferencias y telefonos a la BD al igual que actualizar el pdf 
                TelefonosService telefonoService = new TelefonosService(conexion);
                PreferenciasService preferenciasService = new PreferenciasService(conexion);
                CurriculumService curriculumService = new CurriculumService(conexion);

          
        
                try {
                    // Iniciar transacción
                    conexion.setAutoCommit(false);

                    // Realizar operaciones de cada servicio
                    telefonoService.ingresarTelefonos(perfilUsuario);
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

            } else {

                     response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       
            }

        }

        System.out.println("USuario:  " + perfilUsuario.toString());
        if (perfilEmpresa == null) {
            System.out.println("LA empresa llegó como null");
        }
        System.out.println("------------------------------------------");
        System.out.println("Empresa: " + perfilEmpresa.toString());

        if (perfilUsuario.getTelefonos().isEmpty()) {
            System.out.println("no hay telefonos");
        }

    }

}
