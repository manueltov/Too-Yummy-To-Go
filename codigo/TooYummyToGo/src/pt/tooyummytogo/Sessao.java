package pt.tooyummytogo;

import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class Sessao {
	
	private User currentUser;
	
	public Sessao(User currentUser) {
		this.currentUser = currentUser; //ver o q se passa aqui
		///////// so pra debbug /////////////////////////////
		System.out.println("o " + currentUser.getUsername() + " ja entrou na sua sessao");
	}

	// UC4
	public AdicionarTipoDeProdutoHandler adicionarTipoDeProdutoHandler() {
		return new AdicionarTipoDeProdutoHandler(this.currentUser);
	}

	// UC5
	public ColocarProdutoHandler getColocarProdutoHandler() {
		return new ColocarProdutoHandler(this.currentUser);
	}

	public EncomendarHandler getEncomendarComerciantesHandler() {
		return new EncomendarHandler();
	}
	
}
