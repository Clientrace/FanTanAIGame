package Manager;

public class AgentData {
	
	public static void move(int playerNumber, int handCard){
		int[] cards = new int[30];
		if(playerNumber==1){
			Player1.outputCard=Player1.cards[handCard]+1;
		}
		if(playerNumber==2){
			Player2.outputCard=Player2.cards[handCard]+1;
		}
		if(playerNumber==3){
			Player3.outputCard=Player3.cards[handCard]+1;
		}
	}
	
	public static int[] checkHand(int playerNumber){
		int[] cards = new int[30];
		if(playerNumber==1){
			for(int i=0;i<Player1.numCards;i++){
				cards[i] = Player1.cards[i];
			}
		}
		if(playerNumber==2){
			for(int i=0;i<Player2.numCards;i++){
				cards[i] = Player2.cards[i];
			}
		}
		if(playerNumber==3){
			for(int i=0;i<Player3.numCards;i++){
				cards[i] = Player3.cards[i];
			}
		}
		return cards;
	}
	
	public static int[] checkBoard(int suit){
		int cards[] = new int[14];
		if(suit==0){
			for(int i=0;i<14;i++){
				cards[i] = BoardData.getClubs(i);
			}
		}
		if(suit==1){
			for(int i=0;i<14;i++){
				cards[i] = BoardData.getHearts(i);
			}
		}
		if(suit==2){
			for(int i=0;i<14;i++){
				cards[i] = BoardData.getSpades(i);
			}
		}
		if(suit==3){
			for(int i=0;i<14;i++){
				cards[i] = BoardData.getDiamonds(i);
			}
		}
		return cards;
	}
	
	public static int getCardTop(int suit){
		return BoardData.getLowestCard(suit);
	}
	
	public static int getCardBottom(int suit){
		return BoardData.getHighestCard(suit);
	}
	
	public static int getNumCards(int playerNumber){
		if(playerNumber==1)
			return Player1.numCards;
		if(playerNumber==2)
			return Player2.numCards;
		if(playerNumber==3)
			return Player3.numCards;
		return 0;
	}
}
