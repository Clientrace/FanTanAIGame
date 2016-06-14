package GameStates;

import java.awt.Graphics2D;
import java.util.Random;

import Manager.AgentData;
import Manager.Agents;
import Manager.BoardData;
import Manager.Deck;
import Manager.GameData;
import Manager.GameStateManager;
import Manager.GameStates;
import Manager.Keys;
import Manager.Player1;
import Manager.Player2;
import Manager.Player3;
import Manager.Resources;

public class PlayState extends GameStates{
	
	private static int phase = 0;
	private static int ticks = 0;
	public static int curTurn = 0;
	private static boolean check = true;
	private static int delay = 0;
	private static boolean start = false;
	private static boolean hasWinner = false;
	private static int winner = 0;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		Deck.init();
	}
	
	public void restart(){
		phase = 0;
		ticks = 0;
		curTurn = 0;
		check = true;
		delay = 0;
		start = false;
		hasWinner = false;
		winner = 0;
		Deck.init();
		Player1.init();
		Player2.init();
		Player3.init();
		BoardData.init();
	}

	public void update() {
		handleInput();
		ticks++;
		if(ticks>delay){
			ticks=0;
			if(phase==0){
				if(Player1.numCards<13&&curTurn==0)
					Player1.setCard(Deck.draw());
				else if(Player2.numCards<13&&curTurn==1)
					Player2.setCard(Deck.draw());
				else if(Player3.numCards<13&&curTurn==2)
					Player3.setCard(Deck.draw());
				else{
					phase++;
					delay = 0;
				}
				curTurn++;
				if(curTurn>2)
					curTurn = 0;
				//BoardData.clubs[6] = 6;
				//BoardData.hearts[6] = 19;
				//BoardData.spades[6] = 32;
				//BoardData.diamonds[6] = 45;
				BoardData.clubs[6] = 0;
				BoardData.hearts[6] = 0;
				BoardData.spades[6] = 0;
				BoardData.diamonds[6] = 0;
				
			}
			else if(phase==1){
				if(check){
					check=false;
					curTurn = BoardData.checkFirstTurn();
				}
				if(start){
					if(Player1.numCards==0){
						hasWinner = true;
						winner = 1;
					}
					else if(Player2.numCards==0){
						hasWinner = true;
						winner = 2;
					}
					else if(Player3.numCards==0){
						hasWinner = true;
						winner = 3;
					}
					if(!hasWinner){
						if(curTurn==1){
							if(!BoardData.hasMove(1)){
								Player1.draw();
								System.out.println("Player1 Move: Draw");
							}
							else{
								Agents.agent1Move();
								Player1.turn(Player1.outputCard);
								System.out.println("Player1 Move: "+GameData.cards[Player1.outputCard-1]);
							}
							curTurn++;
						}
						else if(curTurn==2){
							if(!BoardData.hasMove(2)){
								Player2.draw();
								System.out.println("Player2 Move: Draw");
							}
							else{
								Agents.agent2Move();
								Player2.turn(Player2.outputCard);
								System.out.println("Player2 Move: "+GameData.cards[Player2.outputCard-1]);
							}
							curTurn++;
						}
						else if(curTurn==3){
							if(!BoardData.hasMove(3)){
								Player3.draw();
								System.out.println("Player3 Move: Draw");
							}
							else{
								Agents.agent3Move();
								Player3.turn(Player3.outputCard);
								System.out.println("Player3 Move: "+GameData.cards[Player3.outputCard-1]);
							}
							
							curTurn=1;
						}
					}
				}
			}
		}
	}

	public void handleInput() {
		
		if(hasWinner&&Keys.isPressed(Keys.ENTER)){
			restart();
		}
		else if(Keys.isPressed(Keys.ENTER))
			start = true;
	}

	public void draw(Graphics2D g) {
		g.drawImage(Resources.bg[0][0],0,0,1010,710,null);
		if(phase==0){
			Resources.drawString(g,"PLAYER1", 0, 0, 16);
			Resources.drawString(g,"PLAYER2", 0, 230, 16);
			Resources.drawString(g,"PLAYER3", 0, 460, 16);
			for(int i=0;i<Player1.numCards;i++){
				int col = Player1.cards[i]%13;
				int row = Player1.cards[i]/13;
				if(i<10)
					g.drawImage(Resources.card[row][col],10+i*100,30,null);
				else
					g.drawImage(Resources.card[row][col],10+i*100-1000,100,null);
			}
			for(int i=0;i<Player2.numCards;i++){
				int col = Player2.cards[i]%13;
				int row = Player2.cards[i]/13;
				if(i<10)
					g.drawImage(Resources.card[row][col],10+i*100,260,null);
				else
					g.drawImage(Resources.card[row][col],10+i*100-1000,330,null);
			}
			for(int i=0;i<Player3.numCards;i++){
				int col = Player3.cards[i]%13;
				int row = Player3.cards[i]/13;
				if(i<10)
					g.drawImage(Resources.card[row][col],10+i*100,490,null);
				else
					g.drawImage(Resources.card[row][col],10+i*100-1000,560,null);
			}
			Resources.drawString(g,"DRAW PHASE", 360, 340, 32);
		}
		if(phase==1){
			Resources.drawString(g,"PLAYER1", 0, 530, 16);
			Resources.drawString(g,"PLAYER2", 0, 470+130, 16);
			Resources.drawString(g,"PLAYER3", 0, 410+260, 16);
			Resources.drawString(g,"DECK", 920, 20, 16);
			for(int i=0;i<Player1.numCards;i++){
				int col = Player1.cards[i]%13;
				int row = Player1.cards[i]/13;
				g.drawImage(Resources.card[row][col],150+10+i*40,500+30,null);
			}
			for(int i=0;i<Player2.numCards;i++){
				int col = Player2.cards[i]%13;
				int row = Player2.cards[i]/13;
				g.drawImage(Resources.card[row][col],150+10+i*40,440+160,null);
			}
			for(int i=0;i<Player3.numCards;i++){
				int col = Player3.cards[i]%13;
				int row = Player3.cards[i]/13;
				g.drawImage(Resources.card[row][col],150+10+i*40,380+290,null);
			}
			
			for(int i=0;i<14;i++){
				if(BoardData.getClubs(i)!=0){
					if(BoardData.getClubs(i)!=0){
						int col = (BoardData.getClubs(i)-1)%13;
						int row = (BoardData.getClubs(i)-1)/13;
						g.drawImage(Resources.card[row][col],40,10+i*30,null);
					}
				}
			}
			
			for(int i=0;i<14;i++){
				if(BoardData.getHearts(i)!=0){
					if(BoardData.getHearts(i)!=0){
						int col = (BoardData.getHearts(i)-1)%13;
						int row = (BoardData.getHearts(i)-1)/13;
						g.drawImage(Resources.card[row][col],300,10+i*30,null);
					}
				}
			}
			
			for(int i=0;i<14;i++){
				if(BoardData.getSpades(i)!=0){
					if(BoardData.getSpades(i)!=0){
						int col = (BoardData.getSpades(i)-1)%13;
						int row = (BoardData.getSpades(i)-1)/13;
						g.drawImage(Resources.card[row][col],560,10+i*30,null);
					}
				}
			}
			
			for(int i=0;i<14;i++){
				if(BoardData.getDiamonds(i)!=0){
					if(BoardData.getDiamonds(i)!=0){
						int col = (BoardData.getDiamonds(i)-1)%13;
						int row = (BoardData.getDiamonds(i)-1)/13;
						g.drawImage(Resources.card[row][col],820,10+i*30,null);
					}
				}
			}
			
			//deck
			for(int i=0;i<Deck.numCards();i++){
				int col = Deck.getCard(i)%13;
				int row = Deck.getCard(i)/13;
				g.drawImage(Resources.cfd[0][0],920,50+i*30,null);
			}
			if(hasWinner){
				if(winner==1){
					Resources.drawString(g,"PLAYER1 WINS", 340, 340, 32);
				}
				if(winner==2){
					Resources.drawString(g,"PLAYER2 WINS", 340, 340, 32);
				}
				if(winner==3){
					Resources.drawString(g,"PLAYER3 WINS", 340, 340, 32);
				}
				Resources.drawString(g,"PRESS ENTER TO CONTINUE", 348, 400, 16);
			}
			
		}
	}

}
