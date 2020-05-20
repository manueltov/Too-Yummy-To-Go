package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private List<ProductInSale> lstOrderProducts;

	/**
	 * Constructor of Order
	 */
	public Order() {
		this.lstOrderProducts = new ArrayList<ProductInSale>();
	}

	/**
	 * 
	 * @param productToAdd to Order
	 */
	public void addProductToOrder(ProductInSale productToAdd) {
		this.lstOrderProducts.add(productToAdd);
	}

	/**
	 * 
	 * @return total price value in current order
	 */
	public double getTotal() {
		double total = 0;
		for (ProductInSale productInSale : this.lstOrderProducts) {
			total += productInSale.getPrdt().getPrice();
		}
		return total;
	}

}
