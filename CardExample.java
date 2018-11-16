
import java.util.Scanner;
import java.util.Random;

public class CardExample {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Deck deck = new Deck();
		int round = 1;
		Player computer = new Player();
		Player player = new Player();

		computer.getCard(deck); // PC 카드 수령
		player.getCard(deck); // 플레이어 카드 수령
		player.setCoin(50); // 플레이어 코인 50개 지급
		computer.setCoin(50);

		while (round < 11) { // 게임시작
			int com = computer.getCard(); // PC가 가져온 카드 1개
			int play = player.getCard(); // 플레이어가 가져온 카드 1개
			if (player.getCoin() < 1) {
				System.out.println("┌===========================================┐");
				System.out.println("│　====게임에서 패배하셨습니다. 다음에 또 도전하세요!=====　│");
				System.out.println("└===========================================┘");
				System.exit(0);
			} else if (computer.getCoin() < 1) {
				System.out.println("┌===========================================┐");
				System.out.println("│　====게임에서 승리하셨습니다. 다음에 또 도전하세요!=====　│");
				System.out.println("└===========================================┘");
				System.exit(0);
			} else {
				System.out.println("");
				computer.pcCardShow();
				player.playerCardShow();
				System.out.println("=========================================================");
				System.out.println(
						"=======현재 나의 코인 : " + player.getCoin() + " ======= | ======= 현재 라운드 : " + round + "=======");
				System.out.println("컴퓨터의 카드는 숫자는 위와 같습니다. 베팅하시겠습니까? 아니면 포기하시겠습니까?");
				System.out.println("=========================================================");
				System.out.print("베팅은  [ 1 ] 을  포기는 [ 2 ]를 입력하십시오 >");
				int choice = scan.nextInt();
				int bet_num;
				int betting = 1;
				Random rand = new Random();
				int combetting = rand.nextInt(10) + 1;;

				if (combetting > computer.getCoin()) {
					combetting = rand.nextInt(computer.getCoin()) + 1;
				}
				if (choice == 1) {
					while (true) {
						System.out.println("PC의 베팅한 코인  갯수: [ " + combetting + " ]");
						System.out.print("배팅할 코인 갯수를 입력하십시오 >");
						bet_num = scan.nextInt();
						while (bet_num > player.getCoin()) {
							System.out.println("코인이 부족합니다. 현재 가지고 계신 코인은" + player.getCoin() + "개 입니다.");
							System.out.print("배팅할 코인 갯수를 입력하십시오 >");
							bet_num = scan.nextInt();
						}
						betting += bet_num;
						if (betting == combetting)
							break;
						bet_num = rand.nextInt(10) + 1;
						if (bet_num > computer.getCoin()) {
							bet_num = rand.nextInt(computer.getCoin()) + 1;
						}
						combetting += bet_num;
						if (betting == combetting)
							break;
					}

					if (com > play) {
						System.out.println(round + " 라운드 결과 : PC 승");
						System.out.println("  PC의 카드 숫자    :" + com);
						System.out.println("플레이어의 카드 숫자 :" + play);
						System.out.println("남은 코인  갯수 :  [ " + (player.getCoin() - betting) + " ]");
						player.plusCoin(-betting);
					} else if (com < play) {
						System.out.println(round + " 라운드 결과 : 플레이어 승");
						System.out.println("  PC의 카드 숫자    :" + com);
						System.out.println("플레이어의 카드 숫자 :" + play);
						System.out.println("코인 획득  :  [ " + (betting + combetting) + " ] 개");
						player.plusCoin((betting + combetting)); // 코인 추가
					} else if (com == play) {
						System.out.println(round + " 라운드 결과 : 무승부");
						System.out.println("  PC의 카드 숫자    :" + com);
						System.out.println("플레이어의 카드 숫자 :" + play);
					}

				} else if (choice == 2) {
					System.out.println("베팅을 포기하셨습니다. 코인 한 개가 줄어들고 새 라운드를 시작합니다.");
					System.out.println("플레이어의 카드는 [" + play + "] 였습니다.");
					player.minusCoin(1);
					if (play == 10)
						player.minusCoin(10);
				}
				round++;
			}
		}

	}

}

//무승부 처리 베팅 유지, 사용한 카드 삭제 기능 추가 필요
