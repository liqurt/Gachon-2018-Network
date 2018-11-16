
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	List<Integer> cardList = new ArrayList<>();

	public Deck() {
		int[] num = {1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10};

		for (int j = 0; j < num.length; j++) {
			cardList.add(num[j]);
		}

	}

	public void shuffle() {
		Collections.shuffle(cardList);
	}

	public Integer deal() {
		shuffle();
		return cardList.remove(0);

	}

}
