package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class MerchantCatalog {
	
	private static List<Merchant> merchantCat = new ArrayList<>();

	public void adicionaMerchant(String username, String password, PosicaoCoordenadas posi) {
		merchantCat.add(new Merchant(username, password, posi));
		///////// so pra debbug /////////////////////////////
		System.out.println("so pra dizer  q o " + username + " foi adiconado ao cataMerchant.");
	}
	
	public static Merchant getMerchant(String username) {
		for (Merchant merch : merchantCat) {
			if(merch.getUsername().equals(username))
				return merch;
		}
		return null; //TODO return exception Merchant does not exist, era melhor
	}

	public Optional<Merchant> tryLogin(String username, String password) {
		for (Merchant merchant : merchantCat) {
			if(merchant.getUsername().equals(username) && merchant.confirmPassword(password)) {
				return Optional.of(merchant);
			}
		}
		return Optional.empty();
	}
	
}
