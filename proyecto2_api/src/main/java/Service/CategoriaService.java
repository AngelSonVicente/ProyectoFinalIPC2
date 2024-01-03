/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Categoria;
import Datos.JsonUtil;
import Datos.Util;

import DatosBD.GestionCategoriaBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class CategoriaService {

    GestionCategoriaBD categoria = new GestionCategoriaBD();
    Util util = new Util();
    private JsonUtil jsonUtil = new JsonUtil();

  
    

    public void ProcesarSolicitud(String body, HttpServletResponse response ) throws IOException {

       
        try {
        Categoria categoriaFE = (Categoria) jsonUtil.JsonStringAObjeto(body, Categoria.class);


           Categoria categoria = crearCategoria(categoriaFE);

             jsonUtil.EnviarJson(response, categoria);
        

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (InvalidDataException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       
        }

    }

    public List<Categoria> getCategorias() {
        return categoria.getCategorias();
    }

    public Categoria crearCategoria(Categoria cat) throws InvalidDataException {
        validar(cat);
        return categoria.CrearCategoria(cat);
    }

    public Categoria actualizarCategoria(Categoria cat) throws InvalidDataException {
        validar(cat);
        return categoria.actualizarCategoria(cat);
    }

    public boolean ExisteNombre(String nombre) {
        return categoria.ExiteCategoria(nombre);
    }

    public Categoria getCategoria(String codigo) throws NotFoundException {
        Categoria cate = categoria.getCategoria(codigo);
        if (cate != null) {

            return cate;
        } else {

            throw new NotFoundException("No se encontró la categoria");
        }

    }

    public void validar(Categoria cat) throws InvalidDataException {
        if (cat.getNombre().isEmpty() || cat.getDescripcion().isEmpty()) {
            throw new InvalidDataException("Faltan Datos");
        }
        if (!util.ValidarLenght(cat.getNombre(), 100) || !util.ValidarLenght(cat.getDescripcion(), 200)) {
            throw new InvalidDataException("Alguno de los datos exedio el limite de caracteres");

        }
        if (ExisteNombre(cat.getNombre())) {
            throw new InvalidDataException("La categoria ya existe");
       
        }

    }

}
