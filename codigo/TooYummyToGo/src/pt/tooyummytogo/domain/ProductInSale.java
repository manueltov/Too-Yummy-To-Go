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

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

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
