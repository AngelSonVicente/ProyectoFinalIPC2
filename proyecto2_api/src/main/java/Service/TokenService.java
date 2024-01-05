/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.JsonUtil;
import Datos.RecuperarCuenta;
import DatosBD.ConexionBD;
import DatosBD.TokenBD;
import exceptions.InvalidDataException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author MSI
 */
public class TokenService {
    
    JsonUtil jsonUtil = new JsonUtil();
    private TokenBD tokenBD = new TokenBD();
    
    public RecuperarCuenta CrearToken(RecuperarCuenta cuenta) throws InvalidDataException {
         LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        cuenta.setFecha(fechaActual.format(formatoFecha));
        cuenta.setHora(horaActual.format(formatoHora));
        
        validarToken(cuenta);
        return tokenBD.CrearToken(cuenta);
    }
    public RecuperarCuenta getInformacionToken(RecuperarCuenta cuenta) throws InvalidDataException{
        validarGetToken(cuenta);
     return tokenBD.getInformacionToken(cuenta);
    }
    
    
    
     public void validarToken(RecuperarCuenta cuenta) throws InvalidDataException {
        if(cuenta.getToken()==null || cuenta.getCodigo()==null || cuenta.getFecha()==null|| cuenta.getHora()==null
         ||cuenta.getToken().isEmpty() || cuenta.getCodigo().isEmpty() || cuenta.getFecha().isEmpty() || cuenta.getHora().isEmpty()){
            throw new InvalidDataException("Faltan Datos");
        }
    }
     
     public void validarGetToken(RecuperarCuenta cuenta) throws InvalidDataException {
        if(cuenta.getToken()==null || cuenta.getToken().isEmpty()){
            throw new InvalidDataException("Faltan Datos");
        }
    }
     
     
    
}
