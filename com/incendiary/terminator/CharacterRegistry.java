package com.incendiary.terminator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

//contains the default values for the character images
public class CharacterRegistry {
	private static HashMap<Character, BufferedImage> chars = new HashMap<Character, BufferedImage>();
	static String charMap=" ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz." + (char) 15 + (char) 16 + (char) 17 + (char) 18 + (char) 19 + (char) 20+"()><[]012345689{}7"
			+ "\\/;:'\",!@#$%^&*-_+=" + (char) 21 + (char) 22 + "?";
	static {
		//fill all images
		 try {
		   	for(int k=0;k<charMap.length();k++) {
		   		chars.put(charMap.charAt(k), ImageIO.read(new File("res/char"+k+".png")));
		   	}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 //apply the style to it (invert colors, but don't change alpha)
		 for(BufferedImage img : chars.values()) {
			 for(int x = 0; x < img.getWidth(); x++) {
				 for(int y = 0; y < img.getHeight(); y++) {
					 Color c = new Color(img.getRGB(x, y));
					 Color inverted = new Color(255 - c.getRed(), 255 - c.getBlue(), 255 - c.getGreen(), c.getAlpha());
					 img.setRGB(x, y, inverted.getRGB());
				 }
			 }
		 }
	}
	
	public static BufferedImage get(char c) {
		return chars.get(c);
	}
}