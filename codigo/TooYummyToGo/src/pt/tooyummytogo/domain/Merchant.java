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

	public Merchant(String username, String password, PosicaoCoordenadas posi) {
		this.username = username;
		this.password = password;
		this.posi = posi;
		this.lstProducts = new ArrayList<Product>();
		this.productsForSale = new ProductsForSale();
		this.deliveries = new ArrayList<String>();
	}

	public String getUsername() {
		return username;
	}

	boolean confirmPassword(String pw) {
		return this.password.equals(pw);
	}

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

	public List<String> getProductsStringList() {
		List<String> aux = new ArrayList<String>();
		for (Product prd : this.lstProducts) {
			aux.add(prd.getProductType());
		}
		return aux;
	}

	public Product getProduct(String tp) throws ProductNotFoundException {
		for (Product prdt : lstProducts) {
			if (prdt.getProductType().equals(tp))
				return prdt;
		}
		throw new ProductNotFoundException();
	}

	public void indicaProduto(String tp, int quantity) throws ProductNotFoundException {
		this.productsForSale.addProductForSale(getProduct(tp), quantity);
	}

	public ProductsForSale getProductsForSale() {
		return productsForSale;
	}

	public void confirmaHoras(LocalDateTime start, LocalDateTime end) {
		this.productsForSale.setHoraInicio(start);
		this.productsForSale.setHoraFim(end);
	}

	public PosicaoCoordenadas getCoordinates() {
		return this.posi;
	}

	public List<ProdutoInfo> getProductsInfoList() {
		List<ProdutoInfo> aux = new ArrayList<ProdutoInfo>();
		for (ProductInSale productInSale : this.productsForSale.getLstProductsForSale()) {
			aux.add(new ProdutoInfo(productInSale));
		}
		return aux;
	}

	public boolean hasProductsForSale() {
		return productsForSale.hasProductsForSale();
	}

	public void addDelivery(String codigoReserva) {
		this.deliveries.add(codigoReserva);
		// System.out.println("Reserva: "+codigoReserva+" adicionada ao comerciante à
		// lista de "+this.username);
	}

}
