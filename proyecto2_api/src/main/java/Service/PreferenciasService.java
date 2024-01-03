/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.CompletarPerfilUsuario;
import DatosBD.ConexionBD;
import DatosBD.PreferenciasBD;
import java.sql.Connection;

/**
 *
 * @author MSI
 */
public class PreferenciasService {

    private Connection conexion;

    public PreferenciasService(Connection conexion) {
        this.conexion = conexion;
    }

    public PreferenciasService() {
        conexion = ConexionBD.getInstancia().getConexion();
    }


    public CompletarPerfilUsuario ingresarPreferencias(CompletarPerfilUsuario perfilUsuario) {

     PreferenciasBD preferenciasBD = new PreferenciasBD(conexion);
        //hacer otra validacion por si acaso, aunque ya se hizo en CompletarPerfilService
        return preferenciasBD.ingresarPreferencias(perfilUsuario);
    }

}
