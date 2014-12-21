package com.incendiary.terminator;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Screen extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<SubScreen> subScreens = new ArrayList<SubScreen>();
	
	public static void addSubScreen(SubScreen s) {
		subScreens.add(s);
	}
	
	public static void removeSubScreen(SubScreen s) {
		subScreens.remove(s);
	}
	
	public void paintComponent(Graphics g) {
		for(SubScreen s : subScreens) {
			for(int x = 0; x < s.chars.length; x ++) {
				for(int y = 0; y < s.chars[x].length; y ++) {
					int width = s.chars[x][y].image.getWidth();
					int height = s.chars[x][y].image.getHeight();
					
					int drawX = x*width + s.x*width;
					int drawY = y*height + s.y*height;
					
					if(drawX <= s.w*width + s.x*width && drawY <= s.h*height + s.y*height)
							g.drawImage(s.chars[x][y].image, drawX, drawY, null);
				}
			}
		}
		repaint();
	}
}
