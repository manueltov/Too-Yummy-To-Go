package pt.tooyummytogo.facade.utils.observers;

import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class Observable {
    public void notificar(String codigoReserva, ComercianteInfo merchInfo){
        merchInfo.addDelivery(codigoReserva);
    }
}