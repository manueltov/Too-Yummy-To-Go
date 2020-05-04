package pt.tooyummytogo.domain;

public class User {
	
	private String username;
	private String password;//ver melhor se isto pode ser
	private UserType userType;
	
	public User(String username, String password, UserType userType) {
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public boolean confirmPassword(String password2) {
		return this.password==password2;
	}

	public UserType getUserType() {
		return this.userType;
	}

}
