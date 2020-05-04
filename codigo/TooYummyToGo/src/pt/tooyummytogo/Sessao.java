package pt.tooyummytogo;

import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.exceptions.OnlyMerchantsCanAddProductsException;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class Sessao {
	
	private User currentUser;
	
	public Sessao(User currentUser) {
		this.currentUser = currentUser; //ver o q se passa aqui
	}

	// UC4
	public AdicionarTipoDeProdutoHandler adicionarTipoDeProdutoHandler() {
		try {
			return new AdicionarTipoDeProdutoHandler(this.currentUser);
		} catch (OnlyMerchantsCanAddProductsException e) {
			e.printStackTrace();
		}
		return null;
	}

	// UC5
	public ColocarProdutoHandler getColocarProdutoHandler() {
		return new ColocarProdutoHandler();
	}

	public EncomendarHandler getEncomendarComerciantesHandler() {
		return new EncomendarHandler();
	}
	
}
