package pt.tooyummytogo;

import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class Sessao {
	
	private User currentUser;
	
	public Sessao(User currentUser) {
		this.currentUser = currentUser;
	}

	// UC4
	public AdicionarTipoDeProdutoHandler adicionarTipoDeProdutoHandler() {
		return new AdicionarTipoDeProdutoHandler(currentUser);
	}

	// UC5
	public ColocarProdutoHandler getColocarProdutoHandler() {
		return new ColocarProdutoHandler();
	}

	public EncomendarHandler getEncomendarComerciantesHandler() {
		return new EncomendarHandler();
	}
}
