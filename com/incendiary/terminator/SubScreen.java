package com.incendiary.terminator;

public class SubScreen {
	int x, y, w, h;
	PrintableCharacter[][] chars;
	int cursorX, cursorY;
	String title;
	
	public SubScreen(int x, int y, int width, int height, String title) {
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
		this.title = title;
		
		chars = new PrintableCharacter[width][height];
		
		for(int i = 0; i < chars.length; i ++) {
			for(int j = 0; j < chars[i].length; j ++) {
				chars[i][j] = new PrintableCharacter(' ', CharacterRegistry.get(' '));
			}
		}
	}
	
	public void writeChar(PrintableCharacter c) {
		if(cursorX >= chars.length) {
			cursorX = 0;
			cursorY ++;
		}
		if(cursorY >= chars[cursorX].length) {
			for(int i = 0; i < chars.length; i ++) {
				for(int j = 1; j < chars[i].length; j ++) {
					chars[i][j - 1] = chars[i][j];
					if(j == chars[i].length - 1) {
						chars[i][j] = new PrintableCharacter(' ', CharacterRegistry.get(' '));
					}
				}
			}
			cursorY --;
		}
		
		if(c.ch == '\n') {
			cursorX = 0;
			cursorY ++;
		}
		else if(c.ch == '\t') {
			for(int i = 0; i < 4; i ++)
				writeChar(new PrintableCharacter(' ', CharacterRegistry.get(' ')));
		}
		else if(c.ch == '\b') {
			chars[cursorX][cursorY] = new PrintableCharacter(' ', CharacterRegistry.get(' '));
			cursorX --;
			if(cursorX < 0) {
				if(cursorY > 0)
					cursorY --;
				cursorX = 0;
			}
		}
		else {
			chars[cursorX][cursorY] = c;
			cursorX ++;
		}
	}
	
	public void writeString(String s) {
		for(int i = 0; i < s.length(); i ++) {
			writeChar(new PrintableCharacter(s.charAt(i), CharacterRegistry.get(s.charAt(i))));
		}
	}
}