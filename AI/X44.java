package Manager;

public class X44 {
	//all agents must copy this format:
	public static String agentName = "AgentX44";
	public static String grpName = "clarence&friends";
	public static String members = "Kim Clarence Penaflor, etc..";
	public static int playerNumber = 1; //this variable will be used before the game start
	
	//new function creation is allowed
	
	//all agents must have function move, this will return the agent's move
	
	public static int countSixAbove(int cards[]){
		int ret = 0;
		for(int i=0;i<AgentData.getNumCards(playerNumber);i++){
			if(cards[i]>5)
				ret++;
		}
		return ret;
	}
	
	public static int countSixBelow(int cards[]){
		int ret = 0;
		for(int i=0;i<AgentData.getNumCards(playerNumber);i++){
			if(cards[i]<6)
				ret++;
		}
		return ret;
	}
	
	public static void move(){
		//player move should be like this:
		
		
		
		int cards[] = AgentData.checkHand(playerNumber);
		boolean topMovePossible = false;
		boolean botMovePossible = false;
		for(int i=0;i<4;i++){
			int card = AgentData.getCardTop(i) - 1;
			for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
				if(cards[j]==card){
					topMovePossible = true;
					break;
				}
			}	
		}
		for(int i=0;i<4;i++){
			int card = AgentData.getCardBottom(i) - 1;
			for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
				if(cards[j]==card){
					botMovePossible = true;
					break;
				}
			}
			
		}
		
		if(countSixBelow(cards)<countSixAbove(cards)){
			if(topMovePossible){
				for(int i=0;i<4;i++){
					int card = AgentData.getCardTop(i) - 1;
					for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
						if(cards[j]==card){
							AgentData.move(playerNumber,j);
							break;
						}
					}	
				}
			}
			else{
				for(int i=0;i<4;i++){
					int card = AgentData.getCardBottom(i) - 1;
					for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
						if(cards[j]==card){
							AgentData.move(playerNumber,j);
							break;
						}
					}
				}
			}
		}
		else{
			if(botMovePossible){	
				for(int i=0;i<4;i++){
					int card = AgentData.getCardBottom(i) - 1;
					for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
						if(cards[j]==card){
							AgentData.move(playerNumber,j);
							break;
						}
					}
				}
			}
			else{
				for(int i=0;i<4;i++){
					int card = AgentData.getCardTop(i) - 1;
					for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
						if(cards[j]==card){
							AgentData.move(playerNumber,j);
							break;
						}
					}	
				}
			}
		}
		//AgentData.move(playerNumber,*cardFromHand*);
	}
	
	
	
}
