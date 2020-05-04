package pt.tooyummytogo.domain;

import java.util.HashMap;
import java.util.Optional;

public class UserCatalog {
	
	private static HashMap<String, User> userCat;

	public UserCatalog() {
		userCat = new HashMap<>();
	}

	public void adicionaUtilizador(String username, String password, UserType userType) {
		userCat.put(username, new User(username, password, userType));
		System.out.println("so pra dizer  q o " + userType.toString() + " chamdo " + username + " foi adiconado.");
	}
	
	public Optional<User> tryLogin(String username, String password) {
		if (userCat.containsKey(username) && userCat.get(username).confirmPassword(password)) {
			return Optional.of(userCat.get(username));
		} else {
			return Optional.empty();
		}
	}
}
