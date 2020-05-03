package pt.tooyummytogo.domain;

public class User {
	
	private String username;
	private String password;//ver melhor se isto pode ser
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean confirmPassword(String password2) {
		return this.password==password2;
	}

}
