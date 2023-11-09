package Service;

import Datos.Comision;
import Datos.Oferta;
import Datos.Util;
import DatosBD.ComisionBD;
import DatosBD.GestionCategoriaBD;
import DatosBD.OfertaBD;
import exceptions.InvalidDataException;
import exceptions.NotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author MSI
 */
public class OfertaService {

    OfertaBD ofertasBD = new OfertaBD();

    GestionCategoriaBD categoria = new GestionCategoriaBD();

    ComisionBD comision = new ComisionBD();
    Util util = new Util();

    public List<Oferta> getOfertas() {
        return ofertasBD.getOfertas();
    }

//    public Comision crearComision(Comision comi) throws InvalidDataException {
//
//        LocalDate fechaActual = LocalDate.now();
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato para obtener solo la fecha
//        String fecha = fechaActual.format(formato);
//        System.out.println(fecha);
//        comi.setFecha(fecha);
//
//        validar(comi);
//
//        return comision.CrearComision(comi);
//    }

    public Oferta getOferta(String codigo) throws NotFoundException {
    
        Oferta oferta = ofertasBD.getOferta(codigo);
        
      if(oferta!=null){
       
            return oferta;
        }else{
        
            throw new NotFoundException("No se encontró la categoria");
        }
    
    
    }

    public void validar(Comision comision) throws InvalidDataException {
        if (comision.getComision() == 0 || comision.getFecha().isEmpty()) {
            throw new InvalidDataException("Faltan Datos");
        }

        if (comision.getComision() < 0) {
            throw new InvalidDataException("No se puede ingresar un numero negativo");
        }

    }

}
