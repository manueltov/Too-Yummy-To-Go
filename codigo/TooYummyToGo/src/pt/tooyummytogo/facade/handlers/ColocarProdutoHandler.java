package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;

import pt.tooyummytogo.exceptions.ProductNotFoundException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class ColocarProdutoHandler {

	// Authenticated merchant
	private ComercianteInfo currentMerchant;

	/**
	 * Constructor of ColocarProdutoHandler
	 * 
	 * @param currentMerch
	 */
	public ColocarProdutoHandler(ComercianteInfo currentMerch) {
		this.currentMerchant = currentMerch;
	}

	/**
	 * Get list of products that the merchant has.
	 * 
	 * @return that list of products.
	 */
	public List<String> inicioDeProdutosHoje() {
		return this.currentMerchant.getProductsStringList();
	}

	/**
	 * Put product for sale
	 * 
	 * @param tp       - product to put for sale
	 * @param quantity - quantity to put for sale
	 * @throws ProductNotFoundException - if product to add was not found
	 */
	public void indicaProduto(String tp, int quantity) throws ProductNotFoundException {
		this.currentMerchant.indicaProduto(tp, quantity);
	}

	/**
	 * Choose hours of start and end for the sale of that day
	 * 
	 * @param start
	 * @param end
	 */
	public void confirma(LocalDateTime start, LocalDateTime end) {
		this.currentMerchant.confirmaHoras(start, end);
	}

}
