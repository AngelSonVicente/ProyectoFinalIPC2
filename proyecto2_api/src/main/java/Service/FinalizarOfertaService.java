/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Entrevista;
import Datos.FinalizarOferta;
import Datos.JsonUtil;
import Datos.Notificaciones;
import Datos.Oferta;
import Datos.Solicitudes;
import DatosBD.ConexionBD;
import DatosBD.FinalizarOfertaBD;
import DatosBD.OfertaBD;
import DatosBD.SolicitudesBD;
import exceptions.InvalidDataException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class FinalizarOfertaService {

    static Connection conexion = ConexionBD.getInstancia().getConexion();

    FinalizarOfertaBD finalizar = new FinalizarOfertaBD();
    SolicitudesBD soli = new SolicitudesBD();
    EntrevistaService entrevistaService = new EntrevistaService();

    JsonUtil jsonUtil = new JsonUtil();

    public void FinalizarOFerta(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        FinalizarOferta datos = (FinalizarOferta) jsonUtil.JsonStringAObjeto(body, FinalizarOferta.class);

        FinalizarOferta finalizacionHecha = finalizarOfertaBD(datos);

        jsonUtil.EnviarJson(response, finalizacionHecha);

    }

    public FinalizarOferta finalizarOfertaBD(FinalizarOferta finalizarOferta) throws InvalidDataException {

        finalizarOferta.setCodigoSolicitud(soli.getSolicitudOU(finalizarOferta.getCodigoUsuarioElegido(), finalizarOferta.getCodigoOferta()));

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Si jalo");
        System.out.println("oferta: " + finalizarOferta.getCodigoOferta());
        System.out.println("soli: " + finalizarOferta.getCodigoSolicitud());
        System.out.println("usuario: " + finalizarOferta.getCodigoUsuarioElegido());
        System.out.println("empresa: " + finalizarOferta.getCodigoEmpresa());

        List<Entrevista> entrevistas = entrevistaService.getEntrevistasOferta(finalizarOferta.getCodigoOferta());
        NotificacionesService notificacionesService = new NotificacionesService(conexion);

        //APLICAR Transaccionalidad
        try {
            // Iniciar transacción
            conexion.setAutoCommit(false);

            finalizar.actualizarEstadoOferta(finalizarOferta);
            finalizar.actualizarEstadoSolicitud(finalizarOferta);
            finalizar.realizarPago(finalizarOferta);

            Notificaciones notificacionUsuarioElegido = new Notificaciones(null, finalizarOferta.getCodigoEmpresa(), null, finalizarOferta.getCodigoUsuarioElegido(), null, finalizarOferta.getCodigoOferta(), null, "Felicidades! ha conseguido el puesto en la oferta de empleo! Pongase en contacto con la empresa.", null, null);

            //enviar notificacion al usuario elegido
            notificacionesService.CrearNotificacion(notificacionUsuarioElegido);

            //enviar notificaion a los demás usuarios 
            for (Entrevista entrevista : entrevistas) {

                if (entrevista.getCodigoUsuario() != finalizarOferta.getCodigoUsuarioElegido()) {

                    Notificaciones notificacion = new Notificaciones(null, finalizarOferta.getCodigoEmpresa(), null, entrevista.getCodigoUsuario(), null, finalizarOferta.getCodigoOferta(), null, "La oferta de empleo ha finalizado, Lamentablemente usted no ha sido elegido para tomar el puesto. siga buscando mas ofertas!", null, null);

                    notificacionesService.CrearNotificacion(notificacion);

                }

            }

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

            return null;
            
        } finally {
            // Restaurar el modo de autocommit y cerrar la conexión
            try {
                conexion.setAutoCommit(true);
                //enviar json
                 return finalizarOferta;

            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }

        return finalizarOferta;
    }

    
    
    
    
    public void OfertaFinalizada(String codigo, HttpServletResponse response) throws InvalidDataException {
        OfertaService ofertaService = new OfertaService();

        if (ofertaService.OfertaFinalizada(codigo)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

}
