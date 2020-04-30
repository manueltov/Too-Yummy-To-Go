package pt.tooyummytogo.domain;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class Merchant {

	private String username;
	private String password;
	private PosicaoCoordenadas p;
	
	public Merchant(String username, String password, PosicaoCoordenadas p) {
		this.username = username;
		this.password = password;
		this.p = p;
	}

}
