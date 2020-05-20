package pt.tooyummytogo.domain;

/**
 * Product object is a product that the merchant has a merchant can have a
 * product object created or many of them but they can be only registered in the
 * merchant but not used to sale
 * 
 */
public class Product {
	private String productType;
	private double price;

	/**
	 * Constructor of Product
	 * 
	 * @param tp
	 * @param p
	 */
	public Product(String tp, double p) {
		this.productType = tp;
		this.price = p;
	}

	/**
	 * Get product type
	 * 
	 * @return product type
	 */
	public String getProductType() {
		return productType;
	}

	@Override
	public String toString() {
		return getProductType();
	}

	@Override
	public Product clone() {
		return new Product(this.productType, this.price);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
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
		Product other = (Product) obj;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		return true;
	}

	/**
	 * Get the price of the product
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return this.price;
	}

}
