import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Terminal extends JFrame {
public static textRenderer panel;
    
    public static void main(String args[]) 
    {
        launch();
    }
    
    public static void launch()
    {
    	Terminal t = new Terminal();
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        int w,h=0;
        w = 120;
        h = 40;
        
        
        panel = new textRenderer(w, h);
        t.setPreferredSize(new Dimension(w*5,h*10));
        t.setResizable(false);
        
        t.add(panel);
        
        t.pack();
        t.setVisible(true);
        t.setFocusable(true);
        t.addKeyListener(new inputHandler());
        
        displaySmallerCat();
        writePrompt();
    }
    
    public static void writePrompt()
    {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	
    	panel.writeString(" Arathnim ",255,255,255,25,100,100);
        panel.writeString(""+ (char) 21,25,100,100,42,71,135);
        panel.writeString(" "+dateFormat.format(cal.getTime())+" ",255,255,255,42,71,135);
        panel.writeString(""+(char) 18+(char) 17+" ", 42,135,71);
    }
    
    public static void displayCat()
    {
    	BufferedImage cat = null;
    	try {
			cat = ImageIO.read(new File("res/cat.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	int image[] = new int[600];
    	cat.getRGB(0, 0, 30, 20, image, 0, 30);
    	
    	for(int k=0;k<600;k++)
    	{
    			Color c = new Color(image[k]);
    			panel.writeString(""+(char) 15+(char) 15,c.getRed(),c.getGreen(),c.getBlue());
    		
    		if(k%30 == 29)
    			panel.writeString("\n");
    	}
    	
    	panel.writeString("\n");
    }
    
    public static void displaySmallerCat()
    {
    	BufferedImage cat = null;
    	try {
			cat = ImageIO.read(new File("res/cat.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	int image[] = new int[600];
    	cat.getRGB(0, 0, 30, 20, image, 0, 30);
    	
    	for(int k=0;k<600;k++)
    	{
    		if((k/30) %2 == 0)
    		{
    			Color c = new Color(image[k]);
    			Color c2 = new Color(image[k+30]);
    			panel.writeString(""+(char) 16,c2.getRed(),c2.getGreen(),c2.getBlue(),c.getRed(),c.getGreen(),c.getBlue());
    			if(k%30 == 29)
        			panel.writeString("\n");
    		}
    		
    	}
    	
    	panel.writeString("\n");
    }
    
    public static void writeDoubleRow(int r, int g, int b,int r2, int g2, int b2)
    {
    	panel.writeString(""+(char) 16, r, g, b, r2, g2, b2);
    }
}
