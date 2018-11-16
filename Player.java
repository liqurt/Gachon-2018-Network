
import java.util.ArrayList;
import java.util.List;

public class Player {
	private int coin;
	Deck d = new Deck();
	List<Integer> playerList = new ArrayList<>();

	public void getCard(Deck deck) {
		playerList.add(deck.deal());
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public void plusCoin(int coin) {
		this.coin = this.coin + coin;
	}

	public void minusCoin(int coin) {
		this.coin = this.coin - coin;
	}

	public int getCard() {
		return playerList.get(0);
	}

	public void pcCardShow() {
		getCard(d);
		if (getCard() < 10) {
			System.out.println("          ¦£¦¡¦¤");
			System.out.println(" PCÀÇ Ä«µå : ¦¢" + getCard() + "¦¢");
			System.out.println("          ¦¦¦¡¦¥");
			playerList.remove(0);
		}
	}

	public void playerCardShow() {
		getCard(d);
		getCard();
		playerList.remove(0);
	}

}
