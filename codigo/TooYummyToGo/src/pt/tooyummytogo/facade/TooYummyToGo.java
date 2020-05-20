package pt.tooyummytogo.facade;

import java.util.Optional;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.domain.MerchantCatalog;
import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.domain.UserCatalog;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

/**
 * Esta é a classe do sistema.
 */
public class TooYummyToGo {

	private UserCatalog userCat = new UserCatalog();
	private MerchantCatalog merchCat = new MerchantCatalog();

	// UC1
	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(userCat);
	}

	/**
	 * Returns an optional Session representing the authenticated user.
	 * 
	 * @param username
	 * @param password
	 * @requires currentUser != currentMerch
	 * @return
	 * 
	 *         UC2
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		Optional<User> currentUser = userCat.tryLogin(username, password);
		Optional<ComercianteInfo> currentMerch = merchCat.tryLogin(username, password);
		// if a User has an merchant account or vice-versa their credentials need to be
		// different otherwise the User will always sign in as a Merchant
		if (currentMerch.isPresent()) {
			return Optional.of(new Sessao(new ComercianteInfo(username)));
		} else if (currentUser.isPresent()) {
			return Optional.of(new Sessao(currentUser.get()));
		} else {
			return Optional.empty();
		}
	}

	// UC3
	public RegistarComercianteHandler getRegistarComercianteHandler() {
		return new RegistarComercianteHandler(merchCat);
	}

}
