package pt.tooyummytogo.facade.dto;

import java.time.LocalDateTime;
import java.util.List;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.Product;
import pt.tooyummytogo.domain.ProductsForSale;
import pt.tooyummytogo.exceptions.ProductAlreadyExistsException;
import pt.tooyummytogo.exceptions.ProductNotFoundException;

public class ComercianteInfo {

	private Merchant merch;

	/**
	 * Constructor of ComercianteInfo
	 * 
	 * @param merchUsername - current merchant
	 */
	public ComercianteInfo(String merchUsername) {
		this.merch = MerchantCatalog.getMerchant(merchUsername);
	}

	/**
	 * Get Username of the merchant
	 * 
	 * @return merchants username
	 */
	public String getUsername() {
		return this.merch.getUsername();
	}

	/**
	 * Add product to products of the current merchant
	 * 
	 * @param tp    - product type
	 * @param price - price for the product
	 * @return code of the product
	 * @throws ProductAlreadyExistsException
	 */
	public int addProductType(String tp, double price) throws ProductAlreadyExistsException {
		return this.merch.addProductType(tp, price);
	}

	/**
	 * Get all the products of the current merchant
	 * 
	 * @return list of the products types
	 */
	public List<String> getProductsStringList() {
		return this.merch.getProductsStringList();
	}

	/**
	 * Get product
	 * 
	 * @param tp - product type of the product
	 * @return product with the productType given
	 * @throws ProductNotFoundException
	 */
	public Product getProduct(String tp) throws ProductNotFoundException {
		return this.merch.getProduct(tp);
	}

	/**
	 * Put product for sale with some quantity
	 * 
	 * @param tp       - type product of the product to put for sale
	 * @param quantity - quantity of the product
	 * @throws ProductNotFoundException
	 */
	public void indicaProduto(String tp, int quantity) throws ProductNotFoundException {
		this.merch.indicaProduto(tp, quantity);
	}

	/**
	 * Get products for sale of the current merchant
	 * 
	 * @return ProductsForSale of this merchant
	 */
	public ProductsForSale getProductsForSale() {
		return this.merch.getProductsForSale();
	}

	/**
	 * Choose hours of start and end for the sale of that day
	 * 
	 * @param start
	 * @param end
	 */
	public void confirmaHoras(LocalDateTime start, LocalDateTime end) {
		this.merch.confirmaHoras(start, end);
	}

	/**
	 * Get coordinates of the current merchant
	 * 
	 * @return coordinates
	 */
	public PosicaoCoordenadas getCoordinates() {
		return this.merch.getCoordinates();
	}

	/**
	 * Get list of ProductInfo of current merchant
	 * @return list of ProductInfo of current merchant
	 */
	public List<ProdutoInfo> escolheComerciante() {
		return this.merch.getProductsInfoList();
	}

	/**
	 * Returns if this merchant has or not products for sale
	 * @return true if it has products for sale, otherwise false
	 */
	public boolean hasProductsForSale() {
		return this.merch.hasProductsForSale();
	}

	@Override
	public String toString() {
		return "[ Comerciante: " + getUsername() + " ]";
	}

	/**
	 * Add delivery code to this merchant
	 * @param codigoReserva - delivery code
	 */
	public void addDelivery(String codigoReserva) {
		this.merch.addDelivery(codigoReserva);
	}

}