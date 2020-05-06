package pt.tooyummytogo.domain;

import java.util.HashMap;
import java.util.Optional;

import pt.tooyummytogo.exceptions.UserAlreadyExistsException;

public class UserCatalog {
	
	private HashMap<String, User> userCat;

	public UserCatalog() {
		this.userCat = new HashMap<>();
	}

	public void adicionaUtilizador(String username, String password) throws UserAlreadyExistsException {
		if (userCat.containsKey(username)) {
			throw new UserAlreadyExistsException();
		}
		userCat.put(username, new User(username, password));
	}
	
	public Optional<User> tryLogin(String username, String password) {
		if (this.userCat.containsKey(username) && this.userCat.get(username).confirmPassword(password)) {
			return Optional.of(userCat.get(username));
		} else {
			return Optional.empty();
		}
	}
}
