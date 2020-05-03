package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.User;

public class AdicionarTipoDeProdutoHandler {

	private Merchant currentMerchant;
	
	public AdicionarTipoDeProdutoHandler(User currentUser) {
		this.currentMerchant = MerchantCatalog.getMerchant(currentUser);
	}

	public int registaTipoDeProduto(String tp) {
		return currentMerchant.addPruductType(tp);
	}

}