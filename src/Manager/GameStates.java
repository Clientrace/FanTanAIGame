package Manager;

import java.awt.Graphics2D;

public abstract class GameStates {
	protected GameStateManager gsm;
	public GameStates(GameStateManager gsm){
		this.gsm = gsm;
	}
	public abstract void init();
	public abstract void update();
	public abstract void handleInput();
	public abstract void draw(Graphics2D g);
}
