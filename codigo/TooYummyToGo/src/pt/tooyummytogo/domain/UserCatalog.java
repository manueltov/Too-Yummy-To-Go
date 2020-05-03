package pt.tooyummytogo.domain;

import java.util.HashMap;
import java.util.Optional;

public class UserCatalog {
	
	private static HashMap<String, User> userCat;

	public UserCatalog() {
		userCat = new HashMap<>();
	}

	public static void adicionaUtilizador(String username, String password) {
		userCat.put(username, new User(username, password));
	}
	
	public Optional<User> tryLogin(String username, String password) {
		if (userCat.containsKey(username) && userCat.get(username).confirmPassword(password)) {
			return Optional.of(userCat.get(username));
		} else {
			return Optional.empty();
		}
	}
}
