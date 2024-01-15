/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.CompletarPerfilUsuario;
import Datos.JsonUtil;
import DatosBD.ConexionBD;
import DatosBD.PreferenciasBD;
import exceptions.InvalidDataException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author MSI
 */
public class PreferenciasService {

    private Connection conexion;
    private JsonUtil jsonUtil = new JsonUtil();

    public PreferenciasService(Connection conexion) {
        this.conexion = conexion;
    }

    public PreferenciasService() {
        conexion = ConexionBD.getInstancia().getConexion();
    }

    public List<String> getPreferencias(String codigo, HttpServletResponse response) throws InvalidDataException, IOException {

        List<String> preferencias = getPreferenciasBD(codigo);
        jsonUtil.EnviarListaJson(response, preferencias);

        return null;
    }

    public boolean EliminarPreferencias(String codigoUsuario) throws InvalidDataException {
        if (codigoUsuario == null || codigoUsuario.isEmpty()) {
            throw new InvalidDataException("El codigo no es valido");

        }
        PreferenciasBD preferenciasBD = new PreferenciasBD(conexion);

        return preferenciasBD.eliminarPreferencias(codigoUsuario);
    }

    public CompletarPerfilUsuario ingresarPreferencias(CompletarPerfilUsuario perfilUsuario) throws InvalidDataException {

        if(perfilUsuario.getCodigoUsuario()==null || perfilUsuario.getCategorias()==null ||
           perfilUsuario.getCodigoUsuario().isEmpty() || perfilUsuario.getCategorias().isEmpty()     ){
                throw new InvalidDataException("faltan datos");

        }
        
        
        PreferenciasBD preferenciasBD = new PreferenciasBD(conexion);
        //hacer otra validacion por si acaso, aunque ya se hizo en CompletarPerfilService
        return preferenciasBD.ingresarPreferencias(perfilUsuario);
    }

    public List<String> getPreferenciasBD(String Codigo) throws InvalidDataException {
        if (Codigo == null || Codigo.isEmpty()) {
            throw new InvalidDataException("El codigo no es valido");
        }
        PreferenciasBD preferenciasBD = new PreferenciasBD(conexion);

        return preferenciasBD.getPreferencias(Codigo);
    }

}
