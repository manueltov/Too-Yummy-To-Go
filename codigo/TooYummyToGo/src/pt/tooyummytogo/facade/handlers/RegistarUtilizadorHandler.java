package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.UserCatalog;

public class RegistarUtilizadorHandler {
	/**
	 * Regista um utilizador normal.
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password) {
		UserCatalog.adicionaUtilizador(username,password);
		
	}

}
