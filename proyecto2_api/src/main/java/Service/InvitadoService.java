package Service;

import Datos.Entrevista;
import DatosBD.InvitadoBD;
import exceptions.InvalidDataException;

/**
 *
 * @author MSI
 */
public class InvitadoService {

    private InvitadoBD invitadoBD = new InvitadoBD();

    public boolean AgregarInvitado() {
        return invitadoBD.AgregarInvitado();
    }

}
