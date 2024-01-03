/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.CompletarPerfilUsuario;
import DatosBD.ConexionBD;
import DatosBD.TelefonosBD;
import java.sql.Connection;

/**
 *
 * @author MSI
 */
public class TelefonosService {

    private Connection conexion;

    public TelefonosService(Connection conexion) {
        this.conexion = conexion;
    }

    public TelefonosService() {
        conexion = ConexionBD.getInstancia().getConexion();
    }

    public CompletarPerfilUsuario ingresarTelefonos(CompletarPerfilUsuario perfilUsuario) {
        //si dejo el objeto fuera del metodo toma la conexion como null
        //pero si esta dentro del metodo si funciona y manda la conexion
        TelefonosBD telefonosBD = new TelefonosBD(conexion);
        System.out.println("conexion en telefonos service: " + conexion);
        return telefonosBD.ingresarTelefonos(perfilUsuario);
    }

}
