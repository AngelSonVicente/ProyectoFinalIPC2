/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.CompletarPerfilUsuario;
import Datos.Empresa;
import Datos.JsonUtil;
import DatosBD.ConexionBD;
import static Service.CompletarPerfilService.conexion;
import exceptions.InvalidDataException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class EditarPerfilService {

    private JsonUtil jsonUtil = new JsonUtil();
    static Connection conexion = ConexionBD.getInstancia().getConexion();

    public void EditarPerfil(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        System.out.println("Entramos al Editar Perfil");
        System.out.println("body: "+ body);
        CompletarPerfilUsuario perfilUsuario = (CompletarPerfilUsuario) jsonUtil.JsonStringAObjeto(body, CompletarPerfilUsuario.class);
        System.out.println(perfilUsuario.toString());

        //actualizar telefonos y categoriss del usuario
        
        if(perfilUsuario.getCodigoUsuario()!=null && perfilUsuario.getTelefonos()!=null  && perfilUsuario.getCategorias()!=null ||
           !perfilUsuario.getCodigoUsuario().isEmpty() || perfilUsuario.getTelefonos().isEmpty() || perfilUsuario.getCategorias().isEmpty()){
        
            actualizarPerfilUsuario(perfilUsuario, response);
            
        
        }
        
        
        
        
    }
    
    
    public CompletarPerfilUsuario actualizarPerfilUsuario(CompletarPerfilUsuario perfilUsuario, HttpServletResponse response) throws InvalidDataException{
    
       
        
        //ingresar las preferencias y telefonos a la BD al igual que actualizar el pdf 
        TelefonosService telefonoService = new TelefonosService(conexion);
        PreferenciasService preferenciasService = new PreferenciasService(conexion);
       
        try {
            // Iniciar transacción
            conexion.setAutoCommit(false);
            
            System.out.println("Eliminando telefonos y categorias");
            //eliminar los registros primero, para luego volver a ingresar los datos actualizados
            telefonoService.EliminarTelefonos(String.valueOf(perfilUsuario.getCodigoUsuario()));
            preferenciasService.EliminarPreferencias(String.valueOf(perfilUsuario.getCodigoUsuario()));
            
            
            System.out.println("Ingresando datos nuevos");
            // Meter los datos nuevos
            telefonoService.ingresarTelefonos(perfilUsuario.getCodigoUsuario(), perfilUsuario.getTelefonos());
            preferenciasService.ingresarPreferencias(perfilUsuario);
           
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
            System.out.println("error: "+ e);
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
        
    return null;
    }

}
