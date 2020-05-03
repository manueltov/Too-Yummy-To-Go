package pt.tooyummytogo.domain;

import java.util.HashMap;

public class UserCatalog {
	
	private static HashMap<String, User> userCat;

	public UserCatalog() {
		userCat = new HashMap<>();
	}

	public static void adicionaUtilizador(String username, String password) {
		userCat.put(username, new User(username, password));
	}
	
	public Boolean tryLogin(String user, String password) {
		return userCat.containsKey(user) && userCat.get(user).confirmPassword(password);
	}
}
