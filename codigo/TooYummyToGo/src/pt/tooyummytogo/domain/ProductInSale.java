package pt.tooyummytogo.domain;

public class ProductInSale {
	
	private Product prdt;
	private int quantity;
	
	public ProductInSale(Product prdt, int quantity) {
		this.prdt = prdt;
		this.quantity = quantity;
	}

	public Product getPrdt() {
		return prdt;
	}
	
}
