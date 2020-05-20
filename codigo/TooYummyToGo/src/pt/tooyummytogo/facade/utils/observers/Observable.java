package pt.tooyummytogo.facade.utils.observers;

import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class Observable {
	/**
	 * Notifies merchant that a delivery was issued by a customer
	 * @param codigoReserva Reservation code
	 * @param merchInfo merchant to notify
	 */
	public void notificar(String codigoReserva, ComercianteInfo merchInfo) {
		merchInfo.addDelivery(codigoReserva);
	}
}