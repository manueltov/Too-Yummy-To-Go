package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;

import pt.tooyummytogo.exceptions.ProductNotFoundException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class ColocarProdutoHandler {

	private ComercianteInfo currentMerchant;

	public ColocarProdutoHandler(ComercianteInfo currentMerch) {
		this.currentMerchant = currentMerch;
	}

	public List<String> inicioDeProdutosHoje() {
		return this.currentMerchant.getProductsStringList();
	}

	public void indicaProduto(String tp, int quantity) throws ProductNotFoundException {
		this.currentMerchant.indicaProduto(tp, quantity);
	}

	public void confirma(LocalDateTime start, LocalDateTime end) {
		this.currentMerchant.confirmaHoras(start, end);
	}

}
