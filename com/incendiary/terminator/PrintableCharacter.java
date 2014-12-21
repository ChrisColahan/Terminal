package com.incendiary.terminator;

import java.awt.image.BufferedImage;

public class PrintableCharacter {
	BufferedImage image;
	char ch;
	
	public PrintableCharacter(char c, BufferedImage image) {
		ch = c;
		this.image = image;
	}
}