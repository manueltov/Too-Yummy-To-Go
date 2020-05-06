package pt.tooyummytogo.domain;

import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class Delivery {
	
	private User user;
	private ComercianteInfo merchInfo;
	private Order ord;
	private boolean isPayed;
	
	public Delivery(User user, ComercianteInfo merchInfo, Order ord) {
		this.user = user;
		this.merchInfo = merchInfo;
		this.ord = ord;
		this.isPayed = false;
	}

	public boolean isPayed() {
		return isPayed;
	}

	public void setPayed(boolean payed) {
		this.isPayed = payed;
	}
	
	public double totalPrice() {
		return this.ord.getTotal();
	}

	public String generateCode() {
		return Math.abs(hashCode())+"";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((merchInfo == null) ? 0 : merchInfo.hashCode());
		result = prime * result + ((ord == null) ? 0 : ord.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	
}
