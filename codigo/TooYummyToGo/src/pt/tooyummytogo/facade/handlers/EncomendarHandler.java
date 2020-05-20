package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import pt.tooyummytogo.domain.Delivery;
import pt.tooyummytogo.domain.Order;
import pt.tooyummytogo.domain.ProductInSale;
import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.utils.observers.Observable;
import pt.tooyummytogo.facade.utils.paymentAdapter.PaymentAdapter;
import pt.tooyummytogo.facade.utils.paymentAdapter.PluginsPaymentFactory;
import pt.tooyummytogo.facade.utils.strategies.*;


public class EncomendarHandler {

	private User currentUser;
	private ComercianteInfo merchInfo;
	private Order ord;
	private Delivery delivery;
	private PosicaoCoordenadas coordinates;
	
	public EncomendarHandler(User currentUser) {
		this.currentUser = currentUser;
		ord = this.currentUser.createOrder();
	}

	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinates) {
		SearchPorDefault s = new SearchPorDefault();
		this.coordinates = coordinates;
		return s.indicaLocalizacaoActual(coordinates);
	}

	public List<ComercianteInfo> redefineRaio(int i) {
		SearchPorRaio s = new SearchPorRaio();
		Float raio = (float) i;
		return s.redefineRaio(raio,this.coordinates);
	}

	public List<ComercianteInfo> redefinePeriodo(LocalDateTime start, LocalDateTime end) {
		SearchPorPeriodo s = new SearchPorPeriodo();
		return s.redefinePeriodo(start,end,this.coordinates);
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

	public String indicaPagamento(String cardNumber, String monthYear, String ccv) {
		
		//Delivery
		delivery = new Delivery(this.currentUser, this.merchInfo, this.ord);
		

		if(!delivery.isPayed()) {
			
			double total = delivery.totalPrice();
			System.out.println(total);
			
			List<PaymentAdapter> paymentMethodsList = PluginsPaymentFactory.getPaymentPluginsList();
			
			//Use a random payment method
			Random rd = new Random();
			System.out.println();
			int elemIdx = rd.nextInt(paymentMethodsList.size());
			
			//Uses plugin factory
			PaymentAdapter paymentMethod = paymentMethodsList.get(elemIdx);
			//System.out.println("Vai pagar com: " + paymentMethod.getClass());
			
			//Adapter
			paymentMethod.setCard(cardNumber, ccv, monthYear);
			if(paymentMethod.validate()) {
				paymentMethod.block(total);
				paymentMethod.charge(total);
			}
			
			delivery.setPayed(true);
		}
		
		String codigoReserva = delivery.generateCode();
		
		//evento de notificaçao
		Observable obs = new Observable();
		obs.notificar(codigoReserva,this.merchInfo);
		//

		return codigoReserva;
	}

}
