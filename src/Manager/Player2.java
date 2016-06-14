package Manager;

public class Player2 {
	public static int cards[] = new int[20];
	public static int numCards = 0;
	public static int outputCard = 0;
	
	public static void init(){
		cards = new int[20];
		numCards = 0;
		outputCard = 0;
	}
	
	public static void setCard(int c){
		cards[numCards] = c;
		numCards++;
	}
	
	public static void turn(int c){
		boolean found = false;
		for(int i=0;i<numCards;i++){
			if(c-1==cards[i]){
				found = true;
				break;
			}
		}
		if(found){
			int tempCards[] = new int[20];
			int counter = 0;
			for(int i=0;i<numCards;i++){
				if(c-1!=cards[i]){
					tempCards[counter] = cards[i];
					counter++;
				}
			}
			numCards--;
			for(int i=0;i<20;i++)
				cards[i] = 0;
			for(int i=0;i<numCards;i++)
				cards[i] = tempCards[i];
			BoardData.turn(c+1);
		}
	}
	public static void draw(){
		if(!Deck.isEmpty())
			setCard(BoardData.draw());
	}

}
