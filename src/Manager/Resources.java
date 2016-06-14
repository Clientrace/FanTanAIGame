package Manager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Resources {
	
	public static BufferedImage[][] bg = load("bgpaper.png",1024,576);
	public static BufferedImage[][] card = load("card set.png",72,97);
	public static BufferedImage[][] font = load("font.png",8,8);
	public static BufferedImage[][] cfd = load("cardfacedown.png",72,97);
	
	
	public static BufferedImage[][] load(String s, int w, int h) {
		BufferedImage[][] ret;
		try {
			BufferedImage spritesheet = ImageIO.read(Resources.class.getResourceAsStream(s));
			int width = spritesheet.getWidth() / w;
			int height = spritesheet.getHeight() / h;
			ret = new BufferedImage[height][width];
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
				}
			}
			return ret;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}
	public static void drawString(Graphics2D g, String s, int x, int y, int size){
		s = s.toUpperCase();
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c==0) c=47;
			if(c==47) c=36;
			if(c==58) c=37;
			if(c==32) c=38;
			if(c==61) c=39;
			if(c==45) c=40;
			if(c==43) c=41;
			if(c>=65&&c<=90) c-=65;
			if(c>=48&&c<=57) c-=22;
			int row = c / font[0].length;
			int col = c % font[0].length;
			g.drawImage(font[row][col], x + size * i, y,size,size, null);
			
		}
	}
}
