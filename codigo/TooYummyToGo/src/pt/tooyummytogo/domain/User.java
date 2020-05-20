package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String username;
	private String password;
	private List<Order> lstOrder = new ArrayList<Order>();

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

	public Order createOrder() {
		Order ord = new Order();
		this.lstOrder.add(ord);
		return ord;
	}

}
