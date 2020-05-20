package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class ColocarProdutoHandler {

	private ComercianteInfo currentMerchant;

	public ColocarProdutoHandler(ComercianteInfo currentMerch) {
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
