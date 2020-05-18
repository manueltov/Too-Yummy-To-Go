package pt.tooyummytogo.facade.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.Chronology;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.exceptions.NoMerchantInArea;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class Search {
	
	private PosicaoCoordenadas coordinates;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFim;
	private Float raio;
	
	public Search() {
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

	public List<ComercianteInfo> searchIt() throws NoMerchantInArea {
		
		List<ComercianteInfo> merchCat = MerchantCatalog.getMerchantCatalog();
		List<ComercianteInfo> merchInfo = new ArrayList<ComercianteInfo>();
		
		for (ComercianteInfo comercianteInfo : merchCat) {
			if(comercianteInfo.hasProductsForSale()) {
				boolean inRange = comercianteInfo.getCoordinates().distanciaEmMetros(this.coordinates) <= raio;

				boolean startRange = horaInicio.toLocalTime().isBefore(comercianteInfo.getProductsForSale().getHoraFim().toLocalTime());
				boolean endRange = horaFim.toLocalTime().isAfter(comercianteInfo.getProductsForSale().getHoraInicio().toLocalTime());
				
				if( inRange && startRange && endRange) {
					merchInfo.add(comercianteInfo);
				}
			}
		}
		if(merchInfo.isEmpty()) {
			throw new NoMerchantInArea();
		}
		return merchInfo;
	}

}
