package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.exceptions.ProductAlreadyExistsException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;


public class AdicionarTipoDeProdutoHandler {

	private ComercianteInfo currentMerchant;

	public AdicionarTipoDeProdutoHandler(ComercianteInfo currentMerch) {
		this.currentMerchant = currentMerch;
	}

	public void registaTipoDeProduto(String product, double price) {
		try {
			this.currentMerchant.addProductType(product, price);
		} catch (ProductAlreadyExistsException e) {
			System.err.println("Error: Product already exists.");
			//e.printStackTrace(); //uncomment to see error
		}
	}
}
