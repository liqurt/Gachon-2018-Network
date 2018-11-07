import java.util.*;

public class IndianPoker_user {

	String ID;
	int chip;
	ArrayList card;

	int getChip() {
		return chip;
	}

	int setChip() {
		Scanner fromKeyboard = new Scanner(System.in);
		System.out.println("당신의 칩 갯수를 입력해주세요");
		try {
			int chips = fromKeyboard.nextInt();
			this.chip = chips;
		}catch(InputMismatchException e) {
			System.out.println("칩 갯수는 32767을 초과할수 없습니다.");
		}finally {
			return chip;
		}
	}

	int betChip(int betAmount) {
		this.chip = chip - betAmount;
		return chip;
	}

	String getID() {
		return ID;
	}

	void setID() {
		Scanner fromKeyboard = new Scanner(System.in);
		System.out.println("당신의 ID를 입력해주세요");
		String ID = fromKeyboard.nextLine();
		this.ID = ID;
	}

	ArrayList getCard() {
		return this.card;
	}

	int showCard(int game) {
		int yourCard =(int) this.card.get(game);
		return yourCard;
	}

	void setCard() {
		this.card = new ArrayList<>(10);
		for(int i =1 ; i<=10 ; i++) {
			this.card.add(i);
		}
		Collections.shuffle(this.card);
	}

}
