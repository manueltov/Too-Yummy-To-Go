package pt.tooyummytogo.facade.utils.strategies;

import java.time.LocalDateTime;
import java.util.List;

import pt.tooyummytogo.exceptions.NoMerchantInAreaException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public interface SearchStrategy {

	public LocalDateTime horaInicio = LocalDateTime.now();
	public LocalDateTime horaFim = horaInicio.plusHours(1);
	public Float raio = 5000f;
	public final PosicaoCoordenadas coordinates = null;

	public List<ComercianteInfo> escolheMerchant(LocalDateTime horaInicio, LocalDateTime horaFim, Float raio,
			PosicaoCoordenadas coordinates) throws NoMerchantInAreaException;

}