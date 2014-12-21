import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


public class consoleCharacter {
	char value;
	int r = 0,g = 0,b = 0;
	int r2 = 0,g2 = 0, b2 = 0;
	BufferedImage cache;
	boolean needs_update=true;
	
	public consoleCharacter(char value, int r, int g, int b) {
		this.value = value;
		this.r = r;
		this.g = g;
		this.b = b;
		genImage();
	}
	
	public consoleCharacter(char value, int r, int g, int b, int r2, int g2, int b2) {
		this.value = value;
		this.r = r;
		this.g = g;
		this.b = b;
		this.r2 = r2;
		this.g2 = g2;
		this.b2 = b2;
		genImage();
	}

	public consoleCharacter(char value) {
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
    			bi.setRGB(m%5, m/5, new Color(r2, g2, b2).getRGB());	
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
