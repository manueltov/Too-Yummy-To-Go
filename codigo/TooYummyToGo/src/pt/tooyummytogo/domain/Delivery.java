package pt.tooyummytogo.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import pt.tooyummytogo.facade.dto.ComercianteInfo;

public class Delivery {

	private User user;
	private ComercianteInfo merchInfo;
	private Order ord;
	private boolean isPayed;

	/**
	 * Constructor of Delivery
	 * 
	 * @param user      current delivery
	 * @param merchInfo chosen merchant
	 * @param ord       order issued by the user to merchInfo
	 */
	public Delivery(User user, ComercianteInfo merchInfo, Order ord) {
		this.user = user;
		this.merchInfo = merchInfo;
		this.ord = ord;
		this.isPayed = false;
	}

	/**
	 * 
	 * @return boolean if payment is complete
	 */
	public boolean isPayed() {
		return isPayed;
	}

	/**
	 * 
	 * @param payed boolean if payment is complete
	 */
	public void setPayed(boolean payed) {
		this.isPayed = payed;
	}

	/**
	 * 
	 * @return total price in the basket
	 */
	public double totalPrice() {
		return round(this.ord.getTotal(), 2);
	}

	private static double round(double value, int places) {
		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * generate hashCode
	 * 
	 * @return
	 */
	public String generateCode() {
		return Math.abs(hashCode()) + "";
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
