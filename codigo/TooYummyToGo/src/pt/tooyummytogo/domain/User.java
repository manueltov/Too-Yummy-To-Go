package pt.tooyummytogo.domain;

public class User {
	
	private String username;
	private String password;//ver melhor se isto pode ser
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean confirmPassword(String pw) {
		return this.password.equals(pw);
	}

	public String getUsername() {
		return this.username;
	}

}
