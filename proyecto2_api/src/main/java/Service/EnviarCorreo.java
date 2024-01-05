/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.RecuperarCuenta;
import Datos.Usuario;
import exceptions.InvalidDataException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author MSI
 */
public class EnviarCorreo {

    private static String emailFrom = "angelson202030885@cunoc.edu.gt";
    private static String passwordFrom = "ubsbeseworliamys";
    private String emailTo;
    private String subject;
    private String content;
    private String token;

    private TokenService tokenService = new TokenService();
    private UsuarioService usuarioService = new UsuarioService();

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mEmail;

    public EnviarCorreo() {
        mProperties = new Properties();

    }

    public void createEmail(String email) throws InvalidDataException {
        System.out.println(email);
        boolean parar = false;

        do {
            token = generateToken();
            RecuperarCuenta cuenta = new RecuperarCuenta();
            cuenta.setToken(token);
            RecuperarCuenta existeToken = tokenService.getInformacionToken(cuenta);

            if (existeToken == null) {
                // No existe el token, salir del bucle
                parar = true;
            }

            // Si llega aquí, significa que el token ya existe, así que se generará uno nuevo en la próxima iteración
        } while (!parar);

        emailTo = email;
        subject = "Reestablecer Contraseña";
        content = "<h2>Termina de Reestablacer tu contraseña! </h2>  <br><br>     Ingresa a este enlace para Reestablecer tu contraseña: <br><br>  http://localhost:4200/Proyecto2/ResetPassword/" + token;

        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            mEmail = new MimeMessage(mSession);
            mEmail.setFrom(new InternetAddress(emailFrom));
            mEmail.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mEmail.setSubject(subject);
            mEmail.setText(content, "ISO-8859-1", "html");
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendEmail(String email) throws InvalidDataException {

        createEmail(email);
        
       Usuario usuario = usuarioService.getUsuarioCorreo(email);
        System.out.println("UsuariObtenido: "+ usuario.toString());
       
        RecuperarCuenta TokenBD = new RecuperarCuenta(String.valueOf(usuario.getCodigo()),null,token,null,null,null);
        
        
        // cuando llegue aqui, pos ya genero bien el token 
       
   
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mEmail, mEmail.getRecipients(Message.RecipientType.TO));
            System.out.println("Envio exitoso");
            System.out.println(emailTo);
            System.out.println(subject);
            System.out.println(content);

            //subir a la base ya que se envió el correo
            tokenService.CrearToken(TokenBD);
            mTransport.close();

        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Longitud predeterminada del token
    private static final int TOKEN_LENGTH = 32;

    private String generateToken() {
        // Genera una secuencia de bytes aleatoria
        byte[] randomBytes = new byte[TOKEN_LENGTH];
        new SecureRandom().nextBytes(randomBytes);

        // Convierte la secuencia de bytes a una cadena Base64
        String tokenn = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        return tokenn;
    }

}
