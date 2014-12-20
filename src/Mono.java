import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Mono extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private static DylansJankyTextRenderer panel;
    
    public static void main(String args[]) {
        Mono m = new Mono();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        int w,h=0;
        w = 120;
        h = 40;
        
        
        panel = new DylansJankyTextRenderer(w, h);
        m.setPreferredSize(new Dimension(w*5,h*10));
        m.setResizable(false);
        
        m.add(panel);
        
        m.pack();
        m.setVisible(true);
        m.setFocusable(true);
        m.addKeyListener(new listner());
        
    }
}

class DylansJankyTextRenderer extends JPanel {

    private static final long serialVersionUID = 1L;
    
    public DylansJankyTextRenderer(int w, int h) {
        super();
        screenManager.init(w, h);
    }
    
    public void setText(char c, int x, int y) {
        screenManager.writeChar(c, x, y);
    }
    
    public void writeString(String s, int r, int g, int b)
    {
    	for(int k=0;k<s.length();k++)
    	{
    		if(s.charAt(k)=='\n')
    		{
    			screenManager.cursorX=0;
    			screenManager.cursorY++;
    			continue;
    		}
    		screenManager.writeChar(s.charAt(k), screenManager.cursorX, screenManager.cursorY,r,b,g);
    		if(screenManager.cursorX<(screenManager.Width-1))
    			screenManager.cursorX++;
    		else
    		{
    			screenManager.cursorX=0;
    			screenManager.cursorY++;
    		}
    	}
    }
    
    public void writeString(String s)
    {
    	writeString(s, 255, 255, 255);
    }
    
    public void writeString(String s, int x, int y)
    {
    	for(int k=0;k<s.length();k++)
    		screenManager.writeChar(s.charAt(k), x+k, y, 255, 255 , 255);
    }
    
    public void writeString(String s, int x, int y, int r, int g, int b)
    {
    	for(int k=0;k<s.length();k++)
    		screenManager.writeChar(s.charAt(k), x+k, y, r,g,b);
    }
    
    public void paintComponent(Graphics g) {
        for(int x = 0; x < screenManager.Width; x++) {
            for(int y = 0; y < screenManager.Height; y++) {
            	
            	enhancedCharacter c = screenManager.screen[x][y];

            	if(c!=null && c.needs_update)
            	{
            		g.drawImage(c.cache, x*5, y*10, null);
            		//screenManager.screen[x][y].needs_update=false;
            	}
            	
            	else if(c==null)
            	{
            		screenManager.screen[x][y] = new enhancedCharacter(' ');
            		g.drawImage(screenManager.screen[x][y].cache, x*5, y*10, null);
            	}
            	
            	}
            }
        repaint();
        }
    	
    }
    

class enhancedCharacter
{
	char value;
	int r = 0,g = 0,b = 0;
	BufferedImage cache;
	boolean needs_update=true;
	
	public enhancedCharacter(char value, int r, int g, int b) {
		this.value = value;
		this.r = r;
		this.g = g;
		this.b = b;
		genImage();
	}

	public enhancedCharacter(char value) {
		this.value = value;
		genImage();
	}
	
	private void genImage()
	{
		BufferedImage bi = deepCopy(screenManager.table.get(value));
    	
    	int image[] = new int[50];
    	bi.getRGB(0, 0, 5, 10, image, 0, 5);
    	
    	for(int m=0;m<50;m++)
    	{
    		if((image[m] == Color.WHITE.getRGB()))
    			bi.setRGB(m%5, m/5, new Color(0, 0, 0).getRGB());	
    		if(image[m] != Color.WHITE.getRGB())
    			bi.setRGB(m%5, m/5, new Color(r, g, b).getRGB());
    	}
    	
    	cache = bi;
	}
	
	static BufferedImage deepCopy(BufferedImage bi) {
   	 ColorModel cm = bi.getColorModel();
   	 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
   	 WritableRaster raster = bi.copyData(null);
   	 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
   	}

	@Override
	public String toString() {
		return "enhancedCharacter [value=" + value + ", r=" + r + ", g=" + g
				+ ", b=" + b + "]";
	}
	
	
	
}

class screenManager
{
	static int cursorX=0;
	static int cursorY=0;
	static HashMap<Character, BufferedImage> table;
	static String charMap=" ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.";
	static enhancedCharacter[][] screen;
	static int Width,Height;
	
	public static void init(int w, int h)
	{
		Width = w;
	    Height = h;
	    
	    screen = new enhancedCharacter[Width][Height];
	    
	    table = new HashMap<Character, BufferedImage>();
	    
	    try {
	    	for(int k=0;k<charMap.length();k++)
	    		table.put(charMap.charAt(k), ImageIO.read(new File("res/char"+k+".png")));  	
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    for(int x = 0; x < Width; x ++) {
            for(int y = 0; y < Height; y ++) {
                screen[x][y] = new enhancedCharacter(' ');
                screen[x][y].needs_update=true;
            }
        }
	    
	    screen = new enhancedCharacter[Width][Height];
	}
	
	public static void writeChar(char c, int x, int y)
	{
		System.out.println("Called writeChar with : "+c+' '+x+" "+y+" ");
		screen[x][y] = new enhancedCharacter(c);
		screen[x][y].needs_update=true;
	}
	
	public static void writeChar(char c, int x, int y, int r, int b, int g)
	{
		System.out.println("Called writeChar with : "+c+' '+x+" "+y+" ");
		screen[x][y] = new enhancedCharacter(c, r, b, g);
		screen[x][y].needs_update=true;
	}
}

class listner implements KeyListener
{

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()=='\n')
		{
			screenManager.cursorX=0;
			screenManager.cursorY++;
			return;
		}
		
		screenManager.writeChar(e.getKeyChar(), screenManager.cursorX, screenManager.cursorY,255,255,255);
		if(screenManager.cursorX<(screenManager.Width-1))
			screenManager.cursorX++;
		else
		{
			screenManager.cursorX=0;
			screenManager.cursorY++;
		}
		
	}
	
}