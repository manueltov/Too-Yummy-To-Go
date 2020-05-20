package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.exceptions.ProductAlreadyExistsException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class AdicionarTipoDeProdutoHandler {

	private ComercianteInfo currentMerchant;

	/**
	 * Constructor of AdicionarTipoDeProdutoHandler
	 * 
	 * @param currentMerch - authenticated merchant
	 */
	public AdicionarTipoDeProdutoHandler(ComercianteInfo currentMerch) {
		this.currentMerchant = currentMerch;
	}

	/**
	 * Product to add to this merchant iventory
	 * 
	 * @param product - product to add
	 * @param price   - price of the product
	 */
	public void registaTipoDeProduto(String product, double price) {
		try {
			this.currentMerchant.addProductType(product, price);
		} catch (ProductAlreadyExistsException e) {
			System.err.println("Error: Product already exists.");
			// e.printStackTrace(); //uncomment to see error
		}
	}
}
