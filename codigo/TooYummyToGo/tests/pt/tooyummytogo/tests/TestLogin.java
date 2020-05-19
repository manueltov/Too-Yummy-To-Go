package pt.tooyummytogo.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.facade.TooYummyToGo;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

class TestLogin {
	
	TooYummyToGo ty2g = new TooYummyToGo();
	
	@Test
	void testUtilizadorNormal() {
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Felismina", "hortadafcul");
		Optional<Sessao> talvezSessao = ty2g.autenticar("Felismina", "hortadafcul");
		assertNotEquals(Optional.empty(), talvezSessao);
	}
	
	@Test
	void testUtilizadorSemNomeComPass() {
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Felismina", "hortadafcul");
		Optional<Sessao> talvezSessao = ty2g.autenticar("", "hortadafcul");
		assertEquals(Optional.empty(), talvezSessao);
	}
	
	@Test
	void testUtilizadorSemNomeSemPass() {
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Felismina", "hortadafcul");
		Optional<Sessao> talvezSessao = ty2g.autenticar("", "");
		assertEquals(Optional.empty(), talvezSessao);
	}
	
	@Test
	void testUtilizadorComNomeSemPass() {
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Felismina", "hortadafcul");
		Optional<Sessao> talvezSessao = ty2g.autenticar("Felismina", "");
		assertEquals(Optional.empty(), talvezSessao);
	}
	
	@Test
	void testComercianteNormal() {
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();
		regComHandler.registarComerciante("Silvino", "bardoc2", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> talvezSessao = ty2g.autenticar("Silvino", "bardoc2");
		assertNotEquals(Optional.empty(), talvezSessao);
	}
	
	@Test
	void testComercianteComNomeSemPass() {
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();
		regComHandler.registarComerciante("Silvino", "bardoc2", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> talvezSessao = ty2g.autenticar("Silvino", "");
		assertEquals(Optional.empty(), talvezSessao);
	}
	
	@Test
	void testComercianteSemNomeComPass() {
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();
		regComHandler.registarComerciante("Silvino", "bardoc2", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> talvezSessao = ty2g.autenticar("", "bardoc2");
		assertEquals(Optional.empty(), talvezSessao);
	}
	
	@Test
	void testComercianteSemNomeSemPass() {
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();
		regComHandler.registarComerciante("Silvino", "bardoc2", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> talvezSessao = ty2g.autenticar("", "");
		assertEquals(Optional.empty(), talvezSessao);
	}
	
	@Test
    void testUtilizadorComAcessoAHandlerDoComerciante() {
        RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
        regHandler.registarUtilizador("Felismina", "hortadafcul");
        Optional<Sessao> talvezSessao = ty2g.autenticar("Felismina", "hortadafcul");

        assertThrows(NullPointerException.class, () -> {

            talvezSessao.ifPresent( (Sessao s) -> {
                AdicionarTipoDeProdutoHandler atp = s.adicionarTipoDeProdutoHandler();
                Random r = new Random();
                for (String tp : new String[] {"PÃ£o", "PÃ£o de LÃ³", "Mil-folhas"}) {
                    atp.registaTipoDeProduto(tp, r.nextDouble() * 10);
                }
            });
        });
    }
	
	@Test
	void testComercianteComAcessoAHandlerDoUtilizador() {
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();
		regComHandler.registarComerciante("Silvino", "bardoc2", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> talvezSessao3 = ty2g.autenticar("Silvino", "bardoc2");
		
		assertThrows(NullPointerException.class, () -> {
			
			talvezSessao3.ifPresent( (Sessao s) -> {
				EncomendarHandler lch = s.getEncomendarComerciantesHandler();
				List<ComercianteInfo> cs = lch.indicaLocalizacaoActual(new PosicaoCoordenadas(34.5, 45.2));
		});
	});
	
	
	
	}

}
	
	
	
	
	
	


