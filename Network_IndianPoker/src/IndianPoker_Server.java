import java.util.*;

public class IndianPoker_Server {
	
	int pandon;
	static int game=0; //NOW GAME
	
	public int getNowGame() {
		return game;
	}
	
	public void countUpGame() {
		game++;
	}

	public int getPandon() {
		return pandon;
	}
	
	public void setPandon(int left, int right) {
		pandon = left+right+pandon;
	}
}
