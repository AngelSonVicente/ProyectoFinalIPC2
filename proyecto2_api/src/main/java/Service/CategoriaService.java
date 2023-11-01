/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Categoria;
import DatosBD.GestionCategoriaBD;
import java.util.List;

/**
 *
 * @author MSI
 */
public class CategoriaService {
    GestionCategoriaBD categoria;
    
    public List<Categoria> getCategorias(){
    return categoria.getCategorias();
    }
}
