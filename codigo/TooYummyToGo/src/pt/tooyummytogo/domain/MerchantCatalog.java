package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class MerchantCatalog {
	
	private static List<Merchant> merchantList = new ArrayList<>();

	public static void adicionaMerchant(String username, String password, PosicaoCoordenadas p) {
		merchantList.add(new Merchant(new User(username, password), p));
	}
	
}
