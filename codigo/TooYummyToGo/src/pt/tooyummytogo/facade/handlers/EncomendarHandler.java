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
	private MerchantCatalog merchCat;
	private ComercianteInfo merchInfo;
	
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;
	private Float raio;
	
	public EncomendarHandler(User currentUser, MerchantCatalog merchCat) {
		this.currentUser = currentUser;
		this.merchCat = merchCat;
	}
/*
	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinate) {
		this.raio = (float) 5000;
		this.horaInicio = LocalDateTime.now();
		this.horaFim = horaInicio.plusHours(1);
		return this.merchInfo.getIndicaLocalizacaoActual(merchCat, coordinate, horaInicio, horaFim, raio);
	}
*/
	public List<ComercianteInfo> redefineRaio(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ComercianteInfo> redefinePeriodo(LocalDateTime now, LocalDateTime plusHours) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	public void indicaProduto(ProdutoInfo p, int i) {
		// TODO Auto-generated method stub
		
	}

	public String indicaPagamento(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}

}
