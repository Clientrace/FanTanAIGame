package Manager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.JOptionPane; 

public class Agents {
	//declare each agents here:
	
	static String name = "";
	static int ip1= 0;
	static int ip2= 0;
	static int ip3= 0;
	static Class[] noParam = {};
	
	static String p1;
	static String p2;
	static String p3;
	
	
	public static void init(){
		
		p1 = JOptionPane.showInputDialog("Enter Player 1 Name:");
		p2 = JOptionPane.showInputDialog("Enter Player 2 Name:");
		p3 = JOptionPane.showInputDialog("Enter Player 3 Name:");

	}
	
	public static void agent1Move(){
		try{
			Class<?> cls = Class.forName("Manager."+p1);
			Object obj = cls.newInstance();
			Method method = cls.getDeclaredMethod("move", noParam);
			Field f = cls.getDeclaredField("playerNumber");
			f.setInt(obj,1);
			method.invoke(obj);
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"P1 DOES NOT EXIST");
		}
	}
	
	public static void agent2Move(){
		try{
			Class<?> cls = Class.forName("Manager."+p2);
			Object obj = cls.newInstance();
			Method method = cls.getDeclaredMethod("move", noParam);
			Field f = cls.getDeclaredField("playerNumber");
			f.setInt(obj,2);
			method.invoke(obj);
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"P2 DOES NOT EXIST");
		}
	}
	
	public static void agent3Move(){
		try{
			Class<?> cls = Class.forName("Manager."+p3);
			Object obj = cls.newInstance();
			Method method = cls.getDeclaredMethod("move", noParam);
			Field f = cls.getDeclaredField("playerNumber");
			f.setInt(obj,3);
			method.invoke(obj);
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"P3 DOES NOT EXIST");
		}
	}
	
}
