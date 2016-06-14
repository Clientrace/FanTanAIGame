package Agents;

import java.util.Random;

import javax.swing.JOptionPane;

import Main.GamePanel;

public class XYZ {
	public static String agentName = "XYZ";
	public static String grpName = "clarence&friends";
	public static String members = "Kim Clarence Penaflor, etc..";
	public static int playerNumber = 3; //this variable will be used before the game start
	
	//new function creation is allowed
	
	private final int CLUBS = 1;
	private final int HEARTS = 2;
	private final int SPADES = 3;
	private final int DIAMONDS = 4;
	
	private static int[] pmove_top;
	private static int[] pmove_bot;
	private static int topsize;
	private static int botsize;
	
	public static int countClubs(){
		int count = 0;
		int c[] = AgentData.checkHand(playerNumber);
		for(int i=0;i<AgentData.getNumCards(playerNumber);i++){
			if(c[i]>=0&&c[i]<=12){
				count++;
			}
		}
		return count;
	}
	
	public static int countHearts(){
		int count = 0;
		int c[] = AgentData.checkHand(playerNumber);
		for(int i=0;i<AgentData.getNumCards(playerNumber);i++){
			if(c[i]>=13&&c[i]<=25){
				count++;
			}
		}
		return count;
	}
	
	public static int countSpades(){
		int count = 0;
		int c[] = AgentData.checkHand(playerNumber);
		for(int i=0;i<AgentData.getNumCards(playerNumber);i++){
			if(c[i]>=26&&c[i]<=38){
				count++;
			}
		}
		return count;
	}
	
	public static int countDiamonds(){
		int count = 0;
		int c[] = AgentData.checkHand(playerNumber);
		for(int i=0;i<AgentData.getNumCards(playerNumber);i++){
			if(c[i]>=39&&c[i]<=51){
				count++;
			}
		}
		return count;
	}
	
