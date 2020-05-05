package pt.tooyummytogo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductsForSale {
	
	private List<ProductInSale> lstProductsForSale;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;
	
	public ProductsForSale() {
		lstProductsForSale = new ArrayList<ProductInSale>();
	}
	
	public void addProductForSale(Product prdt, int quantity) {
		this.lstProductsForSale.add(new ProductInSale(prdt, quantity));
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public void setHoraFim(LocalDateTime horaFim) {
		this.horaFim = horaFim;
	}
	
}
