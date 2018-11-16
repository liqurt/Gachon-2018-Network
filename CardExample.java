
import java.util.Scanner;
import java.util.Random;

public class CardExample {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Deck deck = new Deck();
		int round = 1;
		Player computer = new Player();
		Player player = new Player();

		computer.getCard(deck); // PC ī�� ����
		player.getCard(deck); // �÷��̾� ī�� ����
		player.setCoin(50); // �÷��̾� ���� 50�� ����
		computer.setCoin(50);

		while (round < 11) { // ���ӽ���
			int com = computer.getCard(); // PC�� ������ ī�� 1��
			int play = player.getCard(); // �÷��̾ ������ ī�� 1��
			if (player.getCoin() < 1) {
				System.out.println("��===========================================��");
				System.out.println("����====���ӿ��� �й��ϼ̽��ϴ�. ������ �� �����ϼ���!=====����");
				System.out.println("��===========================================��");
				System.exit(0);
			} else if (computer.getCoin() < 1) {
				System.out.println("��===========================================��");
				System.out.println("����====���ӿ��� �¸��ϼ̽��ϴ�. ������ �� �����ϼ���!=====����");
				System.out.println("��===========================================��");
				System.exit(0);
			} else {
				System.out.println("");
				computer.pcCardShow();
				player.playerCardShow();
				System.out.println("=========================================================");
				System.out.println(
						"=======���� ���� ���� : " + player.getCoin() + " ======= | ======= ���� ���� : " + round + "=======");
				System.out.println("��ǻ���� ī��� ���ڴ� ���� �����ϴ�. �����Ͻðڽ��ϱ�? �ƴϸ� �����Ͻðڽ��ϱ�?");
				System.out.println("=========================================================");
				System.out.print("������  [ 1 ] ��  ����� [ 2 ]�� �Է��Ͻʽÿ� >");
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
						System.out.println("PC�� ������ ����  ����: [ " + combetting + " ]");
						System.out.print("������ ���� ������ �Է��Ͻʽÿ� >");
						bet_num = scan.nextInt();
						while (bet_num > player.getCoin()) {
							System.out.println("������ �����մϴ�. ���� ������ ��� ������" + player.getCoin() + "�� �Դϴ�.");
							System.out.print("������ ���� ������ �Է��Ͻʽÿ� >");
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
						System.out.println(round + " ���� ��� : PC ��");
						System.out.println("  PC�� ī�� ����    :" + com);
						System.out.println("�÷��̾��� ī�� ���� :" + play);
						System.out.println("���� ����  ���� :  [ " + (player.getCoin() - betting) + " ]");
						player.plusCoin(-betting);
					} else if (com < play) {
						System.out.println(round + " ���� ��� : �÷��̾� ��");
						System.out.println("  PC�� ī�� ����    :" + com);
						System.out.println("�÷��̾��� ī�� ���� :" + play);
						System.out.println("���� ȹ��  :  [ " + (betting + combetting) + " ] ��");
						player.plusCoin((betting + combetting)); // ���� �߰�
					} else if (com == play) {
						System.out.println(round + " ���� ��� : ���º�");
						System.out.println("  PC�� ī�� ����    :" + com);
						System.out.println("�÷��̾��� ī�� ���� :" + play);
					}

				} else if (choice == 2) {
					System.out.println("������ �����ϼ̽��ϴ�. ���� �� ���� �پ��� �� ���带 �����մϴ�.");
					System.out.println("�÷��̾��� ī��� [" + play + "] �����ϴ�.");
					player.minusCoin(1);
					if (play == 10)
						player.minusCoin(10);
				}
				round++;
			}
		}

	}

}

//���º� ó�� ���� ����, ����� ī�� ���� ��� �߰� �ʿ�
