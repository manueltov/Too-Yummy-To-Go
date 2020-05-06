package pt.tooyummytogo;

import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class Sessao {
	
	private User currentUser;
	private ComercianteInfo currentMerch;
	
	public Sessao(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public Sessao(ComercianteInfo currentMerch) {
		this.currentMerch = currentMerch;
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
