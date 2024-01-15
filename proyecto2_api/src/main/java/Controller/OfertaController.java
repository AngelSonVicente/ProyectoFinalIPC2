/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Datos.Entrevista;
import Datos.Estado;
import Datos.JsonUtil;
import Datos.Notificaciones;
import Datos.Oferta;
import Datos.OfertaEliminada;
import Datos.Solicitudes;
import DatosBD.ConexionBD;
import Service.EntrevistaService;
import Service.NotificacionesService;
import Service.OfertaService;
import Service.SolicitudesService;
import Servlets.OfertaServlet;
import com.google.gson.Gson;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class OfertaController {

    JsonUtil jsonUtil = new JsonUtil();
    private String EstadoActivo = "Activo";
    static Connection conexion = ConexionBD.getInstancia().getConexion();


    public void GetOfertas(String codigo, String estado, String codigoEmpresa, String codigoUsuario, HttpServletResponse response) throws IOException, InvalidDataException, NotFoundException {
        OfertaService ofertaService = new OfertaService();

        if (codigo == null) {
            if (codigoEmpresa != null) {
                if (estado != null) {
                    List<Oferta> ofertas;
                    ofertas = ofertaService.getOfertasEmpresaEstados(codigoEmpresa, estado);
                    jsonUtil.EnviarListaJson(response, ofertas);

                } else {
                    List<Oferta> ofertas;
                    ofertas = ofertaService.getOfertasEmpresa(codigoEmpresa);
                    jsonUtil.EnviarListaJson(response, ofertas);
                }

            } else {

                List<Oferta> ofertas;
                if (codigoUsuario != null) {
                    System.out.println("enviando getOfertasPreferencia");

                    ofertas = ofertaService.getOfertasPreferencias(codigoUsuario);
                } else {
                    System.out.println("enviando getOfertas");

                    ofertas = ofertaService.getOfertas();

                }
                jsonUtil.EnviarListaJson(response, ofertas);

            }

        } else {

            Oferta oferta = ofertaService.getOferta(codigo);

            jsonUtil.EnviarJson(response, oferta);

        }

    }

    public void CrearOferta(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        OfertaService ofertaService = new OfertaService();

        Oferta oferta = (Oferta) jsonUtil.JsonStringAObjeto(body, Oferta.class);
        oferta.setEstado(EstadoActivo);
        Oferta ofertaaa = ofertaService.crearOferta(oferta);

        jsonUtil.EnviarJson(response, ofertaaa);

    }
    
     public void ActualizarOferta(String body, HttpServletResponse response) throws IOException, InvalidDataException {
     OfertaService ofertaService = new OfertaService();
         Oferta ofertaFE = (Oferta) jsonUtil.JsonStringAObjeto(body, Oferta.class);

        System.out.println("Objeto recibido: " + ofertaFE.toString());

        //actualizar la oferta
        Oferta oferta = ofertaService.actualizarOfertaBD(ofertaFE);

        //enviar notificacion 
        //entrevista enviar a usuario con entrevistas 
        NotificacionesService notificacionService = new NotificacionesService(conexion);

        //Activo, Seleccion enviar notificacion  a usuarios con solicitudes
        if (ofertaFE.getEstado().equals(Estado.Activo.name()) || ofertaFE.getEstado().equals("Seleccion")) {

            System.out.println("en fase de seleccion ");
            SolicitudesService solciitudesService = new SolicitudesService();

            List<Solicitudes> solicitudesOferta = solciitudesService.getSolicitudesOferta(ofertaFE.getCodigo());

            //enviar notificacion a todas los usuarios con solicitud en la oferta
            for (Solicitudes solicitud : solicitudesOferta) {
                System.out.println(solicitud.toString());

                Notificaciones notificacion = new Notificaciones(null, ofertaFE.getCodigoEmpresa(), null, solicitud.getCodigoUsuario(), null, ofertaFE.getCodigo(), null, "Se ha modificado la oferta", null, null);

                notificacionService.CrearNotificacion(notificacion);

            }

        }

        if (ofertaFE.getEstado().equals(Estado.Entrevista.name())) {

            EntrevistaService entrevistaService = new EntrevistaService();

            List<Entrevista> entrevistasOferta = entrevistaService.getEntrevistasOferta(ofertaFE.getCodigo());

            //enviar notificacion a todas los usuarios con entrevista en la oferta
            for (Entrevista entrevista : entrevistasOferta) {
                Notificaciones notificacion = new Notificaciones(null, ofertaFE.getCodigoEmpresa(), null, entrevista.getCodigoUsuario(), null, ofertaFE.getCodigo(), null, "Se ha modificado la oferta", null, null);

                notificacionService.CrearNotificacion(notificacion);

            }

        }

        jsonUtil.EnviarJson(response, oferta);

    }
     
     

    public void EliminarOferta(OfertaEliminada ofertaEliminada, HttpServletResponse response) throws NotFoundException, InvalidDataException {

        OfertaService ofertaService = new OfertaService();
        System.out.println("oferta eliminada " + ofertaEliminada.toString());

        OfertaEliminada oferta = ofertaService.eliminarOfertaBD(ofertaEliminada);

        if (oferta != null) {

            SolicitudesService solicitudesService = new SolicitudesService();
            EntrevistaService entrevistaService = new EntrevistaService();

            List<Solicitudes> solicitudesOferta = solicitudesService.getSolicitudesOferta(ofertaEliminada.getCodigoOferta());
            List<Entrevista> entrevistasOferta = entrevistaService.getEntrevistasOferta(ofertaEliminada.getCodigoOferta());

            NotificacionesService notificacionesService = new NotificacionesService(conexion);

            Notificaciones notificacion = new Notificaciones(null, null, null, null, null, ofertaEliminada.getCodigoOferta(), null, "Se ha eliminado la oferta de empleo. MOtivo: " + ofertaEliminada.getMotivo(), null, null);

            for (Solicitudes solicitud : solicitudesOferta) {
                notificacion.setCodigoUsuarioDestino(solicitud.getCodigoUsuario());
                notificacionesService.CrearNotificacion(notificacion);

            }

            for (Entrevista entrevista : entrevistasOferta) {
                notificacion.setCodigoUsuarioDestino(entrevista.getCodigoUsuario());
                notificacionesService.CrearNotificacion(notificacion);

            }

            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    

}
