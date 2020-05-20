package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.tooyummytogo.exceptions.MerchantAlreadyExistsException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class MerchantCatalog {

	private static List<Merchant> merchantCat;

	/** Constructor of merchant Catalog */
	public MerchantCatalog() {
		merchantCat = new ArrayList<>();
	}

	/**
	 * 
	 * @return list of merchants
	 */
	public static List<ComercianteInfo> getMerchantCatalog() {
		List<ComercianteInfo> aux = new ArrayList<ComercianteInfo>();
		for (Merchant merch : merchantCat) {
			aux.add(new ComercianteInfo(merch.getUsername()));
		}
		return aux;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param posi     Local Coordinates of the merchant's business
	 * @throws MerchantAlreadyExistsException
	 */
	public void adicionaMerchant(String username, String password, PosicaoCoordenadas posi)
			throws MerchantAlreadyExistsException {
		if (merchantCat.contains(getMerchant(username))) {
			throw new MerchantAlreadyExistsException();
		}
		merchantCat.add(new Merchant(username, password, posi));
	}

	/**
	 * 
	 * @param username
	 * @return merchant object if found
	 */
	public static Merchant getMerchant(String username) {
		for (Merchant merch : merchantCat) {
			if (merch.getUsername().equals(username))
				return merch;
		}
		return null; // TODO return exception Merchant does not exist, era melhor
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return login token if true
	 */
	public Optional<ComercianteInfo> tryLogin(String username, String password) {
		for (Merchant merchant : merchantCat) {
			if (merchant.getUsername().equals(username) && merchant.confirmPassword(password)) {
				return Optional.of(new ComercianteInfo(username));
			}
		}
		return Optional.empty();
	}

}
