package pt.tooyummytogo.domain;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class Merchant {

	private User user;
	private PosicaoCoordenadas p;
	
	public Merchant(User user, PosicaoCoordenadas p) {
		this.user = user;
		this.p = p;
	}
	

}
