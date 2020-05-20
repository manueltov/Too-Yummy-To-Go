package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

import pt.portugueseexpress.InvalidCardException;
import pt.portugueseexpress.PortugueseExpress;
import pt.tooyummytogo.domain.Delivery;
import pt.tooyummytogo.domain.Order;
import pt.tooyummytogo.domain.ProductInSale;
import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.exceptions.NoMerchantInAreaException;
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
		} catch (NoMerchantInAreaException e) {
			System.err.println("Error: No merchant available.");
			//e.printStackTrace();
		}
		return null;
	}

	public List<ComercianteInfo> redefineRaio(int i) {
		this.search.redefineRaio(i);
		try {
			return this.search.searchIt();
		} catch (NoMerchantInAreaException e) {
			System.err.println("Error: No merchant available.");
			//e.printStackTrace();
		}
		return null;
	}

	public List<ComercianteInfo> redefinePeriodo(LocalDateTime start, LocalDateTime end) {
		this.search.redefinePeriodo(start, end);
		try {
			return this.search.searchIt();
		} catch (NoMerchantInAreaException e) {
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
		
		//Delivery
		delivery = new Delivery(this.currentUser, this.merchInfo, this.ord);
		
		
		Random rd = new Random();
		if(rd.nextBoolean()) {
			
			///////////////////////////////////////////////////////////////////////////////////////
			System.out.println("Vai pagar com PortugueseExpress");
			/*
			* API PortugueseExpress
			*/
			
			PortugueseExpress api = new PortugueseExpress();
			
			api.setNumber(number);
			
			int ccv_number = Integer.parseInt(ccv);
			api.setCcv(ccv_number);
			
			String[] monthAndYear = monthYear.split("/");
			int month = Integer.parseInt(monthAndYear[0]);
			int year = Integer.parseInt("20"+monthAndYear[1]);
			api.setMonth(month);
			api.setYear(year);
			
			if(api.validate()) {
				try {
					
					//Payment
					if(!delivery.isPayed()) {
						double total = delivery.totalPrice();
						api.block(total);
						api.charge(total);
						delivery.setPayed(true);
					}
					
				} catch (InvalidCardException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			///////////////////////////////////////////////////////////////////////////////////////
			
		} else {
			
			///////////////////////////////////////////////////////////////////////////////////////
			System.out.println("Vai pagar com MonsterCard");
			/*
			* API MonsterCard
			*/
			
			//Credit Card
			String[] monthAndYear = monthYear.split("/");
			Card c = new Card(number, ccv, monthAndYear[0], monthAndYear[1]);
			MonsterCardAPI m = new MonsterCardAPI();
			
			
			//Payment
			if(!delivery.isPayed()) {
				double total = delivery.totalPrice();
				m.block(c, total);
				m.charge(c, total);
				delivery.setPayed(true);
			}
			///////////////////////////////////////////////////////////////////////////////////////
			
		}
	
		
		String codigoReserva = delivery.generateCode();
		
		return codigoReserva;
	}

}
