package Controller;

import Datos.Categoria;
import Datos.JsonUtil;
import DatosBD.GestionCategoriaBD;
import Service.CategoriaService;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class CategoriaController {

    JsonUtil jsonUtil = new JsonUtil();

    public void getCategorias(String codigo, HttpServletResponse response) throws IOException, NotFoundException {

        if (codigo == null) {
            GestionCategoriaBD categoria = new GestionCategoriaBD();

            // CategoriaService categoriaService = new CategoriaService( categoria);
            List<Categoria> categorias = categoria.getCategorias();
            jsonUtil.EnviarJson(response, categorias);

        } else {

            CategoriaService categoriaService = new CategoriaService();
            Categoria categoriaCreada = categoriaService.getCategoria(codigo);

            jsonUtil.EnviarJson(response, categoriaCreada);

        }

    }

    public void CrearSolicitud(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        CategoriaService categoriaService = new CategoriaService();

        Categoria categoriaFE = (Categoria) jsonUtil.JsonStringAObjeto(body, Categoria.class);

        Categoria categoria = categoriaService.crearCategoria(categoriaFE);

        jsonUtil.EnviarJson(response, categoria);

    }

    public void ActualizarCategoria(String body, HttpServletResponse response) throws IOException, InvalidDataException {

        Categoria categoriaFE = (Categoria) jsonUtil.JsonStringAObjeto(body, Categoria.class);

        System.out.println(categoriaFE.toString());

        CategoriaService categoriaService = new CategoriaService();

        Categoria categoria = categoriaService.actualizarCategoria(categoriaFE);

        jsonUtil.EnviarJson(response, categoria);

    }

}
