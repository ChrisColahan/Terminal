package dylans;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class screenManager
{
	static int cursorX=0;
	static int cursorY=0;
	static HashMap<Character, BufferedImage> table;
	static String charMap=" ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz." + (char) 15 + (char) 16 + (char) 17 + (char) 18 + (char) 19 + (char) 20+"()><[]012345689{}7"
			+ "\\/;:'\",!@#$%^&*-_+=" + (char) 21 + (char) 22;
	static consoleCharacter[][] screen;
	static int Width,Height;
	public static String input="";
	
	public static void init(int w, int h)
	{
		Width = w;
	    Height = h;
	    
	    screen = new consoleCharacter[Width][Height];
	    
	    table = new HashMap<Character, BufferedImage>();
	    
	    try {
	    	for(int k=0;k<charMap.length();k++)
	    		table.put(charMap.charAt(k), ImageIO.read(new File("res/char"+k+".png")));  	
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    for(int x = 0; x < Width; x ++) {
            for(int y = 0; y < Height; y ++) {
                screen[x][y] = new consoleCharacter(' ');
                screen[x][y].needs_update=true;
            }
        }
	    
	    screen = new consoleCharacter[Width][Height];
	}
	
	public static void writeChar(char c, int x, int y)
	{
		writeChar(c, x, y, 0,0,0);
	}
	
	public static void writeChar(char c, int x, int y, int r, int b, int g)
	{
		writeChar(c, x, y, r, b, g, 0, 0, 0);
	}
	
	public static void writeChar(char c, int x, int y, int r, int b, int g,int r2, int b2, int g2)
	{
		screen[x][y] = new consoleCharacter(c, r, b, g, r2, b2, g2);
		screen[x][y].needs_update=true;
	}
	
	public static void shift()
	{
		for (int k=0;k<Width;k++)
			for (int j=0;j<Height-1;j++)
				screen[k][j] = screen[k][j+1];
		cursorY--;
	}
}