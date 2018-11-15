import java.util.*;

public class IndianPoker_user {
	int nujuk=0;
	String ID;
	int chip;
	ArrayList card;
	boolean isGiveup;

	//constructor for debug
	public IndianPoker_user(String autoID, int autoChip) {
		this.ID = autoID;
		this.chip = autoChip;
	}

	//show whose chip
	public int getChip() {
		return chip;
	}

	public int getNujuk() {
		return nujuk;
	}
	
	//initialize chip before game start
	int setChip() {
		Scanner fromKeyboard = new Scanner(System.in);
		System.out.println("Input your chip");
		try {
			int chips = fromKeyboard.nextInt();
			this.chip = chips;
		}catch(InputMismatchException e) {
			// int : from -32767 to 32767
			System.out.println("can't exceed 32767");
		}finally {
			return chip;
		}
	}

	//give pandon to winner
	void giveChipToWinner(int pandon) {
		this.chip = this.chip + pandon;
	}

	//bet chip by user
	int betChipInput() {
		while(true) {
			System.out.println("How much do you wanna bet");
			Scanner fromKeyboard = new Scanner(System.in);
			try {
				int bet = fromKeyboard.nextInt();
				//exceed
				if(bet > this.chip) {
					System.out.println("Can't bet over your chip, now you have "+this.chip);
					continue;
				}
				//give up
				else if (bet == 0) {
					System.out.println(this.ID +" give up the bet");
					this.isGiveup = true;
				}
				this.chip = this.chip - bet;
				nujukChip(bet);
				return bet;
				
			}catch(InputMismatchException e)
			{
				System.out.println("Input integer");
			}
		}
	}

	boolean getGiveUpState() {
		return this.isGiveup;
	}
	
	void setGiveUpState(boolean state) {
		this.isGiveup = state;
	}
	
	//bet chip at the beginning of the game
	int defaultChipBet(int bet) {
		this.chip = chip - bet;
		nujukChip(bet);
		return bet;
	}

	void initializeNujuk() {
		this.nujuk = 0; 
	}
	
	int nujukChip(int betAmount) {
		nujuk = nujuk+betAmount;
		return nujuk;
	}

	String getID() {
		return ID;
	}

	//initialize user's ID for game
	void setID() {
		Scanner fromKeyboard = new Scanner(System.in);
		System.out.println("Input your ID");
		String ID = fromKeyboard.nextLine();
		this.ID = ID;
	}

	//show whole card list of course nobody use this.
	ArrayList getCardList() {
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
