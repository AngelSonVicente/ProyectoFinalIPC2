/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Categoria;
import Datos.Util;


import DatosBD.GestionCategoriaBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class CategoriaService {
    GestionCategoriaBD categoria = new GestionCategoriaBD();
    Util util = new Util();

  
    
    
    public List<Categoria> getCategorias(){
    return categoria.getCategorias();
    }
    
    public Categoria crearCategoria(Categoria cat) throws InvalidDataException{
        validar(cat);
            
      
    return categoria.CrearCategoria(cat);
    }
    
    public Categoria actualizarCategoria(Categoria cat) throws InvalidDataException{
    validar(cat);
    
    return categoria.actualizarCategoria(cat);
    }
    
    public Categoria getCategoria(String codigo)throws NotFoundException {
        Categoria cate = categoria.getCategoria(codigo);
        if(cate!=null){
       
            return cate;
        }else{
        
            throw new NotFoundException("No se encontró la categoria");
        }
    
    }
    
    public void validar(Categoria cat) throws InvalidDataException{
          if (cat.getNombre().isEmpty() || cat.getDescripcion().isEmpty() ) {
            throw new InvalidDataException("Faltan Datos");
        }

        if (!util.ValidarLenght(cat.getNombre(), 100) || !util.ValidarLenght(cat.getDescripcion(), 200)) {
            throw new InvalidDataException("Alguno de los datos exedio el limite de caracteres");
       
        }
    
    }


}
