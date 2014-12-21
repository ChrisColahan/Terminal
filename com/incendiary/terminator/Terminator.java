package com.incendiary.terminator;

public class Terminator {
	//size, in characters
	public static final int WIDTH = 120, HEIGHT = 40, IMG_WIDTH = 5, IMG_HEIGHT = 10;
	
	public static void main(String[] args) {
		Window.create(WIDTH * IMG_WIDTH * 2, HEIGHT * IMG_HEIGHT);
		SubScreen s = new SubScreen(0, 0, WIDTH, HEIGHT, "TERMINATOR");
		SubScreen s2 = new SubScreen(WIDTH, 0, WIDTH, HEIGHT, "TERMINATOR MK 2");
		s.writeString("HELLO THERE STRANGER! DO YOU LIKE MY FINE TEXT? IM THE BETTER SCREEN.\nhow are you?");
		s2.writeString("IGNORE THE OTHER SCREEN, THIS ONE IS BETTER!");
		Screen.addSubScreen(s);
		Screen.addSubScreen(s2);
	}
}