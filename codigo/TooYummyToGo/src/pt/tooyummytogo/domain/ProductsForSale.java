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

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public LocalDateTime getHoraFim() {
		return horaFim;
	}

	public List<ProductInSale> getLstProductsForSale() {
		return lstProductsForSale;
	}

	public ProductInSale getProductInSale(String prdType) {
		ProductInSale aux = new ProductInSale(new Product(null, 0), 0);
		for (ProductInSale productInSale : this.lstProductsForSale) {
			if (productInSale.getPrdt().getProductType().equals(prdType))
				aux = productInSale;
		}
		if (aux.getPrdt().getProductType() == null) {
			// return exception
		}
		return aux;
	}

	public boolean hasProductsForSale() {
		return !(this.lstProductsForSale.isEmpty());
	}

}
