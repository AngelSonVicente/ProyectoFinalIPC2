/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.CompletarPerfilUsuario;
import Datos.Empresa;
import DatosBD.ConexionBD;
import DatosBD.TelefonosBD;
import exceptions.InvalidDataException;
import java.sql.Connection;
import java.util.List;

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

    public CompletarPerfilUsuario ingresarTelefonos(String codigoUsuario, List<String> telefonos) throws InvalidDataException {
        //si dejo el objeto fuera del metodo toma la conexion como null
        //pero si esta dentro del metodo si funciona y manda la conexion
        TelefonosBD telefonosBD = new TelefonosBD(conexion);
        validar(codigoUsuario, telefonos);
        System.out.println("conexion en telefonos service: " + conexion);
        return telefonosBD.ingresarTelefonos(codigoUsuario, telefonos);
    }

    public void validar(String codigoUsuario, List<String> telefonos) throws InvalidDataException {
        if (codigoUsuario == null || telefonos == null || codigoUsuario.isEmpty() || telefonos.isEmpty()) {
            throw new InvalidDataException("Faltan Datos");
        }

    }
}
