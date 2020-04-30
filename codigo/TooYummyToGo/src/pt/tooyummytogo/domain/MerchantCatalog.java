package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class MerchantCatalog {
	
	private List<Merchant> merchantList = new ArrayList<>();
	
	public void adicionaMerchant (String username, String password, PosicaoCoordenadas p) {
		merchantList.add(new Merchant(username, password, p));
	}
	
}
