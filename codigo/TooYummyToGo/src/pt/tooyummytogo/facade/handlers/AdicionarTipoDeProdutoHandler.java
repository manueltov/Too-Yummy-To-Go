package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.facade.dto.ComercianteInfo;


public class AdicionarTipoDeProdutoHandler {

	private ComercianteInfo currentMerchant;

	public AdicionarTipoDeProdutoHandler(ComercianteInfo currentMerch) {
		this.currentMerchant = currentMerch;
	}

	public void registaTipoDeProduto(String product, double quantity) {
		this.currentMerchant.addProductType(product, quantity);
	}
}
