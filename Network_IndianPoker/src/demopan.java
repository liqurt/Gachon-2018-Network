
public class GAME {
	public static void main(String[] args) {

		//make
		IndianPoker_user[] USERS = new IndianPoker_user[2];
		for(int i =0 ; i<USERS.length ; i++) {
			if(i==0)
				USERS[i] = new IndianPoker_user("Demopan",200);
			else
				USERS[i] = new IndianPoker_user("Solly",200);
			//use these 2 lines for real-game
			//USERS[i].setID();
			//USERS[i].setChip();
			USERS[i].setCard();
			System.out.println("ID : "+USERS[i].getID()+"\nChip : "+USERS[i].getChip());
		}
		run(USERS[0],USERS[1]);
	}

	public static void run(IndianPoker_user user0, IndianPoker_user user1) {
		IndianPoker_Server dealer = new IndianPoker_Server();
		while(true) {
			//show information before game
			System.out.println();
			System.out.println(user0.getID()+" have "+user0.getChip() + "chips");
			System.out.println(user1.getID()+" have "+user1.getChip() + "chips");
			
			//check user broke and game number
			if((user0.getChip() == 0) || (user1.getChip()==0) || (10<=dealer.getNowGame())) {
				if(user0.getChip() > user1.getChip())
					System.out.println(user0.getID()+" win");
				else if(user0.getChip() == user1.getChip())
					System.out.println("draw");
				else {
					System.out.println(user1.getID()+" win");
				}
				break;
			}

			//initialize the game
			System.out.println("#"+dealer.getNowGame() + " game start");
			dealer.setPandon(user0.defaultChipBet(1), user1.defaultChipBet(1));
			System.out.println("Pandon : "+dealer.getPandon());
			System.out.println(user0.getID()+" 's card : "+user0.showCard(dealer.getNowGame()));
			System.out.println(user1.getID()+" 's card : "+user1.showCard(dealer.getNowGame()));
			
			
			//bet
			/* 1번은 되는데, 계속 베팅하고 싶은데,  하이고
			int tempChip;
			while(true) {
				tempChip = user0.betChipInput();
				System.out.println(user0.getID()+" bet "+tempChip + "chips");
				dealer.setPandon(tempChip, 0);
				System.out.println(user0.getID()+" have betted "+user0.nujuk);
				System.out.println("Now pandon : " + dealer.getPandon());
				
				tempChip = user1.betChipInput();
				System.out.println(user1.getID()+" bet "+tempChip + "chips");
				System.out.println(user1.getID()+" have betted "+user1.nujuk);
				dealer.setPandon(0, tempChip);
				System.out.println("Now pandon : " + dealer.getPandon());
				break;
			}*/
			
			int turn=0;
			int tempChip = user0.betChipInput();
			System.out.println(user0.getID()+" bet "+tempChip + "chips");
			dealer.setPandon(tempChip, 0);
			System.out.println(user0.getID()+" have betted "+user0.getNujuk());
			System.out.println("Now pandon : " + dealer.getPandon());
			turn++;
			
			while((turn==1) ||(turn==0)) {
				//user0 turn
				if(turn==0) {
					while(turn==0) {
						tempChip = user0.betChipInput();
						//ERROR : bet should equal or more than opposite's (except 0)
						if((user0.getNujuk() < user1.getNujuk()) && (tempChip!=0)) {
							System.out.println("bet amount should equal or more than "+user1.getID()+"'s nujuk chips : "+user1.getNujuk());
							continue;
						}
						//Bet over : both players nujuk are equal
						else if(user0.getNujuk() == user1.getNujuk()) {
							System.out.println(user0.getNujuk() +" : "+user1.getNujuk()+"\nboth players have same nujuk bet amount, so end the bet");
							turn=2;
							break;
						}
						//Bet continue : this player nujuk is more than opposite's
						else {
							System.out.println(user0.getID() + " bet " + tempChip + " and his nujuk is : " + user0.getNujuk());
							turn=1;
							break;
						}
					}
				}
				//user1 turn
				else if(turn==1) {
					while(turn==1) {
						tempChip = user1.betChipInput();
						//ERROR : bet should equal or more than opposite's
						if(user1.getNujuk() < user0.getNujuk()) {
							System.out.println("bet amount should equal or more than "+user0.getID()+"'s nujuk chips : "+user0.getNujuk());
							continue;
						}
						//Bet over : both players nujuk are equal
						else if(user0.getNujuk() == user1.getNujuk()) {
							System.out.println(user0.getNujuk() +" : "+user1.getNujuk()+"\nboth players have same nujuk bet amount, so end the bet");
							turn=2;
							break;
						}
						//Bet continue : this player nujuk is more than opposite's
						else {
							System.out.println(user1.getID() + " bet " + tempChip + " and his nujuk is : " + user1.getNujuk());
							turn=0;
							break;
						}
					}
				}
				if(turn ==2) {
					break;
				}
			}
			
			
			//who's winner?
			//user0 win
			if(((user0.showCard(dealer.getNowGame()) > user1.showCard(dealer.getNowGame())) || user1.isGiveup==true) && user0.isGiveup==false) {
				System.out.println(user0.getID() + " win!");
				System.out.println("Pandon : "+dealer.getPandon() + " is in " + user0.getID() + "'s pocket");
				//user1 give up and his card was 10
				if((user1.showCard(dealer.getNowGame())==10) && (user1.getGiveUpState())) {
					System.out.println(user1.getID() + " give up the game even his card is " + user1.showCard(dealer.getNowGame()) + " how stupid it is");
					dealer.setPandon(0, user1.defaultChipBet(10));
					System.out.println("So," + user0.getID() +" earn 10 bonus chips from "+user1.getID());
				}
				user0.giveChipToWinner(dealer.getPandon());
				dealer.setPandon(0,-dealer.getPandon());
			}
			//user1 sin
			else if(((user0.showCard(dealer.getNowGame()) < user1.showCard(dealer.getNowGame())) || user0.isGiveup==true) && user1.isGiveup==false) {
				System.out.println(user1.getID() + " win!");
				System.out.println("Pandon : "+dealer.getPandon() + " is in " + user1.getID() + "'s pocket");
				//user0 give up and his card was 10
				if((user0.showCard(dealer.getNowGame())==10) && (user0.getGiveUpState())) {
					System.out.println(user0.getID() + " give up the game even his card is " + user0.showCard(dealer.getNowGame()) + " how stupid it is");
					dealer.setPandon(user0.defaultChipBet(10) , 0);
					System.out.println("So," + user1.getID() +" earn 10 bonus chips from "+user0.getID());
				}
				user1.giveChipToWinner(dealer.getPandon());
				dealer.setPandon(0,-dealer.getPandon());
			}
			//draw
			else {
				System.out.println("DRAW!");
				//no need to initialize pandon :)
				System.out.println("Pandon : "+dealer.getPandon()+" will add to next game's pandon");
			}
			
			//initialize for next game
			user0.initializeNujuk();
			user1.initializeNujuk();
			user0.setGiveUpState(false);
			user1.setGiveUpState(false);
			dealer.countUpGame();
		}
	}
}
