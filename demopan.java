
public class demopan {
	public static void main(String[] args) {
		IndianPoker_user demoman = new IndianPoker_user();
		demoman.setID();
		demoman.setCard();
		System.out.println(demoman.getID() + " have " +demoman.setChip() + " chips ");
		System.out.println(demoman.getCard());
		System.out.println(demoman.showCard(0));
		
		demoman.betChip(100);
		System.out.println(demoman.getChip());
		demoman.betChip(10);
		System.out.println(demoman.getChip());
		
		
		
		
		IndianPoker_user soldier = new IndianPoker_user();
		soldier.setID();
		soldier.setCard();
		
		System.out.println(soldier.getID() + " have " +soldier.setChip() + " chips ");
		System.out.println(soldier.getCard());
		System.out.println(soldier.showCard(0));
		
		soldier.betChip(200);
		System.out.println(soldier.getChip());
		soldier.betChip(120);
		System.out.println(soldier.getChip());
	}
}
