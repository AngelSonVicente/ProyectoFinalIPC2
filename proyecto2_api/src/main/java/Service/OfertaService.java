package Service;

import Datos.Comision;
import Datos.Oferta;
import Datos.OfertaEliminada;
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

    public List<Oferta> getOfertasEmpresa(String codigo) {
        return ofertasBD.getOfertasEmpresa(codigo);
    }
    public List<Oferta> getOfertasEmpresaEstados(String codigo, String estado) {
        return ofertasBD.getOfertasEmpresaEstados(codigo,estado);
    }

    public Oferta crearOferta(Oferta oferta) throws InvalidDataException {
        validar(oferta);
        return ofertasBD.crearOferta(oferta);
    }

    
    public Oferta actualizarOferta(Oferta oferta) throws InvalidDataException{
        validar(oferta);
    return ofertasBD.actualizarOferta(oferta);
    }
    public OfertaEliminada eliminarOferta(OfertaEliminada oferta){
        return ofertasBD.EliminarOferta(oferta);
    }
    
    
    
    
    public Oferta getOferta(String codigo) throws NotFoundException {
    
        Oferta oferta = ofertasBD.getOferta(codigo);
        
      if(oferta!=null){
       
            return oferta;
        }else{
        
            throw new NotFoundException("No se encontró la categoria");
        }
    
    
    }

    public void validar(Oferta oferta) throws InvalidDataException {
        if (oferta.getNombre().isEmpty() || oferta.getDescripcion().isEmpty() ||oferta.getFechaLimite().isEmpty()  || oferta.getModadidad().isEmpty() ||  oferta.getUbicacion().isEmpty() || oferta.getDetalle().isEmpty()) {
            throw new InvalidDataException("Faltan Datos");
        }

        if (oferta.getSalario()< 0) {
            throw new InvalidDataException("No se puede ingresar un numero negativo");
        }

    }

}
