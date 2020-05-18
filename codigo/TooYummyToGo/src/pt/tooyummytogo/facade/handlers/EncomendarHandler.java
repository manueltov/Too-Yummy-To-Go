package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

import pt.tooyummytogo.domain.Delivery;
import pt.tooyummytogo.domain.Order;
import pt.tooyummytogo.domain.ProductInSale;
import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.exceptions.NoMerchantInArea;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.utils.Search;

public class EncomendarHandler {

	private User currentUser;
	private ComercianteInfo merchInfo;
	private Order ord;
	private Delivery delivery;	
	
	private Search search;
	
	public EncomendarHandler(User currentUser) {
		this.currentUser = currentUser;
		this.search = new Search();
		ord = this.currentUser.createOrder();
	}

	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinates) {
		this.search.indicaLocalizacaoActual(coordinates);
		try {
			return this.search.searchIt();
		} catch (NoMerchantInArea e) {
			System.err.println("Error: No merchant available.");
			//e.printStackTrace();
		}
		return null;
	}

	public List<ComercianteInfo> redefineRaio(int i) {
		this.search.redefineRaio(i);
		try {
			return this.search.searchIt();
		} catch (NoMerchantInArea e) {
			System.err.println("Error: No merchant available.");
			//e.printStackTrace();
		}
		return null;
	}

	public List<ComercianteInfo> redefinePeriodo(LocalDateTime start, LocalDateTime end) {
		this.search.redefinePeriodo(start, end);
		try {
			return this.search.searchIt();
		} catch (NoMerchantInArea e) {
			System.err.println("Error: No merchant available.");
			//e.printStackTrace();
		}
		return null;
	}

	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) {
		this.merchInfo = comercianteInfo;
		return comercianteInfo.escolheComerciante();
	}

	public void indicaProduto(ProdutoInfo prd, int quantity) {
		ProductInSale prdInSale = this.merchInfo.getProductsForSale().getProductInSale(prd.getProductType());
		if(prdInSale.getQuantity() >= quantity) {
			ProductInSale prdToOrder = prdInSale.clone();
			prdToOrder.setQuantity(quantity);
			this.ord.addProductToOrder(prdToOrder);
			prdInSale.setQuantity(prdInSale.getQuantity() - quantity);
		} else {
			System.err.println("Quantity not enough.");
		}
	}

	public String indicaPagamento(String number, String monthYear, String ccv) {
		
		//Credit Card
		String[] monthAndYear = monthYear.split("/");
		Card c = new Card(number, ccv, monthAndYear[0], monthAndYear[1]);
		MonsterCardAPI m = new MonsterCardAPI();
		
		//Delivery
		delivery = new Delivery(this.currentUser, this.merchInfo, this.ord);
		
		//Payment
		if(!delivery.isPayed()) {
			double total = delivery.totalPrice();
			m.block(c, total);
			m.charge(c, total);
			delivery.setPayed(true);
		}
		
		String codigoReserva = delivery.generateCode();
		
		return codigoReserva;
	}

}
