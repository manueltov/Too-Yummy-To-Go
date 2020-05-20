package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.UserCatalog;
import pt.tooyummytogo.exceptions.UserAlreadyExistsException;

public class RegistarUtilizadorHandler {

	/*
	 * UserCatalog - Users Catalog
	 */
	private UserCatalog userCat;

	/**
	 * Constructor of RegistarUtilizadorHandler
	 * 
	 * @param userCat
	 */
	public RegistarUtilizadorHandler(UserCatalog userCat) {
		this.userCat = userCat;
	}

	/**
	 * Regista um utilizador normal.
	 * 
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password) {
		try {
			this.userCat.adicionaUtilizador(username, password);
		} catch (UserAlreadyExistsException e) {
			System.err.println("Error: User already exists.");
			// e.printStackTrace(); //Uncomment to see error
		}
	}
}