	public static int simulate(int trymove){
		
		
		int card1NoMove = 0;
		
		//try card1
		for(int n=0;n<1000;n++){
			int pcards[] = AgentData.checkHand(playerNumber);
			int p3[] = new int[15];
			int p3Top = 13;
			int p2Top = 13;
			int p2[] = new int[15];
			int deck[] = new int[26];
			boolean noMove1 = false, noMove2 = false;
			Random rand = new Random();
			int history[] = new int[26];
			int counter=0;
			for(int i=0;i<26;i++)
				history[i] = -1;
			for(int i=0;i<26;i++){
				int var;
				while(true){
					var = rand.nextInt(52);
					boolean repeat = false;
					for(int j=0;j<26;j++){
						if(var==history[j]){
							repeat = true;
						}
						if(trymove==var){
							break;
						}
					}
					for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
						if(var==pcards[i])
							repeat = true;
					}
					
					if(!repeat){
						history[counter] = var;
						counter++;
						break;
					}
				}
				deck[i] = var;
			}
			int c1=0,c2=0;
			for(int i=0;i<26;i++){
				if(i>=0&&i<=12){
					p2[c1] = deck[i];
					c1++;
				}
				else{
					p3[c2] = deck[i];
					c2++;
				}
	 		}
			noMove1 = false;
			for(int i=0;i<4;i++){
				int l = AgentData.getCardBottom(i)-1;
				for(int j=0;j<p2Top;j++){
					if(p2[j]==l){
						noMove1 = true;
					}
				}
			}
			for(int i=0;i<4;i++){
				int h = AgentData.getCardTop(i)-1;
				for(int j=0;j<p2Top;j++){
					if(p2[j]==h){
						noMove1 = true;
					}
				}
			}
			if(noMove1)
				card1NoMove++;
		}
		return card1NoMove;
	}
	
	public static int getWeight(int card){
		int weight=0;
		if(card == 5||card == 18||card==31){
			weight = 10;
		}
		else if(card==11||card==24||card==37||card==50){
			weight = 9;
			if(card==11){
				weight+=countClubs();
			}
			if(card==24){
				weight+=countHearts();
			}
			if(card==37){
				weight+=countSpades();
			}
			if(card==50){
				weight+=countDiamonds();
			}
		}
		else if(card==10||card==23||card==36||card==49){
			weight = 8;
			if(card==10){
				weight+=countClubs();
			}
			if(card==23){
				weight+=countHearts();
			}
			if(card==36){
				weight+=countSpades();
			}
			if(card==49){
				weight+=countDiamonds();
			}
		}
		else if(card==9||card==22||card==35||card==48){
			weight = 7;
			if(card==9){
				weight+=countClubs();
			}
			if(card==22){
				weight+=countHearts();
			}
			if(card==35){
				weight+=countSpades();
			}
			if(card==48){
				weight+=countDiamonds();
			}
		}
		else if(card==8||card==21||card==34||card==47){
			weight = 6;
			if(card==8){
				weight+=countClubs();
			}
			if(card==21){
				weight+=countHearts();
			}
			if(card==34){
				weight+=countSpades();
			}
			if(card==47){
				weight+=countDiamonds();
			}
		}
		else if(card==7||card==20||card==33||card==46){
			weight = 5;
			if(card==7){
				weight+=countClubs();
			}
			if(card==20){
				weight+=countHearts();
			}
			if(card==33){
				weight+=countSpades();
			}
			if(card==46){
				weight+=countDiamonds();
			}
		}
		else if(card==6||card==19||card==32||card==45){
			weight = 4;
			if(card==6){
				weight+=countClubs();
			}
			if(card==19){
				weight+=countHearts();
			}
			if(card==32){
				weight+=countSpades();
			}
			if(card==45){
				weight+=countDiamonds();
			}
		}
		else if(card==12||card==25||card==38||card==51){
			weight = 3;
			if(card==12){
				weight+=countClubs();
			}
			if(card==25){
				weight+=countHearts();
			}
			if(card==38){
				weight+=countSpades();
			}
			if(card==51){
				weight+=countDiamonds();
			}
		}
		else if(card==4||card==17||card==30||card==43){
			weight = 9;
			if(card==4){
				weight+=countClubs();
			}
			if(card==17){
				weight+=countHearts();
			}
			if(card==30){
				weight+=countSpades();
			}
			if(card==43){
				weight+=countDiamonds();
			}
		}
		else if(card==3||card==18||card==29||card==42){
			weight = 8;
			if(card==3){
				weight+=countClubs();
			}
			if(card==18){
				weight+=countHearts();
			}
			if(card==29){
				weight+=countSpades();
			}
			if(card==42){
				weight+=countDiamonds();
			}
		}
		else if(card==2||card==17||card==28||card==41){
			weight = 7;
			if(card==2){
				weight+=countClubs();
			}
			if(card==17){
				weight+=countHearts();
			}
			if(card==28){
				weight+=countSpades();
			}
			if(card==41){
				weight+=countDiamonds();
			}
		}
		else if(card==1||card==16||card==27||card==40){
			weight = 6;
			if(card==1){
				weight+=countClubs();
			}
			if(card==16){
				weight+=countHearts();
			}
			if(card==27){
				weight+=countSpades();
			}
			if(card==40){
				weight+=countDiamonds();
			}
		}
		else if(card==0||card==13||card==26||card==39){
			weight = 3;
			if(card==0){
				weight+=countClubs();
			}
			if(card==13){
				weight+=countHearts();
			}
			if(card==26){
				weight+=countSpades();
			}
			if(card==39){
				weight+=countDiamonds();
			}
		}
		
		return weight;
	}
	
	public static void checkPosibleMoves(){
		
		pmove_top = new int[20];
		pmove_bot = new int[20];
		topsize = 0;
		botsize = 0;
		
		int cards[] = AgentData.checkHand(playerNumber);
	
		for(int i=0;i<4;i++){
			int card = AgentData.getCardTop(i) - 1;
			for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
				if(cards[j]==card){
					pmove_top[topsize] = j;
					topsize++;
				}
			}	
		}
		
		for(int i=0;i<4;i++){
			int card = AgentData.getCardBottom(i) - 1;
			for(int j=0;j<AgentData.getNumCards(playerNumber);j++){
				if(cards[j]==card){
					pmove_bot[botsize] = j;
					botsize++;
				}
			}
			
		}
	}
	
	//all agents must have function move, this will return the agent's move
	public static void move(){
		//player move should be like this:
		int cards[] = AgentData.checkHand(playerNumber);
		checkPosibleMoves();
		String strPMoves = "MOVES:\nTOP:\n";
		for(int i=0;i<topsize;i++)
			strPMoves = strPMoves.concat(""+pmove_top[i]+"\n");
		strPMoves = strPMoves.concat("\nBOT:\n");
		for(int i=0;i<botsize;i++)
			strPMoves = strPMoves.concat(""+pmove_bot[i]+"\n");
		//JOptionPane.showMessageDialog(null, strPMoves);
		
		if(topsize>botsize){
			WeightedMove wm[] = new WeightedMove[topsize];
			for(int i=0;i<topsize;i++){
				wm[i] = new WeightedMove();
			}
			for(int i=0;i<topsize;i++){
				wm[i].setNum(pmove_top[i]);
				wm[i].setWeight(getWeight(cards[pmove_top[i]]));
			}
			for(int i=0;i<topsize;i++){
				for(int j=0;j<topsize;j++){
					if(wm[i].getWeight()>wm[j].getWeight()){
						int temp = wm[i].getWeight();
						wm[i].setWeight(wm[j].getWeight());
						wm[j].setWeight(temp);
					}
				}
			}
			if(topsize>2){
				if(wm[0].getWeight()==wm[1].getWeight()){
					int one = simulate(cards[wm[0].getNum()]);
					int two = simulate(cards[wm[1].getNum()]);
					if(one>two)
						AgentData.move(playerNumber,wm[0].getNum());
					else
						AgentData.move(playerNumber,wm[1].getNum());
				}
				else
					AgentData.move(playerNumber,wm[0].getNum());
			}
			else
				AgentData.move(playerNumber,wm[0].getNum());
			
		}
		else{
			WeightedMove wm[] = new WeightedMove[botsize];
			for(int i=0;i<botsize;i++){
				wm[i] = new WeightedMove();
 			}
			for(int i=0;i<botsize;i++){
				wm[i].setNum(pmove_bot[i]);
				wm[i].setWeight(getWeight(cards[pmove_bot[i]]));
			}
			for(int i=0;i<botsize;i++){
				for(int j=0;j<botsize;j++){
					if(wm[i].getWeight()>wm[j].getWeight()){
						int temp = wm[i].getWeight();
						wm[i].setWeight(wm[j].getWeight());
						wm[j].setWeight(temp);
					}
				}
			}
			//JOptionPane.showMessageDialog(null, "MOVED: "+wm[0].getNum());
			if(botsize>2){
				if(wm[0].getWeight()==wm[1].getWeight()){
					int one = simulate(cards[wm[0].getNum()]);
					int two = simulate(cards[wm[1].getNum()]);
					if(one>two)
						AgentData.move(playerNumber,wm[0].getNum());
					else
						AgentData.move(playerNumber,wm[1].getNum());
				}
				else
					AgentData.move(playerNumber,wm[0].getNum());
			}
			else
				AgentData.move(playerNumber,wm[0].getNum());
			
		}
		
		
		//move 6 of diamonds
		for(int i=0;i<AgentData.getNumCards(playerNumber);i++){
			if(cards[i]==44)
				AgentData.move(playerNumber,i);
		}
		//AgentData.move(playerNumber,*cardFromHand*);
	}
	
}

class WeightedMove{
	 private int num = 0;
	 private int weight = 0;
	 public int getNum(){
		 return num;
	 }
	 public int getWeight(){
		 return weight;
	 }
	 public void setNum(int n){
		 num = n;
	 }
	 public void setWeight(int w){
		 weight = w;
	 }
}