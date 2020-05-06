package pt.tooyummytogo.facade.dto;

import java.time.LocalDateTime;
import java.util.List;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.Product;
import pt.tooyummytogo.domain.ProductsForSale;
import pt.tooyummytogo.exceptions.ProductAlreadyExists;

public class ComercianteInfo {
	
	private Merchant merch;
	
	public ComercianteInfo(String merchUsername) {
		this.merch = MerchantCatalog.getMerchant(merchUsername);
	}
	
	public String getUsername() {
		return this.merch.getUsername();
	}
	
	public int addProductType(String tp, double price) throws ProductAlreadyExists {
		return this.merch.addProductType(tp, price);
	}
	
	public List<String> getProductsStringList() {
		return this.merch.getProductsStringList();
	}
	
	public Product getProduct(String tp) {
		return this.merch.getProduct(tp);
	}
	
	public void indicaProduto(String tp, int quantity) {
		this.merch.indicaProduto(tp, quantity);
	}
	
	public ProductsForSale getProductsForSale() {
		return this.merch.getProductsForSale();
	}
	
	public void confirmaHoras(LocalDateTime start, LocalDateTime end) {
		this.merch.confirmaHoras(start, end);
	}
	
	public PosicaoCoordenadas getCoordinates() {
		return this.merch.getCoordinates();
	}

	public List<ProdutoInfo> escolheComerciante() {
		return this.merch.getProductsInfoList();
	}

	public boolean hasProductsForSale() {
		return this.merch.hasProductsForSale();
	}

	@Override
	public String toString() {
		return "[ Comerciante: " + getUsername() + " ]";
	}

}