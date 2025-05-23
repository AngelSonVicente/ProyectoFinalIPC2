/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.CambiarPassword;
import Datos.JsonUtil;
import Datos.RecuperarCuenta;
import Datos.Usuario;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class ReestablecerPasswordService {

    UsuarioService usuarioService = new UsuarioService();

    JsonUtil jsonUtil = new JsonUtil();
    TokenService tokenService = new TokenService();
    
    public void Actualizarpassword(String Body, HttpServletResponse response) throws IOException, InvalidDataException, NotFoundException{
        
        UsuarioService usuarioService= new UsuarioService();
        
        CambiarPassword cambiarPassword= (CambiarPassword) jsonUtil.JsonStringAObjeto(Body, CambiarPassword.class);
        System.out.println(cambiarPassword.toString());
        
        if(cambiarPassword.getCodigoUsuario()==null || cambiarPassword.getPasswordAnterior() == null || cambiarPassword.getPasswordNuevo()==null ||
                cambiarPassword.getCodigoUsuario().isEmpty() || cambiarPassword.getPasswordAnterior().isEmpty() || cambiarPassword.getPasswordNuevo().isEmpty() ){
             throw new InvalidDataException("Faltan Datos");  
        }
        
        //buscamos la informacion del usuario 
        Usuario usuario= usuarioService.getUsuarioID(cambiarPassword.getCodigoUsuario());
        
        LoginService loginService = new LoginService();
        //verificar si la contraseña anterior es la misma
        if(loginService.IsLogin(cambiarPassword.getPasswordAnterior(), usuario.getUsuario())!=null){
        
            //cambiarPassword
            usuarioService.cambiarPassword(cambiarPassword.getCodigoUsuario(), cambiarPassword.getPasswordNuevo());
            
                 response.setStatus(HttpServletResponse.SC_OK);   
        }else{
        
            //mandar error 
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       
        }
        
    
    }

    public boolean ReestablecerPassword(String Body, HttpServletResponse response) throws InvalidDataException, IOException {

        RecuperarCuenta cuenta = (RecuperarCuenta) jsonUtil.JsonStringAObjeto(Body, RecuperarCuenta.class);

        //si el TOken Esta vencido lanza excepcion
        validarToken(cuenta);

        if (!usuarioService.cambiarPassword(cuenta.getCodigo(), cuenta.getPassword())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        } else {
            return true;
        }

    }

    public void getInformaiconToken(String token, HttpServletResponse response) throws InvalidDataException, IOException {
        RecuperarCuenta cuenta = new RecuperarCuenta(null, null, token, null, null, null);

        RecuperarCuenta informacionToken = tokenService.getInformacionToken(cuenta);

        //si el TOken Esta vencido lanza excepcion
        validarToken(informacionToken);

        jsonUtil.EnviarJson(response, informacionToken);

    }

    private void validarToken(RecuperarCuenta cuenta) throws InvalidDataException {
        System.out.println("Cuenta a validar: "+ cuenta.toString());
        boolean tokenVencido = false;
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalTime horaAlmacenada = LocalTime.parse(cuenta.getHora(), DateTimeFormatter.ofPattern("HH:mm:ss"));

        //verifica que en la hora no haya pasaod más de 10 minutos
        if (calcularDiferenciaEnMinutos(horaActual, horaAlmacenada) > 10) {
            tokenVencido = true;
        }

        if ((cuenta.getFecha().equals(fechaActual.format(formatoFecha)) && !tokenVencido)  ) {
        }else{
            throw new InvalidDataException("TOken Expirado");
        
        }

    }

    private static long calcularDiferenciaEnMinutos(LocalTime horaActual, LocalTime horaAlmacenada) {
        // Calcular la diferencia en minutos
        return Math.abs(horaActual.getMinute() - horaAlmacenada.getMinute());
    }
}
