package pt.tooyummytogo.domain;

/**
 * A ProductInSale object is a Product that the merchant has for sale When a
 * product is for sale, it has the product object and also the quantity
 * 
 */
public class ProductInSale {

	private Product prdt;
	private int quantity;

	/**
	 * Constructor of ProductInSale
	 * @param prdt - Product to create the ProductInSale
	 * @param quantity - quantity of unites of this product
	 */
	public ProductInSale(Product prdt, int quantity) {
		this.prdt = prdt;
		this.quantity = quantity;
	}

	/**
	 * Get the product object
	 * @return product
	 */
	public Product getPrdt() {
		return prdt;
	}

	/**
	 * Get the quantity of this product that is for sale
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set the quantity
	 * @param quantity of this product that is for sale
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public ProductInSale clone() {
		return new ProductInSale(this.prdt.clone(), this.quantity);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prdt == null) ? 0 : prdt.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInSale other = (ProductInSale) obj;
		if (prdt == null) {
			if (other.prdt != null)
				return false;
		} else if (!prdt.equals(other.prdt))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
