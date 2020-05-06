package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class SearchHandler {
	
	private PosicaoCoordenadas coordinates;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;
	private Float raio;
	
	public SearchHandler() {
		this.horaInicio = LocalDateTime.now();
		this.horaFim = horaInicio.plusHours(1);
		this.raio = (float) 5000;
	}

	public void indicaLocalizacaoActual(PosicaoCoordenadas coordinates) {
		this.coordinates = coordinates;
	}

	public void redefineRaio(int i) {
		this.raio = (float) i;
	}

	public void redefinePeriodo(LocalDateTime start, LocalDateTime end) {
		this.horaInicio = start;
		this.horaFim = end;
	}

	public List<ComercianteInfo> searchIt() {
		List<ComercianteInfo> merchCat = MerchantCatalog.getMerchantCatalog();
		List<ComercianteInfo> merchInfo = new ArrayList<ComercianteInfo>();
		for (ComercianteInfo comercianteInfo : merchCat) {
			boolean inRange = comercianteInfo.getCoordinates().distanciaEmMetros(this.coordinates) <= raio;
			
			boolean startsBeforeEnd = horaInicio.compareTo(comercianteInfo.getProductsForSale().getHoraFim()) <= 0;
			///////// so pra debbug /////////////////////////////
			//System.out.println(horaInicio.compareTo(comercianteInfo.getProductsForSale().getHoraFim()));
			// no caso da Felismina a primeira vez neste boolean fica a -1
			
			boolean endsAfterStart = horaFim.compareTo(comercianteInfo.getProductsForSale().getHoraInicio()) >= 0;
			///////// so pra debbug /////////////////////////////
			//System.out.println(horaFim.compareTo(comercianteInfo.getProductsForSale().getHoraInicio()));
			// no caso da Felismina a primeira vez neste boolean fica a +1
			
			if( inRange && startsBeforeEnd && endsAfterStart) {
				merchInfo.add(comercianteInfo);
			}
		}
		return merchInfo;
	}

}
