package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.exceptions.MerchantAlreadyExistsException;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class RegistarComercianteHandler {

	/**
	 * MerchantCatalog - Merchants Catalog
	 */
	private MerchantCatalog merchCat;

	/**
	 * Constructor of RegistarComercianteHandler
	 * 
	 * @param merchCat
	 */
	public RegistarComercianteHandler(MerchantCatalog merchCat) {
		this.merchCat = merchCat;
	}

	/**
	 * Regista um Comerciante.
	 * 
	 * @param Username
	 * @param Password
	 * @ensures existe um comerciante com esse username
	 */
	public void registarComerciante(String username, String password, PosicaoCoordenadas p) {
		try {
			this.merchCat.adicionaMerchant(username, password, p);
		} catch (MerchantAlreadyExistsException e) {
			System.err.println("Error: Merchant already exists.");
			// e.printStackTrace(); //uncomment to see error
		}
	}

}
