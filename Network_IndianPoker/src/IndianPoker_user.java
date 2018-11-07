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
		System.out.println("����� Ĩ ������ �Է����ּ���");
		try {
			int chips = fromKeyboard.nextInt();
			this.chip = chips;
		}catch(InputMismatchException e) {
			System.out.println("Ĩ ������ 32767�� �ʰ��Ҽ� �����ϴ�.");
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
		System.out.println("����� ID�� �Է����ּ���");
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
