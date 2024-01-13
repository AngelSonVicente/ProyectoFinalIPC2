/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.JsonUtil;
import Datos.Usuario;
import DatosBD.ConexionBD;
import DatosBD.CurriculumBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author MSI
 */
public class CurriculumService {

    private Connection conexion;

    public CurriculumService(Connection conexion) {
        this.conexion = conexion;
    }

    public CurriculumService() {
        conexion = ConexionBD.getInstancia().getConexion();

    }
    UsuarioService usuarioSerice = new UsuarioService();
    JsonUtil jsonUtil = new JsonUtil();

    public void actualizarCurriculum(String codigo, Part pdfPart, HttpServletResponse response) throws InvalidDataException, IOException, NotFoundException {

        if (IngresarCV(codigo, pdfPart)) {
            //regresar el usuario con el cv Actualizado

            Usuario usuario = usuarioSerice.getUsuarioID(codigo);
            jsonUtil.EnviarJson(response, usuario);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        }

    }

    public boolean IngresarCV(String codigo, Part cvPart) throws IOException, InvalidDataException {

        if (codigo == null || codigo.isEmpty()) {
            throw new InvalidDataException("El codigo no es valido");

        }
        if (!cvPart.getContentType().equals("application/pdf")) {
            throw new InvalidDataException("El archivo ingresado, no es un archivo PDF");

        }

        CurriculumBD curriculumBD = new CurriculumBD(conexion);
        //validar que sea un pdf auqnue ya se validó en el Completar perfilService, pero este igual se va a usar para actualizar el cv
        return curriculumBD.IngresarCV(codigo, cvPart.getInputStream());

    }

}
