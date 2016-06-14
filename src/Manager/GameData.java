package Manager;

import java.util.Random;

public class GameData {
	public static String cards[] = {"ace of club","2 of clubs","3 of clubs","4 of clubs","5 of clubs","6 of clubs","7 of clubs","8 of clubs","9 of clubs","10 of clubs","jack of clubs","queen of clubs","king of clubs"
			,"ace of heart","2 of hearts","3 of hearts","4 of hearts","5 of hearts","6 of hearts","7 of hearts","8 of hearts","9 of hearts","10  of hearts","jack of hearts","queen of hearts","king of hearts"
			,"ace of spade","2 of spades","3 of spades","4 of spades","5  of spades","6 of spades","7 of spades","8 of spades","9 of spades","10 of spades","jack of spades","queen of spades","king of spades"
			,"ace of diamond","2 of diamonds","3 of diamonds","4 of diamonds","5 of diamonds","6 of diamonds","7 of diamonds","8 of diamonds","9 of diamonds","10 of diamonds","jack of diamonds","queen of diamonds","king of diamonds"};
	
	public static int[] shuffle(){
		Random rand = new Random();
		int history[] = new int[52];
		int deck[] = new int[52];
		int counter=0;
		for(int i=0;i<52;i++)
			history[i] = -1;
		for(int i=0;i<52;i++){
			int var;
			while(true){
				var = rand.nextInt(52);
				boolean repeat = false;
				for(int j=0;j<52;j++){
					if(var==history[j]){
						repeat = true;
					}
				}
				if(!repeat){
					history[counter] = var;
					counter++;
					break;
				}
			}
			deck[i] = var;
		}
		for(int i=0;i<13;i++){
			if(deck[i]==44){
				int temp = 0;
				int r = rand.nextInt(3);
				if(r==0){
					temp = deck[51];
					deck[51] = 44;
				}
				if(r==1){
					temp = deck[50];
					deck[50] = 44;
				}
				if(r==2){
					temp = deck[49];
					deck[49] = 44;
				}
				deck[i] = temp;
				System.out.print("\n\n\nlock");
				break;
			}
		}
		return deck;
	}
	
	private static void printDeck(int deck[]){
		for(int i=0;i<52;i++){
			System.out.print("\n"+cards[deck[i]]);
		}
		System.out.print("\n\n");
	}

}
