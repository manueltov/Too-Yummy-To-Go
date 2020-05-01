package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class RegistarComercianteHandler {
	
	private MerchantCatalog merchCat;
	
	public RegistarComercianteHandler() {
		this.merchCat = new MerchantCatalog();
	}

	/**
	 * Regista um Comerciante.
	 * @param Username
	 * @param Password
	 * @ensures existe um comerciante com esse username
	 */
	public void registarComerciante(String username, String password, PosicaoCoordenadas p) {
		this.merchCat.adicionaMerchant(username, password, p);
	}

}
