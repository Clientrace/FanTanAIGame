package Manager;

public class SampleAgent3 {

	//all agents must copy this format:
		public static String agentName = "AgentX44";
		public static String grpName = "clarence&friends";
		public static String members = "Kim Clarence Penaflor, etc..";
		public static int playerNumber = 3; //this variable will be used before the game start
		
		//new function creation is allowed
		
		//all agents must have function move, this will return the agent's move
		public static void move(){
			//player move should be like this:
			
			int cards[] = AgentData.checkHand(playerNumber);
			
			
			for(int i=0;i<4;i++){
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
		
		
		
	
}
