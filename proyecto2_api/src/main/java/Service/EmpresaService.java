/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Empresa;
import Datos.Oferta;
import DatosBD.EmpresaBD;
import exceptions.NotFoundException;

/**
 *
 * @author MSI
 */
public class EmpresaService {
    EmpresaBD empresaBD = new EmpresaBD();
    
       public Empresa getEmpresa(String codigo) throws NotFoundException {
    
        Empresa empresa = empresaBD.getEmpresa(codigo);
        
      if(empresa!=null){
       
            return empresa;
        }else{
        
            throw new NotFoundException("No se encontró la categoria");
        }
    
    
    }
    
}
