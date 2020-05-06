package pt.tooyummytogo.facade.dto;

import pt.tooyummytogo.domain.Product;
import pt.tooyummytogo.domain.ProductInSale;

public class ProdutoInfo {
	
	private ProductInSale productInSale;

	public ProdutoInfo(ProductInSale productInSale) {
		this.productInSale = productInSale;
	}
	
	public String getProductType() {
		return this.productInSale.getPrdt().getProductType();
	}
	
	public int getProductQuantityAvaliable() {
		return this.productInSale.getQuantity();
	}
	
}
