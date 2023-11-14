/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Datos.Entrevista;
import Datos.HoraDisponible;
import Datos.Solicitudes;
import DatosBD.HoraDisponibleBD;
import exceptions.InvalidDataException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.Range;

/**
 *
 * @author MSI
 */
public class HorasDisponiblesService {
    HoraDisponibleBD horasocupadas = new HoraDisponibleBD();
    
    
      public List<HoraDisponible> getHorasDisponibles(String fecha, String ubicacion, String codigoOferta) throws InvalidDataException {
          validar(fecha, ubicacion, codigoOferta);
          List<HoraDisponible> horasOcupadas=horasocupadas.getHorasOcupadas(fecha,ubicacion,codigoOferta);
        return obtenerHorasDisponibles(horasOcupadas);
    }
    
    
          
    public void validar(String fecha, String ubicacion, String codigoOferta) throws InvalidDataException{
          if (fecha.isEmpty()  | ubicacion.isEmpty() | ubicacion.isEmpty() | fecha==null  | ubicacion==null | ubicacion==null ) {
            throw new InvalidDataException("Faltan Datos");
        }
          
 
    }
    
    
    
    
    public static List<HoraDisponible> obtenerHorasDisponibles(List<HoraDisponible> horasOcupadas) {
        List<HoraDisponible> horasDisponibles = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            String hora = String.format("%02d:00:00", i);
            horasDisponibles.add(new HoraDisponible(hora));
        }

        Iterator<HoraDisponible> iterator = horasDisponibles.iterator();
        while (iterator.hasNext()) {
            HoraDisponible horaDisponible = iterator.next();
            for (HoraDisponible ocupada : horasOcupadas) {
                if (horaDisponible.getHora().equals(ocupada.getHora())) {
                    iterator.remove();
                    break; 
                }
            }
        }

        return horasDisponibles;
    }
            
      
      
}
