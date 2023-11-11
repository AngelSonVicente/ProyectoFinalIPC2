
package Service;

import Datos.Entrevista;
import DatosBD.EntrevistaBD;
import java.util.List;

/**
 *
 * @author MSI
 */
public class EntrevistaService {
    private EntrevistaBD entrevistaBD = new EntrevistaBD();
    
    
      public List<Entrevista> getEntrevistasUsuario(String codigo) {
        return entrevistaBD.getEntrevistasUsuario(codigo);
    }
    
    
}
