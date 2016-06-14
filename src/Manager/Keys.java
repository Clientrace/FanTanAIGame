package Manager;

import java.awt.event.KeyEvent;

public class Keys {
	public static final int NUM_KEYS = 2;
	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKey[] = new boolean[NUM_KEYS];
	
	public static final int ENTER = 0;
	public static final int ESCAPE = 1;
	
	public static void keySet(int num, boolean b){
		
		if(num == KeyEvent.VK_ENTER)
			keyState[ENTER] = b;
		if(num == KeyEvent.VK_ESCAPE)
			keyState[ESCAPE] = b;
	}
	
	public static boolean isHold(int i){
		return keyState[i];
	}
	
	public static void update(){
		for(int i=0; i<NUM_KEYS;i++){
			prevKey[i] = keyState[i];
		}
	}
	
	public static boolean isPressed(int num){
		return keyState[num]&&!prevKey[num];
	}
	
}
