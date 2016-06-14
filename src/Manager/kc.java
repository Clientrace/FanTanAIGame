package Manager;

public class kc {
	
	public static String agentName = "AgentX44";
	public static String grpName = "clarence&friends";
	public static String members = "Kim Clarence Penaflor, etc..";
	public static int playerNumber = 1; //this variable will be used before the game start
	
	//new function creation is allowed
	
	//all agents must have function move, this will return the agent's move
	public static void move(){
		//player move should be like this:
		setCard();
		int cards[] = AgentData.checkHand(playerNumber);
		
		for(int i=4;i>0;i--){
			int card = AgentData.getCardTop(i) - 1;
			for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
				if(cards[j]==card){
					AgentData.move(playerNumber,j);
					break;
				}
			}	
		}
		
		for(int i=0;i<4;i++){
			int card = AgentData.getCardBottom(i) - 1;
			for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
				if(cards[j]==card){
					AgentData.move(playerNumber,j);
					break;
				}
			}
			
		}
		//AgentData.move(playerNumber,*cardFromHand*);
	}
	
	public static int getCardValue(int c){
		if(c>=0&&c<=12){
			return c;
		}
		if(c>=13&&c<=26){
			return c-13;
		}
		if(c>=27&&c<=40){
			return c-26;
		}
		if(c>=41&&c<=53){
			return c-39;
		}
		return 0;
	}
	
	
	public static void setCard(){
		if(playerNumber==1){
			int p2card[] = AgentData.checkHand(2);
			for(int i=0;i<Player1.numCards;i++){
				if(getCardValue(Player1.cards[i])>getCardValue(p2card[i]))
					Player1.cards[i] = p2card[i];
			}
		}
	}
	
}
