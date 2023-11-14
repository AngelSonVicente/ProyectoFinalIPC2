
package Service;

import Datos.Entrevista;
import DatosBD.EntrevistaBD;
import exceptions.InvalidDataException;
import java.util.List;

/**
 *
 * @author MSI
 */
public class EntrevistaService {
    private EntrevistaBD entrevistaBD = new EntrevistaBD();
    
    
      public List<Entrevista> getEntrevistasOferta(String codigo) {
        return entrevistaBD.getEntrevistasOferta(codigo);
    }
      public Entrevista finalizarEntrevista(Entrevista entrevista) throws InvalidDataException{
          validarFinalizacion(entrevista);
      return entrevistaBD.finalizarEntrevista(entrevista);
      }
      
      public List<Entrevista> getEntrevistasUsuario(String codigo) {
        return entrevistaBD.getEntrevistasUsuario(codigo);
    }
      
    public Entrevista crearEntrevista(Entrevista entrevista) throws InvalidDataException{
    validar(entrevista);
    return entrevistaBD.crearEntrevista(entrevista);
    }
    
    
       public void validar(Entrevista entrevista) throws InvalidDataException{
          if (entrevista.getCodigoOferta().isEmpty() || entrevista.getCodigoUsuario().isEmpty() ||  entrevista.getFecha().isEmpty()|| entrevista.getHora().isEmpty() ||
                  entrevista.getUbicacion().isEmpty() || entrevista.getCodigoOferta()==null || entrevista.getCodigoUsuario()==null ||  entrevista.getFecha()==null|| entrevista.getHora()==null ||
                  entrevista.getUbicacion()==null ) {
            throw new InvalidDataException("Faltan Datos");
        }
     
          
 
    }
    
        public void validarFinalizacion(Entrevista entrevista) throws InvalidDataException{
          if (entrevista.getCodigo().isEmpty() || entrevista.getNota().isEmpty() || entrevista.getCodigo()==null ||
                  entrevista.getNota()==null ) {
            throw new InvalidDataException("Faltan Datos");
        }
        }
}
