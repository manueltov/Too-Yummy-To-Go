package pt.tooyummytogo.facade.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.exceptions.NoMerchantInAreaException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class Search {

	public List<ComercianteInfo> searchIt(LocalDateTime horaInicio, LocalDateTime horaFim, Float raio,
			PosicaoCoordenadas coordinates) throws NoMerchantInAreaException {

		List<ComercianteInfo> merchCat = MerchantCatalog.getMerchantCatalog();
		List<ComercianteInfo> merchInfo = new ArrayList<ComercianteInfo>();

		for (ComercianteInfo comercianteInfo : merchCat) {
			if (comercianteInfo.hasProductsForSale()) {
				boolean inRange = comercianteInfo.getCoordinates().distanciaEmMetros(coordinates) <= raio;

				boolean startRange = horaInicio.toLocalTime()
						.isBefore(comercianteInfo.getProductsForSale().getHoraFim().toLocalTime());
				boolean endRange = horaFim.toLocalTime()
						.isAfter(comercianteInfo.getProductsForSale().getHoraInicio().toLocalTime());

				if (inRange && startRange && endRange) {
					merchInfo.add(comercianteInfo);
				}
			}
		}
		if (merchInfo.isEmpty()) {
			throw new NoMerchantInAreaException();
		}
		return merchInfo;
	}

}
