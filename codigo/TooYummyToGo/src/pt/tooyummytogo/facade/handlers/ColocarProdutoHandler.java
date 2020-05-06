package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.User;

public class ColocarProdutoHandler {

	private Merchant currentMerchant;

	public ColocarProdutoHandler(Merchant currentMerch) {
		this.currentMerchant = currentMerch;
	}

	public List<String> inicioDeProdutosHoje() {
		return this.currentMerchant.getProductsStringList();
	}

	public void indicaProduto(String tp, int quantity) {
		this.currentMerchant.indicaProduto(tp, quantity);
	}

	public void confirma(LocalDateTime start, LocalDateTime end) {
		this.currentMerchant.confirmaHoras(start, end);
	}

}
