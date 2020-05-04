package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.domain.UserType;
import pt.tooyummytogo.exceptions.OnlyMerchantsCanAddProductsException;


public class AdicionarTipoDeProdutoHandler {

	private Merchant currentMerchant;

	public AdicionarTipoDeProdutoHandler(User currentUser) throws OnlyMerchantsCanAddProductsException {
		if(currentUser.getUserType() != UserType.MERCHANT)
			throw new OnlyMerchantsCanAddProductsException();
		this.currentMerchant = MerchantCatalog.getMerchant(currentUser);
	}

	public void registaTipoDeProduto(String tp, double p) {
		this.currentMerchant.addProductType(tp, p);
	}
}
