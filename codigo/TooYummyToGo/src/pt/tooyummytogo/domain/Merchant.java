package pt.tooyummytogo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class Merchant {

	private String username;
	private String password;
	private PosicaoCoordenadas posi;
	private List<Product> lstProducts;
	private ProductsForSale productsForSale;
	
	public Merchant(String username, String password, PosicaoCoordenadas posi) {
		this.username = username;
		this.password = password;
		this.posi = posi;
		this.lstProducts = new ArrayList<Product>();
		this.productsForSale = new ProductsForSale();
	}

	public String getUsername() {
		return username;
	}
	
	public boolean confirmPassword(String pw) {
		return this.password.equals(pw);
	}

	public int addProductType(String tp, double price) {
		this.lstProducts.add(new Product(tp, price));
		///////// so pra debbug /////////////////////////////
		System.out.println("o " + this.username + " adicionou " + tp + " em " + (lstProducts.size()-1));
		///////// so pra debbug /////////////////////////////
		System.out.println("por enquanto a lista esta assim: " + this.lstProducts.toString());
		return lstProducts.size()-1;
	}

	public List<String> getProductsStringList() {
		List<String> aux = new ArrayList<String>();
		for (Product prd : this.lstProducts) {
			aux.add(prd.getProductType());
		}
		///////// so pra debbug /////////////////////////////
		System.out.println("pediram a lista de produtos deste comerciante e foi dado isto: " + aux.toString());
		return aux;
	}
	
	public Product getProduct(String tp) {
		for (Product prdt : lstProducts) {
			if(prdt.getProductType().equals(tp))
				return prdt;
		}
		return null; //TODO //mandar excepcao de qe nao ha esse produto
	}
	
	public void indicaProduto(String tp, int quantity) {
		Product prdt = getProduct(tp);
		if(prdt != null){
			this.productsForSale.addProductForSale(prdt, quantity);
			///////// so pra debbug /////////////////////////////
			System.out.println("pediram para adicionar um produto � lista dos produtos para venda: " + quantity + " 'vezes' " + prdt.toString() );
		}
		else {
			//TODO
			//create exception de q produto tem de estar la nista para ir para os produtos do dia
		}
			
	}
	
	public ProductsForSale getProductsForSale() {
		return productsForSale;
	}

	public void confirmaHoras(LocalDateTime start, LocalDateTime end) {
		this.productsForSale.setHoraInicio(start);
		this.productsForSale.setHoraFim(end);
		///////// so pra debbug /////////////////////////////
		System.out.println("puseram o horario para a venda disto com inicio : " + start + " e fim : " + end );
	}

	public PosicaoCoordenadas getCoordinates() {
		return this.posi;
	}
	
}
