package pt.tooyummytogo.domain;

import java.util.HashMap;
import java.util.Optional;

public class UserCatalog {
	
	private HashMap<String, User> userCat = new HashMap<>();
	
	public void adicionaUtilizador (String username, String password) {
		userCat.put(username, new User(username, password));
	}
	
	public Optional<User> tryLogin(String user, String password) {
		/*
		if (utilizadores.containsKey(user) && utilizadores.get(user).hasPassword(pw)) {
			return Optional.of(utilizadores.get(user));
		} else {
			return Optional.empty();
		}
		*/
		return Optional.ofNullable(userCat.get(user)).filter(u -> u.hasPassword(password));
	}
}
