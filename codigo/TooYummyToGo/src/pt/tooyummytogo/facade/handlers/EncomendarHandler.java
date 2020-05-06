package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;

public class EncomendarHandler {

	private User currentUser;
	private ComercianteInfo merchInfo;
	
	private SearchHandler search;
	
	public EncomendarHandler(User currentUser) {
		this.currentUser = currentUser;
		this.search = new SearchHandler();
	}

	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinates) {
		this.search.indicaLocalizacaoActual(coordinates);
		return this.search.searchIt();
	}

	public List<ComercianteInfo> redefineRaio(int i) {
		this.search.redefineRaio(i);
		return this.search.searchIt();
	}

	public List<ComercianteInfo> redefinePeriodo(LocalDateTime start, LocalDateTime end) {
		this.search.redefinePeriodo(start, end);
		return this.search.searchIt();
	}

	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) {
		return comercianteInfo.escolheComerciante();
	}

	public void indicaProduto(ProdutoInfo p, int i) {
		
	}

	public String indicaPagamento(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}

}
