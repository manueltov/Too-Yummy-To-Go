package pt.tooyummytogo.domain;

import java.util.HashMap;

public class UserCatalog {
	
	private static HashMap<String, User> userCat;

	public UserCatalog() {
		userCat = new HashMap<>();
	}

	public void adicionaUtilizador(String username, String password) {
		userCat.put(username, new User(username, password));
	}
	
	public Boolean tryLogin(String username, String password) {
		if (UserCatalog.userCat.containsKey(username) && UserCatalog.userCat.get(username).hasPassword(password)) {
			return true;
		} else {
			return false;
		}
	}
}
