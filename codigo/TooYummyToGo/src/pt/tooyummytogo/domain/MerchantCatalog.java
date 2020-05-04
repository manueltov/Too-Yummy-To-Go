package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class MerchantCatalog {
	
	private static List<Merchant> merchantList = new ArrayList<>();

	public void adicionaMerchant(String username, String password, PosicaoCoordenadas p) {
		merchantList.add(new Merchant(new User(username, password, UserType.MERCHANT), p));
	}
	
	public static Merchant getMerchant(User user) {
		for (Merchant merch : merchantList) {
			if(merch.getUser() == user)
				return merch;
		}
		return null; //TODO return exception Merchant does not exist, era melhor
	}
	
}
