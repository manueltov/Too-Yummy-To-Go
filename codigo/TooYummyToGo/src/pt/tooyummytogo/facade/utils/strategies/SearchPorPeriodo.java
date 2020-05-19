package pt.tooyummytogo.facade.utils.strategies;

import java.time.LocalDateTime;
import java.util.List;

import pt.tooyummytogo.exceptions.NoMerchantInAreaException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.utils.Search;

public class SearchPorPeriodo implements SearchStrategy {

    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    private Float raio = 5000f;
    private PosicaoCoordenadas coordinates;

    public List<ComercianteInfo> redefinePeriodo(LocalDateTime horaInicio,LocalDateTime horaFim, PosicaoCoordenadas coordinates) {
        this.horaFim = horaFim;
        this.horaInicio = horaInicio;
        this.coordinates = coordinates;
        return escolheMerchant(this.horaInicio, this.horaFim, raio, this.coordinates);
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