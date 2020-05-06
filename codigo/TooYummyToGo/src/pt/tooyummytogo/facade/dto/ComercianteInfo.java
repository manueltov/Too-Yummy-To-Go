package pt.tooyummytogo.facade.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.Merchant;
import pt.tooyummytogo.domain.MerchantCatalog;

public class ComercianteInfo {
	
	private List<ComercianteInfo> merchInfo;
	
	public ComercianteInfo() {
		this.merchInfo = new ArrayList<>();
		
	}
	
	public List<ComercianteInfo> getIndicaLocalizacaoActual(MerchantCatalog merchCat, PosicaoCoordenadas coordinate, LocalDateTime horaInicio, LocalDateTime horaFim, Float raio) {
		for(Merchant merch : merchCat.getMerchantCatalog()) {
			boolean firstCondition = merch.getCoordinates().distanciaEmMetros(coordinate) <= raio; //Rever o .getHour de baixo
			boolean secondCondition = horaInicio.getHour() <= merch.getProductsForSale().getHoraFim().getHour() && horaFim.getHour() >= merch.getProductsForSale().getHoraInicio().getHour();
			if( firstCondition && secondCondition) {
				merchInfo.add(merch.getUsername());
			}
		}
		return merchInfo;
	}
	
	//
	
}