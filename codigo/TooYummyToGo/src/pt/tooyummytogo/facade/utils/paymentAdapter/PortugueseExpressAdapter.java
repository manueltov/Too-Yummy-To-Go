package pt.tooyummytogo.facade.utils.paymentAdapter;

import pt.portugueseexpress.InvalidCardException;
import pt.portugueseexpress.PortugueseExpress;

public class PortugueseExpressAdapter implements PaymentAdapter {

	private PortugueseExpress api;

	public PortugueseExpressAdapter() {
		this.api = new PortugueseExpress();
	}

	@Override
	public boolean validate() {
		return this.api.validate();
	}

	@Override
	public void block(double amount) {
		try {
			this.api.block(amount);
		} catch (InvalidCardException e) {
			// e.printStackTrace();
			System.err.println("Error, blocking the card.");
		}
	}

	@Override
	public void charge(double amount) {
		try {
			this.api.charge(amount);
		} catch (InvalidCardException e) {
			// e.printStackTrace();
			System.err.println("Error, charging the card.");
		}
	}

	@Override
	public void setCard(String cardNumber, String ccv, String monthYear) {
		this.api.setNumber(cardNumber);

		int ccv_number = Integer.parseInt(ccv);
		this.api.setCcv(ccv_number);

		String[] monthAndYear = monthYear.split("/");
		int month = Integer.parseInt(monthAndYear[0]);
		monthAndYear[1] = "20" + monthAndYear[1];
		int year = Integer.parseInt(monthAndYear[1]);
		this.api.setMonth(month);
		this.api.setYear(year);
	}

}
