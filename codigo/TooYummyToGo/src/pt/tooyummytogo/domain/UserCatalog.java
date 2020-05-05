package pt.tooyummytogo.domain;

import java.util.HashMap;
import java.util.Optional;

public class UserCatalog {
	
	private HashMap<String, User> userCat;

	public UserCatalog() {
		this.userCat = new HashMap<>();
	}

	public void adicionaUtilizador(String username, String password) {
		userCat.put(username, new User(username, password));
		///////// so pra debbug /////////////////////////////
		System.out.println("so pra dizer  q o " + username + " foi adiconado ao cataUser.");
	}
	
	public Optional<User> tryLogin(String username, String password) {
		if (this.userCat.containsKey(username) && this.userCat.get(username).confirmPassword(password)) {
			return Optional.of(userCat.get(username));
		} else {
			return Optional.empty();
		}
	}
}
