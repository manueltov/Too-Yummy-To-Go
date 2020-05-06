package pt.tooyummytogo;

import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class Sessao {
	
	private User currentUser;
	private ComercianteInfo currentMerch;
	
	public Sessao(User currentUser) {
		this.currentUser = currentUser; //ver o q se passa aqui
		///////// so pra debbug /////////////////////////////
		System.out.println("o " + currentUser.getUsername() + " ja entrou na sua sessao");
	}
	
	public Sessao(ComercianteInfo currentMerch) {
		this.currentMerch = currentMerch; //ver o q se passa aqui
		///////// so pra debbug /////////////////////////////
		System.out.println("o " + currentMerch.getUsername() + " ja entrou na sua sessao");
	}

	// UC4
	public AdicionarTipoDeProdutoHandler adicionarTipoDeProdutoHandler() {
		return new AdicionarTipoDeProdutoHandler(this.currentMerch);
	}

	// UC5
	public ColocarProdutoHandler getColocarProdutoHandler() {
		return new ColocarProdutoHandler(this.currentMerch);
	}
	
	//UC6
	public EncomendarHandler getEncomendarComerciantesHandler() {
		return new EncomendarHandler(this.currentUser);
	}
	
}
