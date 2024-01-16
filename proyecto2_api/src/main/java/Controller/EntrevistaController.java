package Controller;

import Datos.Entrevista;
import Datos.Estado;
import Datos.JsonUtil;
import Datos.Notificaciones;
import Datos.Oferta;
import DatosBD.ConexionBD;
import Service.EntrevistaService;
import Service.NotificacionesService;
import Service.OfertaService;
import com.google.gson.Gson;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EntrevistaController {

    JsonUtil jsonUtil = new JsonUtil();
    static Connection conexion = ConexionBD.getInstancia().getConexion();

    public void getEntrevistas(String codigoUsuario, String codigoOferta, HttpServletResponse response) throws IOException, InvalidDataException {

        EntrevistaService entrevistaService = new EntrevistaService();
        if (codigoUsuario != null) {

            List<Entrevista> entrevistas = entrevistaService.getEntrevistasUsuario(codigoUsuario);

            jsonUtil.EnviarListaJson(response, entrevistas);
        }
        if (codigoOferta != null) {

            List<Entrevista> entrevistas = entrevistaService.getEntrevistasOferta(codigoOferta);
            jsonUtil.EnviarListaJson(response, entrevistas);
        }

    }

    public void CrearEntrevista(String Body, HttpServletResponse response) throws IOException, InvalidDataException, NotFoundException, SQLException {
        //Crear entrevista y actualizar estado SOlicitud a "Entrevista"
        Entrevista entrevista = (Entrevista) jsonUtil.JsonStringAObjeto(Body, Entrevista.class);
        entrevista.setEstado(Estado.Pendiente.name());

        EntrevistaService entrevistaService = new EntrevistaService(conexion);
        NotificacionesService notificacionesService = new NotificacionesService(conexion);
        OfertaService ofertaService = new OfertaService();

        Entrevista entrevistaAgendada = new Entrevista();

        //APLICAR TRANSACCION
        try {
            // Iniciar transacción
            conexion.setAutoCommit(false);

            entrevistaAgendada = entrevistaService.agendarEntrevista(entrevista);

            Oferta oferta = ofertaService.getOferta(entrevista.getCodigoOferta());

            //enviar notificacion a usuario de que ha conseguido la entrevista
            Notificaciones notificacion = new Notificaciones(null, "1", null, entrevista.getCodigoUsuario(), null, entrevista.getCodigoOferta(), null, "Se ha agendado una Entrevista En la oferta de empleo", null, null);
            //enviar notificacion
            notificacionesService.CrearNotificacion(notificacion);

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

          //  response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } finally {
            // Restaurar el modo de autocommit y cerrar la conexión
            try {
                conexion.setAutoCommit(true);
                //enviar json
                
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }
        jsonUtil.EnviarJson(response, entrevistaAgendada);


    }

    public void ActualizarEntrevista(String body, HttpServletResponse response) throws IOException, InvalidDataException {
        EntrevistaService entrevistaService = new EntrevistaService();

        Entrevista entrevista = (Entrevista) jsonUtil.JsonStringAObjeto(body, Entrevista.class);

        System.out.println("codigo: " + entrevista.getCodigo());
        System.out.println("nota: " + entrevista.getNota());

        Entrevista entrevistaFinalizada = entrevistaService.finalizarEntrevista(entrevista);
        jsonUtil.EnviarJson(response, entrevistaFinalizada);

    }

}
