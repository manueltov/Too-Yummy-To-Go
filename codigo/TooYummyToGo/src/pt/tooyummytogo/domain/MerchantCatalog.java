package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class MerchantCatalog {
	
	private static List<Merchant> merchantCat = new ArrayList<>();

	public void adicionaMerchant(String username, String password, PosicaoCoordenadas p) {
		merchantCat.add(new Merchant(new User(username, password), p));
		///////// so pra debbug /////////////////////////////
		System.out.println("so pra dizer  q o " + username + " foi adiconado ao cataMerchant.");
	}
	
	public static Merchant getMerchant(User user) {
		for (Merchant merch : merchantCat) {
			if(merch.getUser().equals(user))
				return merch;
		}
		return null; //TODO return exception Merchant does not exist, era melhor
	}

	public Optional<User> tryLogin(String username, String password) {
		for (Merchant merchant : merchantCat) {
			if(merchant.getUser().getUsername().equals(username) && merchant.getUser().confirmPassword(password)) {
				return Optional.of(merchant.getUser());
			}
		}
		return Optional.empty();
	}
	
}
