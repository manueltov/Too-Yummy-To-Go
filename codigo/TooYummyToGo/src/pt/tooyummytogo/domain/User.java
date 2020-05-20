package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String username;
	private String password;
	private List<Order> lstOrder = new ArrayList<Order>();

	/**
	 * Constructor of user
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * confirm if password is valid for login
	 * @param pw
	 * @return
	 */
	public boolean confirmPassword(String pw) {
		return this.password.equals(pw);
	}

	/**
	 * 
	 * @return username of current user
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Create order for current user
	 * @return order object
	 */
	public Order createOrder() {
		Order ord = new Order();
		this.lstOrder.add(ord);
		return ord;
	}

}
