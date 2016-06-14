package Manager;

public class Pekwan {
	//all agents must copy this format:
	public static String agentName = "Pekwan";
	public static String grpName = "Pekwan";
	public static String members = "Gerard Velasco, Danielle Guarin, Jorem Olivarez, Jeffey Roxas";
	public static int playerNumber = 2; //this variable will be used before the game start
	//new function creation is allowed
	
	//all agents must have function move, this will return the agent's move
	public static void move(){
		//player move should be like this:
		
		int cards[] = AgentData.checkHand(playerNumber);
		int index=0;
		int priority[] = {5,4,3,2,1,0,1,2,3,4,5,6,7};
		int availables[] = new int[10];
		int availmoves[] = new int[10];
		int sixholder = 0; 
		boolean stopper = false;
		for(int i=0;i<4;i++){
			int card = AgentData.getCardTop(i) - 1;
			for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
				if(cards[j]==card){
					availables[index]=j;
					index++;
					if(cardvalue(cards[j])==5){
					stopper=true;
					sixholder=j;
					break;	
					}
					
					//AgentData.move(playerNumber,j);
					//break;
				}	
				if(stopper)
				break;	
			}	
			}
		
		for(int i=0;i<4;i++){
			int card = AgentData.getCardBottom(i) - 1;
			for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
				if(cards[j]==card){
					availables[index]=j;
					index++;
					if(cardvalue(cards[j])==5){
						stopper=true;
						sixholder=j;
						break;	
						}//AgentData.move(playerNumber,j);
					//break;
				}
				if(stopper)
				break;				
				}
			}
		int bestcard=0;
		for(int i = 0;i<index;i++){
		availmoves[i]=cardvalue(cards[availables[i]]);//0-12,ace to k
		System.out.println("AvailMoves(Card): "+availmoves[i]);
		availmoves[i]=priority[availmoves[i]];
		System.out.println("AvailMoves(Prio): "+availmoves[i]);
		}
		
		for(int i = 0;i<index;i++){
			if(availmoves[i]>availmoves[i+1]){
				bestcard=availables[i];
				
			}
		}
		/*for(int i = 0;i<index;i++){
			if(availables[i]>availables[i+1]){
				
			}
		}*/
		System.out.println("index:"+index);
		if(stopper)
		AgentData.move(playerNumber, sixholder);
		else
		AgentData.move(playerNumber,bestcard);
		//AgentData.move(playerNumber,*cardFromHand*);
	}
	public static int cardvalue(int value){
		int currentValue = 0;
		if(value>=0&&value<=12){
			currentValue = value;
		}
		else if(value>=13&&value<=25){
			currentValue = value - 13;
		}
		else if(value>=26&&value<=38){
			currentValue = value - 26;
		}
		else if(value>=39&&value<=51){
			currentValue = value - 39;
		}
		return currentValue;
	}
}