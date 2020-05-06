package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.User;


public class AdicionarTipoDeProdutoHandler {

	private Merchant currentMerchant;

	public AdicionarTipoDeProdutoHandler(Merchant currentMerch) {
		this.currentMerchant = currentMerch;
	}

	public void registaTipoDeProduto(String product, double quantity) {
		this.currentMerchant.addProductType(product, quantity);
	}
}
