package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class Merchant {

	private User user;
	private PosicaoCoordenadas p;
	private List<Products> lstProducts;
	
	public Merchant(User user, PosicaoCoordenadas p) {
		this.user = user;
		this.p = p;
		this.lstProducts = new ArrayList<Products>();
	}

	public User getUser() {
		return user;
	}

	public int addProductType(String tp, double p) {
		this.lstProducts.add(new Products(tp, p));
		///////// so pra debbug /////////////////////////////
		System.out.println("o " + this.user.getUsername() + " adicionou " + tp + " em " + (lstProducts.size()-1));
		return lstProducts.size()-1;
	}
	
}
