package Manager;

import java.util.Random;

public class Deck {
	private static Random rand = new Random();
	private static int deck[] = new int[52];
	private static int top;
	public static void init(){
		deck = GameData.shuffle();
		top = 52;
	}
	public static int draw(){
		top--;
		return deck[top];
	}
	public static int numCards(){
		return top;
	}
	public static int getCard(int index){
		return deck[index];
	}
	public static boolean isEmpty(){
		if(top==0)
			return true;
		return false;
	}
}
