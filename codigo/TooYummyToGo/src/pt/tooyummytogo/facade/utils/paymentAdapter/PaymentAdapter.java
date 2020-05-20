package pt.tooyummytogo.facade.utils.paymentAdapter;

public interface PaymentAdapter {
		
	public boolean validate();
	
	public void block(double amount);
	
	public void charge(double amount);
	
	public void setCard (String cardNumber, String ccv, String monthYear);

}
