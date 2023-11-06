/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Categoria;
import Datos.Comision;
import Datos.Util;
import DatosBD.ComisionBD;
import DatosBD.GestionCategoriaBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ComisionService {
    GestionCategoriaBD categoria = new GestionCategoriaBD();
   ComisionBD comision = new ComisionBD();
    Util util = new Util();

  
    
    
    public List<Comision> getHistorialComision(){
    return comision.getHistorialComisiones();
    }
    
    public Comision crearComision(Comision comi) throws InvalidDataException{
        
            LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato para obtener solo la fecha
        String fecha= fechaActual.format(formato);
        System.out.println(fecha);
        comi.setFecha(fecha);
        
        
        validar(comi);
      
        
        
        
      
    return comision.CrearComision(comi);
    }
    

    
    public Comision getComision(){
        return comision.getComision();
    }
    
    public void validar(Comision comision) throws InvalidDataException{
          if (comision.getComision()==0 || comision.getFecha().isEmpty() ) {
            throw new InvalidDataException("Faltan Datos");
        }
          
          if (comision.getComision()<0 ) {
            throw new InvalidDataException("No se puede ingresar un numero negativo");
        }
          

      
    
    }

    
}
