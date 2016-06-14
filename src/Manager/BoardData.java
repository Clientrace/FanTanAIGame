package Manager;

public class BoardData {
	public static int clubs[] = new int[14];
	public static int hearts[] = new int[14];
	public static int spades[] = new int [14];
	public static int diamonds[] = new int[14];
	
	public static void init(){
		clubs = new int[14];
		hearts = new int[14];
		spades = new int[14];
		diamonds = new int[14];
	}
	
	public static void turn(int c){
		c = c - 1;
		if(c==6){
			clubs[6] = c;
		}
		else if(c==19){
			hearts[6] = c;
		}
		else if(c==32){
			spades[6] = c;
		}
		else if(c==45){
			diamonds[6] = c;
		}
		else{
			if(c>=1&&c<=13){
				if(c==getLowestCard(0)){
					clubs[c] = c;
				}
				if(c==getHighestCard(0)){
					clubs[c] = c;
				}
			}
			if(c>=14&&c<=26){
				if(c==getLowestCard(1)){
					hearts[c-13] = c;
				}
				if(c==getHighestCard(1)){
					hearts[c-13] = c;
				}
			}
			if(c>=27&&c<=39){
				if(c==getLowestCard(2)){
					spades[c-26] = c;
				}
				if(c==getHighestCard(2)){
					spades[c-26] = c;
				}
			}
			if(c>=40&&c<=52){
				if(c==getLowestCard(3)){
					diamonds[c-39] = c;
				}
				if(c==getHighestCard(3)){
					diamonds[c-39] = c;
				}
			}
		}
	}
	
	public static int draw(){
		return Deck.draw();
	}
	
	public static int checkFirstTurn(){
		int ret=0;
		for(int i=0;i<Player1.numCards;i++){
			if(Player1.cards[i]==44)
				ret = 1;
		}
		for(int i=0;i<Player2.numCards;i++){
			if(Player2.cards[i]==44)
				ret = 2;
		}
		for(int i=0;i<Player3.numCards;i++){
			if(Player3.cards[i]==44)
				ret = 3;
		}
		return ret;
	}
	
	public static int getLowestCard(int cardType){
		int ret = 0;
		if(cardType==0){
			for(int i=6;i>0;i--){
				if(clubs[i]==0){
					ret = i;
					break;
				}
			}
		}
		if(cardType==1){
			for(int i=6;i>0;i--){
				if(hearts[i]==0){
					ret = i+13;
					break;
				}
			}
		}
		if(cardType==2){
			for(int i=6;i>0;i--){
				if(spades[i]==0){
					ret = i+26;
					break;
				}
			}
		}
		if(cardType==3){
			for(int i=6;i>0;i--){
				if(diamonds[i]==0){
					ret = i+39;
					break;
				}
			}
		}
		return ret;
	}
	
	public static int getHighestCard(int cardType){
		int ret = 0;
		if(cardType==0){
			for(int i=6;i<=13;i++){
				if(clubs[i]==0){
					ret = i;
					break;
				}
			}
		}
		if(cardType==1){
			for(int i=6;i<=13;i++){
				if(hearts[i]==0){
					ret = i+13;
					break;
				}
			}
		}
		if(cardType==2){
			for(int i=6;i<=13;i++){
				if(spades[i]==0){
					ret = i+26;
					break;
				}
			}
		}
		if(cardType==3){
			for(int i=6;i<=13;i++){
				if(diamonds[i]==0){
					ret = i+39;
					break;
				}
			}
		}
		return ret;
	}
	
	public static boolean validateTurn(){
		return true;
	}
	
	public static int getClubs(int index){
		return clubs[index];
	}
	
	public static int getHearts(int index){
		return hearts[index];
	}
	
	public static int getSpades(int index){
		return spades[index];
	}
	
	public static int getDiamonds(int index){
		return diamonds[index];
	}
	
	public static boolean checkValidMove(int c){
		int lowestClubs= getLowestCard(0) - 1;
		int lowestHeart= getLowestCard(1) - 1;
		int lowestSpade= getLowestCard(2) - 1;
		int lowestDiamond= getLowestCard(3) - 1;
		int highestClubs= getHighestCard(0) - 1;
		int highestHeart= getHighestCard(1) - 1;
		int highestSpade= getHighestCard(2) - 1;
		int highestDiamond= getHighestCard(3) - 1;
		if(c==lowestClubs||c==lowestDiamond||c==lowestHeart||c==lowestSpade){
			return true;
		}
		if(c==highestClubs||c==highestDiamond||c==highestHeart||c==highestSpade){
			return true;
		}
		return false;
	}
	
	public static boolean hasMove(int player){
		
		if(player==1){
			for(int i=0;i<4;i++){
				int l = getLowestCard(i)-1;
				for(int j=0;j<Player1.numCards;j++){
					if(Player1.cards[j]==l){
						return true;
					}
				}
			}
			for(int i=0;i<4;i++){
				int h = getHighestCard(i)-1;
				for(int j=0;j<Player1.numCards;j++){
					if(Player1.cards[j]==h){
						return true;
					}
				}
			}
		}
		else if(player==2){
			for(int i=0;i<4;i++){
				int l = getLowestCard(i)-1;
				for(int j=0;j<Player2.numCards;j++){
					if(Player2.cards[j]==l){
						return true;
					}
				}
			}
			for(int i=0;i<4;i++){
				int h = getHighestCard(i)-1;
				for(int j=0;j<Player2.numCards;j++){
					if(Player2.cards[j]==h){
						return true;
					}
				}
			}
		}
		else if(player==3){
			for(int i=0;i<4;i++){
				int l = getLowestCard(i)-1;
				for(int j=0;j<Player3.numCards;j++){
					if(Player3.cards[j]==l){
						return true;
					}
				}
			}
			for(int i=0;i<4;i++){
				int h = getHighestCard(i)-1;
				for(int j=0;j<Player3.numCards;j++){
					if(Player3.cards[j]==h){
						return true;
					}
				}
			}
		}
		
		
		return false;
	}
	
}