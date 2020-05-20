package pt.tooyummytogo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Every merchant has a ProductsForSale object Its like an inventory of the
 * products that the merchant has for sale eg: A merchant can create a
 * "inventory" for this day and add some ProductsInSale to his list and set the
 * start and end of the time it will be open to give the delivery in this day
 */
public class ProductsForSale {

	// List of ProductsInSale
	private List<ProductInSale> lstProductsForSale;

	// Start and end of the hours that the merchant is available to the deliveries
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;

	/**
	 * Constructor of ProductsForSale
	 */
	public ProductsForSale() {
		lstProductsForSale = new ArrayList<ProductInSale>();
	}

	/**
	 * To add a product for sale
	 * 
	 * @param prdt     - the product to add
	 * @param quantity - the quantity
	 */
	public void addProductForSale(Product prdt, int quantity) {
		this.lstProductsForSale.add(new ProductInSale(prdt, quantity));
	}

	/**
	 * Set hour of start of the sale
	 * 
	 * @param horaInicio
	 */
	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * Set hour of end of the sale
	 * 
	 * @param horaFim
	 */
	public void setHoraFim(LocalDateTime horaFim) {
		this.horaFim = horaFim;
	}

	/**
	 * Get hour of start of the sale
	 * 
	 * @return horaInicio
	 */
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	/**
	 * Get hour of end of the sale
	 * 
	 * @return horaFim
	 */
	public LocalDateTime getHoraFim() {
		return horaFim;
	}

	/**
	 * Get the list of the products for sale
	 * 
	 * @return List of ProductsInSale
	 */
	public List<ProductInSale> getLstProductsForSale() {
		return lstProductsForSale;
	}

	/**
	 * Get a ProductInSale of the list
	 * 
	 * @param prdType - product type of the product to get
	 * @return the productInSale object
	 */
	public ProductInSale getProductInSale(String prdType) {
		ProductInSale aux = new ProductInSale(new Product(null, 0), 0);
		for (ProductInSale productInSale : this.lstProductsForSale) {
			if (productInSale.getPrdt().getProductType().equals(prdType))
				aux = productInSale;
		}
		if (aux.getPrdt().getProductType() == null) {
			// TODO return exception
		}
		return aux;
	}

	/**
	 * Has any Product For sale
	 * 
	 * @return true if the List of ProductsInSale has something, false otherwise
	 */
	public boolean hasProductsForSale() {
		return !(this.lstProductsForSale.isEmpty());
	}

}
