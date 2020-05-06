package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.Chronology;
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
			if(comercianteInfo.hasProductsForSale()) {
				boolean inRange = comercianteInfo.getCoordinates().distanciaEmMetros(this.coordinates) <= raio;
				
				LocalDateTime aux1 = comercianteInfo.getProductsForSale().getHoraFim();

				boolean startsBeforeEnd = horaInicio.toLocalTime().isBefore(aux1.toLocalTime());
				///////// so pra debbug /////////////////////////////
				System.out.println(startsBeforeEnd);
				// no caso da Felismina a primeira vez neste boolean fica a -1
				
				LocalDateTime aux2 = comercianteInfo.getProductsForSale().getHoraInicio();
				boolean endsAfterStart = horaFim.toLocalTime().isAfter(aux2.toLocalTime());
				///////// so pra debbug /////////////////////////////
				System.out.println(endsAfterStart);
				// no caso da Felismina a primeira vez neste boolean fica a +1
				
				if( inRange && startsBeforeEnd && endsAfterStart) {
					merchInfo.add(comercianteInfo);
					System.out.println("boleans funcionaram");
				}
			}
		}
		if(merchInfo.isEmpty()) {
			System.out.println("pesquisa esta vazia");
			//mandar erro
		}
		return merchInfo;
	}

}
