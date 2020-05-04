package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.UserCatalog;
import pt.tooyummytogo.domain.UserType;

public class RegistarUtilizadorHandler {
	
	private UserCatalog userCat;

	public RegistarUtilizadorHandler(UserCatalog userCat) {
		this.userCat = userCat;
	}

	/**
	 * Regista um utilizador normal.
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password) {
		this.userCat.adicionaUtilizador(username,password, UserType.USER);
	}

}
