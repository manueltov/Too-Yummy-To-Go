package pt.tooyummytogo.facade;

import java.util.Optional;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.domain.User;
import pt.tooyummytogo.domain.UserCatalog;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

/**
 * Esta é a classe do sistema.
 */
public class TooYummyToGo {
	private UserCatalog userCat = new UserCatalog();

	// UC1
	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler();
	}
	
	/**
	 * Returns an optional Session representing the authenticated user.
	 * @param username
	 * @param password
	 * @return
	 * 
	 * UC2
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		Optional<User> currentUser = userCat.tryLogin(username, password);
		if(currentUser.isPresent()){
			return Optional.of(new Sessao(currentUser.get()));
		}
		else{
			return Optional.empty();
		}
	}

	// UC3
	public RegistarComercianteHandler getRegistarComercianteHandler() {
		return new RegistarComercianteHandler();
	}
	

}