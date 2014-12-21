package dylans;
import java.awt.Graphics;

import javax.swing.JPanel;


public class textRenderer extends JPanel {
private static final long serialVersionUID = 1L;

    public textRenderer(int w, int h) {
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
    			if(screenManager.cursorY==(screenManager.Height-2))
    				screenManager.shift();
    			continue;
    		}
    		screenManager.writeChar(s.charAt(k), screenManager.cursorX, screenManager.cursorY,r,b,g);
    		if(screenManager.cursorX<(screenManager.Width-1))
    			screenManager.cursorX++;
    		else
    		{
    			screenManager.cursorX=0;
    			screenManager.cursorY++;
    			if(screenManager.cursorY==(screenManager.Height-2))
    				screenManager.shift();
    		}
    	}
    }
    
    public void writeString(String s, int r, int g, int b,int r2, int g2, int b2)
    {
    	for(int k=0;k<s.length();k++)
    	{
    		if(s.charAt(k)=='\n')
    		{
    			screenManager.cursorX=0;
    			screenManager.cursorY++;
    			if(screenManager.cursorY==(screenManager.Height-2))
    				screenManager.shift();
    			continue;
    		}
    		screenManager.writeChar(s.charAt(k), screenManager.cursorX, screenManager.cursorY,r,b,g,r2,g2,b2);
    		if(screenManager.cursorX<(screenManager.Width-1))
    			screenManager.cursorX++;
    		else
    		{
    			screenManager.cursorX=0;
    			screenManager.cursorY++;
    			if(screenManager.cursorY==(screenManager.Height-2))
    				screenManager.shift();
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
            	
            	consoleCharacter c = screenManager.screen[x][y];

            	if(c!=null && c.needs_update)
            	{
            		g.drawImage(c.cache, x*5, y*10, null);
            	}
            	
            	else if(c==null)
            	{
            		screenManager.screen[x][y] = new consoleCharacter(' ');
            		g.drawImage(screenManager.screen[x][y].cache, x*5, y*10, null);
            	}
            	
            	}
            }
        repaint();
        }
}
