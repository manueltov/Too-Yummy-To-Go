package pt.tooyummytogo.facade.utils.paymentAdapter;

public interface PaymentAdapter {

	/**
	 * 
	 * @return true if payment is valid
	 */
	public boolean validate();

	/**
	 * block a paycard's ammount for further transfer 
	 * @param amount
	 */
	public void block(double amount);

	/**
	 * charge the paycard
	 * @param amount
	 */
	public void charge(double amount);

	/**
	 * Creates a paycard
	 * @param cardNumber
	 * @param ccv
	 * @param monthYear month/year
	 */
	public void setCard(String cardNumber, String ccv, String monthYear);

}
