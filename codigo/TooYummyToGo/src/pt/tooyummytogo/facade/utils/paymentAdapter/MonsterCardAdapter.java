package pt.tooyummytogo.facade.utils.paymentAdapter;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

public class MonsterCardAdapter implements PaymentAdapter {

	private Card card;
	private MonsterCardAPI monsterCard;

	/**Constructor of payment */
	public MonsterCardAdapter() {
		this.monsterCard = new MonsterCardAPI();
	}

	@Override
	public boolean validate() {
		return this.monsterCard.isValid(this.card);
	}

	@Override
	public void block(double amount) {
		this.monsterCard.block(this.card, amount);
	}

	@Override
	public void charge(double amount) {
		this.monsterCard.charge(this.card, amount);
	}

	@Override
	public void setCard(String cardNumber, String ccv, String monthYear) {
		String[] monthAndYear = monthYear.split("/");
		monthAndYear[1] = "20" + monthAndYear[1];
		this.card = new Card(cardNumber, ccv, monthAndYear[0], monthAndYear[1]);
	}

}
