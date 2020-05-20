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

	// Authenticated user
	private User currentUser;

	// The merchant that will sell things to the authenticated user
	private ComercianteInfo merchInfo;

	// Order of purchase
	private Order ord;

	// Delivery to purchase
	private Delivery delivery;

	// Coordinates of the user
	private PosicaoCoordenadas coordinates;

	/**
	 * Constructor of EncomendarHandler
	 * 
	 * @param currentUser
	 */
	public EncomendarHandler(User currentUser) {
		this.currentUser = currentUser;
		ord = this.currentUser.createOrder();
	}

	/**
	 * Search for merchants based in location
	 * 
	 * @param coordinates
	 * @return list of merchants of the search
	 */
	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinates) {
		SearchPorDefaultStrategy s = new SearchPorDefaultStrategy();
		this.coordinates = coordinates;
		return s.indicaLocalizacaoActual(coordinates);
	}

	/**
	 * Search for merchants giving a range
	 * 
	 * @param i - range
	 * @return list of merchants of the search
	 */
	public List<ComercianteInfo> redefineRaio(int i) {
		SearchPorRaioStrategy s = new SearchPorRaioStrategy();
		Float raio = (float) i;
		return s.redefineRaio(raio, this.coordinates);
	}

	/**
	 * Search for merchants giving a time period
	 * 
	 * @param start
	 * @param end
	 * @return list of merchants of the search
	 */
	public List<ComercianteInfo> redefinePeriodo(LocalDateTime start, LocalDateTime end) {
		SearchPorPeriodoStrategy s = new SearchPorPeriodoStrategy();
		return s.redefinePeriodo(start, end, this.coordinates);
	}

	/**
	 * Choose merchant to shop
	 * 
	 * @param comercianteInfo
	 * @return list of products of the selected merch
	 */
	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) {
		this.merchInfo = comercianteInfo;
		return comercianteInfo.escolheComerciante();
	}

	/**
	 * Choose product to add to order
	 * 
	 * @param prd      - product to add
	 * @param quantity - quantity of products of this type to add to order
	 */
	public void indicaProduto(ProdutoInfo prd, int quantity) {
		ProductInSale prdInSale = this.merchInfo.getProductsForSale().getProductInSale(prd.getProductType());
		if (prdInSale.getQuantity() >= quantity) {
			ProductInSale prdToOrder = prdInSale.clone();
			prdToOrder.setQuantity(quantity);
			this.ord.addProductToOrder(prdToOrder);
			prdInSale.setQuantity(prdInSale.getQuantity() - quantity);
		} else {
			System.err.println("Quantity not enough.");
		}
	}

	/**
	 * Make payment
	 * 
	 * @param cardNumber - Number of the credit card
	 * @param monthYear  - expire date of credit card "MM/YY"
	 * @param ccv        - CCV code of the card
	 * @return code of the delivery
	 */
	public String indicaPagamento(String cardNumber, String monthYear, String ccv) {

		// Delivery
		delivery = new Delivery(this.currentUser, this.merchInfo, this.ord);

		if (!delivery.isPayed()) {

			double total = delivery.totalPrice();

			List<PaymentAdapter> paymentMethodsList = PluginsPaymentFactory.getPaymentPluginsList();

			// Use a random payment method
			Random rd = new Random();
			int elemIdx = rd.nextInt(paymentMethodsList.size());

			// Uses plugin factory
			PaymentAdapter paymentMethod = paymentMethodsList.get(elemIdx);
			// System.out.println("Vai pagar com: " + paymentMethod.getClass());

			// Adapter
			paymentMethod.setCard(cardNumber, ccv, monthYear);
			if (paymentMethod.validate()) {
				paymentMethod.block(total);
				paymentMethod.charge(total);
			}

			delivery.setPayed(true);
		}

		String codigoReserva = delivery.generateCode();

		// evento de notificaçao
		Observable obs = new Observable();
		obs.notificar(codigoReserva, this.merchInfo);
		//

		return codigoReserva;
	}

}
