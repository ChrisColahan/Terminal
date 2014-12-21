package com.incendiary.terminator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static Window inst;
	public static Screen screen;
	
	public static void create(int width, int height) {
		inst = new Window();
		inst.setTitle("TERMINATOR");
		
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension win = new Dimension(width, height);
		
		inst.setLocation(scr.width/2 - win.width/2, scr.height/2 - win.height/2);
		
		screen = new Screen();
		
		inst.setResizable(false);
		inst.add(BorderLayout.CENTER, screen);
		inst.setPreferredSize(win);
		inst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//inst.setUndecorated(true);	//for later
		inst.pack();
		inst.setVisible(true);
	}
	
	public static void destroy() {
		inst.dispatchEvent(new WindowEvent(inst, WindowEvent.WINDOW_CLOSING));
		inst = null;
		screen = null;
	}
	
	public static int getWindowWidth() {
		return Window.inst.getSize().width;
	}
	
	public static int getWindowHeight() {
		return Window.inst.getSize().height;
	}
}
