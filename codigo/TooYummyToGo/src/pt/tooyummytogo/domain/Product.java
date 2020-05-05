package pt.tooyummytogo.domain;

public class Product {
	private String productType;
	private double price;

	public Product(String tp, double p) {
		this.productType = tp;
		this.price = p;
	}

	public String getProductType() {
		return productType;
	}

	public String toString() {
		return getProductType();
	}
	
	
}
