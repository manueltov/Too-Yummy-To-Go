package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private List<ProductInSale> lstOrderProducts;

	public Order() {
		this.lstOrderProducts = new ArrayList<ProductInSale>();
	}
	
	public void addProductToOrder(ProductInSale productToAdd) {
		this.lstOrderProducts.add(productToAdd);
	}

	public double getTotal() {
		double total = 0;
		for (ProductInSale productInSale : this.lstOrderProducts) {
			total += productInSale.getPrdt().getPrice();
		}
		return total;
	}
	
}
