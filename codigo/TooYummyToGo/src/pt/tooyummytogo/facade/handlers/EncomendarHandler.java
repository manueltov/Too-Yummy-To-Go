package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.Order;
import pt.tooyummytogo.domain.ProductInSale;
import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;

public class EncomendarHandler {

	private User currentUser;
	private ComercianteInfo merchInfo;
	private Order ord;
	
	private SearchHandler search;
	
	public EncomendarHandler(User currentUser) {
		this.currentUser = currentUser;
		this.search = new SearchHandler();
		ord = this.currentUser.createOrder();
	}

	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinates) {
		this.search.indicaLocalizacaoActual(coordinates);
		return this.search.searchIt();
	}

	public List<ComercianteInfo> redefineRaio(int i) {
		this.search.redefineRaio(i);
		return this.search.searchIt();
	}

	public List<ComercianteInfo> redefinePeriodo(LocalDateTime start, LocalDateTime end) {
		this.search.redefinePeriodo(start, end);
		return this.search.searchIt();
	}

	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) {
		this.merchInfo = comercianteInfo;
		return comercianteInfo.escolheComerciante();
	}

	public void indicaProduto(ProdutoInfo prd, int quantity) {
		ProductInSale prdInSale = this.merchInfo.getProductsForSale().getProductInSale(prd.getProductType());
		//ver se ha quantidade
		if(prdInSale.getQuantity() >= quantity) {
			//caso sim, cria copia de prdoto pra venda (q sera para adicionar à order)
			// depois altera a quantidade desse para a pretendida
			// decrementa  a qunatidade do produto para venda 
			ProductInSale prdToOrder = prdInSale.clone();
			prdToOrder.setQuantity(quantity);
			this.ord.addProductToOrder(prdToOrder);
			prdInSale.setQuantity(prdInSale.getQuantity() - quantity);
		} else {
			//mandar excepcao de que nao ha quantidade para encomnedar
		}
	}

	public String indicaPagamento(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}

}
