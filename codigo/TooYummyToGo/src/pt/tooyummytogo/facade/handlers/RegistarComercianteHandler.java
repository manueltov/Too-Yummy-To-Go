package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class RegistarComercianteHandler {
	
	/**
	 * Regista um Comerciante.
	 * @param Username
	 * @param Password
	 * @ensures existe um comerciante com esse username
	 */
	public void registarComerciante(String username, String password, PosicaoCoordenadas p) {
		MerchantCatalog.adicionaMerchant(username,password,p);
	}

}