/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Datos.Comision;
import Datos.JsonUtil;
import Service.ComisionService;
import com.google.gson.Gson;
import exceptions.InvalidDataException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ComisionController {

    JsonUtil jsonUtil = new JsonUtil();

    public void getComision(String list, HttpServletResponse response) throws IOException {

        ComisionService comisionService = new ComisionService();

        if (list == null) {

            // CategoriaService categoriaService = new CategoriaService( categoria);
            List<Comision> comisiones = comisionService.getHistorialComision();

            jsonUtil.EnviarListaJson(response, comisiones);
        } else {

            Comision comision = comisionService.getComision();

            jsonUtil.EnviarJson(response, comision);

        }

    }

    public void CrearComision(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        Comision comisionFE = (Comision) jsonUtil.JsonStringAObjeto(body, Comision.class);

        ComisionService comisionService = new ComisionService();

        Comision comisisionCreada = comisionService.crearComision(comisionFE);

        jsonUtil.EnviarJson(response, comisisionCreada);

    }

}
