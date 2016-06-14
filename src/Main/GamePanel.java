package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Manager.GameStateManager;
import Manager.Keys;

public class GamePanel extends JPanel implements KeyListener, Runnable {

	private BufferedImage image;
	private int fps = 60;
	private int TARGET_TIME = fps/1000000;
	private Thread thread;
	private Graphics2D g;
	private GameStateManager gsm;
	
	public GamePanel(){
		gsm = new GameStateManager();
		setPreferredSize(new Dimension(1000,700));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread==null){
			thread = new Thread(this);
			thread.start();
			addKeyListener(this);
		}
	}

	public void run(){
		init();
		
		long start;
		long wait;
		long elapsed;
		while(true){
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			elapsed = System.nanoTime() - start;
			wait = TARGET_TIME-elapsed/1000000;
			if(wait < 0)
				wait = TARGET_TIME;
			try{
				Thread.sleep(wait);
			}catch(Exception e){}
		}
	}
	
	public void update(){
		gsm.update();
		Keys.update();
	}
	
	public void draw(){
		gsm.draw(g);
	}
	
	public void init(){
		image = new BufferedImage(1360,768,1);
		g = (Graphics2D)image.getGraphics();
		gsm = new GameStateManager();
	}
	
	public void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image,0,0,1360,768,null);
		g2.dispose();
	}
	public void keyTyped(KeyEvent key) {
		
	}
	
	public void keyPressed(KeyEvent key){
		Keys.keySet(key.getKeyCode(),true);
	}
	
	public void keyReleased(KeyEvent key){
		Keys.keySet(key.getKeyCode(),false);
	}
	
}
