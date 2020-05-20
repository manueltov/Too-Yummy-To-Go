package pt.tooyummytogo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.exceptions.ProductAlreadyExistsException;
import pt.tooyummytogo.exceptions.ProductNotFoundException;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;

public class Merchant {

	private String username;
	private String password;
	private PosicaoCoordenadas posi;
	private List<Product> lstProducts;
	private ProductsForSale productsForSale;
	private List<String> deliveries;

	/**
	 * Constructor of Merchant
	 * 
	 * @param username
	 * @param password
	 * @param posi     LocalCoordinates of the business
	 */
	public Merchant(String username, String password, PosicaoCoordenadas posi) {
		this.username = username;
		this.password = password;
		this.posi = posi;
		this.lstProducts = new ArrayList<Product>();
		this.productsForSale = new ProductsForSale();
		this.deliveries = new ArrayList<String>();
	}

	/**
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Evalutes if the password inserted is the correct one
	 * 
	 * @param pw
	 * @return boolean
	 */
	boolean confirmPassword(String pw) {
		return this.password.equals(pw);
	}

	/**
	 * 
	 * @param tp    Type of the product
	 * @param price price of the product
	 * @return index of the product in the list
	 * @throws ProductAlreadyExistsException
	 */
	public int addProductType(String tp, double price) throws ProductAlreadyExistsException {
		try {
			if (lstProducts.contains(getProduct(tp)))
				throw new ProductAlreadyExistsException();
		} catch (ProductNotFoundException e) {
			this.lstProducts.add(new Product(tp, price));
			return lstProducts.size() - 1;
		}
		return 0;
	}

	/**
	 * Convert product list to string
	 * 
	 * @return string list
	 */
	public List<String> getProductsStringList() {
		List<String> aux = new ArrayList<String>();
		for (Product prd : this.lstProducts) {
			aux.add(prd.getProductType());
		}
		return aux;
	}

	/**
	 * 
	 * @param tp type of product
	 * @return the product if it's registred
	 * @throws ProductNotFoundException
	 */
	public Product getProduct(String tp) throws ProductNotFoundException {
		for (Product prdt : lstProducts) {
			if (prdt.getProductType().equals(tp))
				return prdt;
		}
		throw new ProductNotFoundException();
	}

	/**
	 * 
	 * @param tp       type of product
	 * @param quantity quantity to make available
	 * @throws ProductNotFoundException
	 */
	public void indicaProduto(String tp, int quantity) throws ProductNotFoundException {
		this.productsForSale.addProductForSale(getProduct(tp), quantity);
	}

	/**
	 * 
	 * @return products that are for sale
	 */
	public ProductsForSale getProductsForSale() {
		return productsForSale;
	}

	/**
	 * Confirm hours of sale of the day
	 * 
	 * @param start start time of the sale of the day
	 * @param end   end time of the sale of the day
	 */
	public void confirmaHoras(LocalDateTime start, LocalDateTime end) {
		this.productsForSale.setHoraInicio(start);
		this.productsForSale.setHoraFim(end);
	}

	/**
	 * 
	 * @return LocalCoordinates of the business
	 */
	public PosicaoCoordenadas getCoordinates() {
		return this.posi;
	}

	/**
	 * 
	 * @return list of products for sale
	 */
	public List<ProdutoInfo> getProductsInfoList() {
		List<ProdutoInfo> aux = new ArrayList<ProdutoInfo>();
		for (ProductInSale productInSale : this.productsForSale.getLstProductsForSale()) {
			aux.add(new ProdutoInfo(productInSale));
		}
		return aux;
	}

	/**
	 * 
	 * @return true if there is a list with products for sale
	 */
	public boolean hasProductsForSale() {
		return productsForSale.hasProductsForSale();
	}

	/**
	 * 
	 * @param codigoReserva Reservation Code of the delivery
	 */
	public void addDelivery(String codigoReserva) {
		this.deliveries.add(codigoReserva);
	}

}
