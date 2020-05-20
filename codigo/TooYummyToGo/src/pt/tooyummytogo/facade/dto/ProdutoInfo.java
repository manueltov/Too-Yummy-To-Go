package pt.tooyummytogo.facade.dto;

import pt.tooyummytogo.domain.ProductInSale;

public class ProdutoInfo {

	private ProductInSale productInSale;

	/**
	 * Constructor of ProdutoInfo
	 * 
	 * @param productInSale
	 */
	public ProdutoInfo(ProductInSale productInSale) {
		this.productInSale = productInSale;
	}

	/**
	 * Get product type
	 * 
	 * @return product type
	 */
	public String getProductType() {
		return this.productInSale.getPrdt().getProductType();
	}

	/**
	 * Get product quantity avaliable
	 * 
	 * @return product quantity avaliable
	 */
	public int getProductQuantityAvaliable() {
		return this.productInSale.getQuantity();
	}

}
