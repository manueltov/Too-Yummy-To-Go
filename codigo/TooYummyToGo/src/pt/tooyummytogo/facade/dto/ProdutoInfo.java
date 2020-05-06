package pt.tooyummytogo.facade.dto;

import pt.tooyummytogo.domain.Product;

public class ProdutoInfo {
	
	private Product product;

	public ProdutoInfo(Product product) {
		this.product = product;
	}
	
	public String getProductType() {
		return this.product.getProductType();
	}
	
}
