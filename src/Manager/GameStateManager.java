package Manager;

import java.awt.Graphics2D;

import GameStates.PlayState;

public class GameStateManager {
	public static int NUM_STATES = 2;
	public static int MENU = 0;
	public static int PLAY = 1;
	private GameStates[] gameStates;
	private int currentState;
	private int prevState;
	
	public GameStateManager(){
		gameStates = new GameStates[NUM_STATES];
		loadState(PLAY);
	}
	
	public void loadState(int num){
		prevState = currentState;
		currentState = num;
		if(num==PLAY){
			gameStates[num] = new PlayState(this);
			gameStates[num].init();
		}
	}
	
	public void unloadState(int num){
		gameStates[num] = null;
	}
	
	public void update(){
		gameStates[currentState].update();
	}
	
	public void draw(Graphics2D g){
		gameStates[currentState].draw(g);
	}
}
