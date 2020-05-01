package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.UserCatalog;

public class RegistarUtilizadorHandler {
	
	private UserCatalog userCat;
	
	public RegistarUtilizadorHandler() {
		this.userCat = new UserCatalog();
	}

	/**
	 * Regista um utilizador normal.
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password) {
		this.userCat.adicionaUtilizador(username, password);
	}

}
