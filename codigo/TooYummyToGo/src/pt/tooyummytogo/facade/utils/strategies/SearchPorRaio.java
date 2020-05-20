package pt.tooyummytogo.facade.utils.strategies;

import java.time.LocalDateTime;
import java.util.List;

import pt.tooyummytogo.exceptions.NoMerchantInAreaException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.utils.Search;

public class SearchPorRaio implements SearchStrategy {

    private LocalDateTime horaInicio = LocalDateTime.now();
    private LocalDateTime horaFim = horaInicio.plusHours(1);
    private Float raio;
    private PosicaoCoordenadas coordinates;

    public List<ComercianteInfo> redefineRaio(Float raio,PosicaoCoordenadas coordinates) {
        this.raio = raio;
        this.coordinates = coordinates;
        return escolheMerchant(horaInicio, horaFim, this.raio, this.coordinates);
}

    @Override
    public List<ComercianteInfo> escolheMerchant(LocalDateTime horaInicio, LocalDateTime horaFim, Float raio,
        PosicaoCoordenadas coordinates) {
        Search s = new Search();
        try {
            return s.searchIt(horaFim, horaFim, raio, coordinates);
        } catch (NoMerchantInAreaException e) {
            System.err.println("NoMerchantInArea");
        }
        return null;
    }
    
    
}