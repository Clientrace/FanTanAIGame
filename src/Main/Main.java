package Main;

import javax.swing.JFrame;

import Manager.Agents;

public class Main {
	public static void main(String args[]){
		JFrame frame = new JFrame("PEKWA");
		frame.add(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		Agents.init();
	}
}
